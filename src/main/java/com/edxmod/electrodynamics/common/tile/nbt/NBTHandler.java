package com.edxmod.electrodynamics.common.tile.nbt;

import com.edxmod.electrodynamics.common.lib.EDXLogger;
import com.edxmod.electrodynamics.common.tile.nbt.data.AbstractSerializer;
import com.edxmod.electrodynamics.common.util.ArrayHelper;
import com.google.common.collect.Maps;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * @author dmillerw
 */
public class NBTHandler {

	private static boolean validField(Field field) {
		if (Modifier.isPrivate(field.getModifiers())) {
			return false;
		}

		if (Modifier.isStatic(field.getModifiers())) {
			return false;
		}

		if (Modifier.isAbstract(field.getModifiers())) {
			return false;
		}

		if (field.getAnnotation(SideOnly.class) != null) {
			return false;
		}

		if (field.getAnnotation(NBTData.class) == null) {
			return false;
		}

		return true;
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target( {ElementType.FIELD})
	public static @interface NBTData {

	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target( {ElementType.FIELD})
	public static @interface ArrayDefault {
		int value() default 0;
	}

	public static void writeObject(String name, Object object, NBTTagCompound nbt) {
		Class<?> type = object.getClass();

		if (type.isEnum()) {
			Enum<?> instance = (Enum<?>) object;
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("class", instance.getClass().getCanonicalName());
			tag.setString("name", instance.name());
			nbt.setTag(name, tag);
		} else if (type.isArray()) {
			Object[] array = (Object[]) object;
			NBTTagCompound arrayTag = new NBTTagCompound();
			NBTTagList list = new NBTTagList();

			arrayTag.setInteger("size", array.length);

			for (int i=0; i<array.length; i++) {
				if (array[i] != null) {
					NBTTagCompound tag = new NBTTagCompound();
					tag.setInteger("index", i);
					NBTHandler.writeObject(name + "_" + i, array[i], tag);
					list.appendTag(tag);
				}
			}

			arrayTag.setTag("contents", list);
			nbt.setTag(name, arrayTag);
		} else {
			boolean serialized = false;
			for (AbstractSerializer<?> serializer : AbstractSerializer.serializerList) {
				if (serializer.canHandle(type)) {
					serializer.serialize(name, object, nbt);
					serialized = true;
					break;
				}
			}

			if (!serialized) {
				// We can check specifically for boxed data classes because
				// Objects don't store unboxed primitives
				if (type == byte.class || type == Byte.class) {
					nbt.setByte(name, (Byte) object);
				} else if (type == boolean.class || type == Boolean.class) {
					nbt.setBoolean(name, (Boolean) object);
				} else if (type == short.class || type == Short.class) {
					nbt.setShort(name, (Short) object);
				} else if (type == int.class || type == Integer.class) {
					nbt.setInteger(name, (Integer) object);
				} else if (type == long.class || type == Long.class) {
					nbt.setLong(name, (Long) object);
				} else if (type == float.class || type == Float.class) {
					nbt.setFloat(name, (Float) object);
				} else if (type == double.class || type == Double.class) {
					nbt.setDouble(name, (Double) object);
				} else if (type == String.class) {
					nbt.setString(name, ((String) object));
				}
			}
		}
	}
	
	public static Object readObject(String name, Class<?> type, NBTTagCompound nbt) {
		if (type.isEnum()) {
			Enum<?> object = null;
			try {
				NBTTagCompound tag = nbt.getCompoundTag(name);
				Class clazz = Class.forName(tag.getString("class"));
				object = Enum.valueOf(clazz, tag.getString("name"));
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			return object;
		} else if (type.isArray()) {
			type = type.getComponentType();

			NBTTagCompound arrayTag = nbt.getCompoundTag(name);
			NBTTagList list = (NBTTagList) arrayTag.getTag("contents");
			Object[] array = new Object[arrayTag.getInteger("size")];

			for (int i=0; i<list.tagCount(); i++) {
				NBTTagCompound tag = list.getCompoundTagAt(i);
				array[tag.getInteger("index")] = NBTHandler.readObject(name + "_" + i, type, tag);
			}

			return array;
		} else {
			for (AbstractSerializer<?> serializer : AbstractSerializer.serializerList) {
				if (serializer.canHandle(type)) {
					return serializer.deserialize(name, nbt);
				}
			}

			// It'll only get here if a deserializer doesn't return anything
			if (type == byte.class || type == Byte.class) {
				return nbt.getByte(name);
			} else if (type == boolean.class || type == Boolean.class) {
				return nbt.getBoolean(name);
			} else if (type == short.class || type == Short.class) {
				return nbt.getShort(name);
			} else if (type == int.class || type == Integer.class) {
				return nbt.getInteger(name);
			} else if (type == long.class || type == Long.class) {
				return nbt.getLong(name);
			} else if (type == float.class || type == Float.class) {
				return nbt.getFloat(name);
			} else if (type == double.class || type == Double.class) {
				return nbt.getDouble(name);
			} else if (type == String.class) {
				return nbt.getString(name);
			}
		}

		return null;
	}
	
	private Object parent;

	private Map<String, Field> fields = Maps.newHashMap();

	public NBTHandler(Object parent, boolean scan) {
		this.parent = parent;

		if (scan) {
			for (Field field : parent.getClass().getDeclaredFields()) {
				if (NBTHandler.validField(field)) {
					fields.put(field.getName(), field);
				}
			}
		}
	}

	public NBTHandler addField(Class<?> clazz, String name) {
		try {
			addField(clazz.getDeclaredField(name));
		} catch (NoSuchFieldException ex) {
			// Throw error
		}
		return this;
	}

	public NBTHandler addField(Field field) {
		if (NBTHandler.validField(field)) {
			fields.put(field.getName(), field);
		}
		return this;
	}

	public String[] getFields() {
		return fields.entrySet().toArray(new String[fields.size()]);
	}

	public void debugWrite() {
		for (String str : fields.keySet()) {
			FMLLog.info(str);
		}
	}

	public void writeAllToNBT(NBTTagCompound nbt) {
		for (String str : fields.keySet()) {
			writeField(str, nbt);
		}
	}

	public void writeSelectedToNBT(String[] names, NBTTagCompound nbt) {
		for (String str : names) {
			writeField(str, nbt);
		}
	}

	public void readFromNBT(NBTTagCompound nbt) {
		for (String str : fields.keySet()) {
			readField(str, nbt);
		}
	}

	public void readSelectedFromNBT(String[] names, NBTTagCompound nbt) {
		for (String str : names) {
			readField(str, nbt);
		}
	}

	private void writeField(String name, NBTTagCompound nbt) {
		Field field = fields.get(name);
		if (field == null) {
			EDXLogger.error("Tried to write field " + name + " from " + parent.getClass() + " but it doesn't exist!");
			return;
		}

		try {
			if (field.get(parent) == null) {
				return;
			}

			NBTHandler.writeObject(name, field.get(parent), nbt);
		} catch (IllegalAccessException ex) {
			EDXLogger.error("Failed to access field " + name + " from " + parent.getClass());
		}
	}

	private void readField(String name, NBTTagCompound nbt) {
		Field field = fields.get(name);
		if (field == null) {
			EDXLogger.error("Tried to read field " + name + " from " + parent.getClass() + " but it doesn't exist!");
			return;
		}

		try {
			if (!nbt.hasKey(name)) {
				if (!field.getType().isArray()) {
					field.set(parent, null);
				}
			}

			if (field.getType().isArray()) {
				ArrayDefault arrayDefault = field.getAnnotation(ArrayDefault.class);
				if (arrayDefault != null) {
					field.set(parent, Array.newInstance(field.getType().getComponentType(), arrayDefault.value()));
				}
				field.set(parent, ArrayHelper.handleGenericArray(NBTHandler.readObject(name, field.getType(), nbt), field.getType().getComponentType()));
			} else {
				field.set(parent, NBTHandler.readObject(name, field.getType(), nbt));
			}
		} catch (IllegalAccessException ex) {
			EDXLogger.error("Failed to access field " + name + " from " + parent.getClass());
		}
	}
}
