package com.edxmod.electrodynamics.common.tile.nbt;

import com.edxmod.electrodynamics.common.tile.nbt.data.AbstractSerializer;
import com.google.common.collect.Maps;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
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
	@Inherited
	public static @interface NBTData {

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
		for (Object obj : nbt.func_150296_c()) {
			// It's a string, I know it is :|
			String str = obj.toString();

			readField(str, nbt);
		}
	}

	private void writeField(String name, NBTTagCompound nbt) {
		// If the field is a primitive, it gets a proper NBTBase tag, otherwise the registered serializer handles things

		Field field = fields.get(name);
		if (field == null) {
			// Throw error
			return;
		}

		try {
			if (field.get(parent) == null) {
				return;
			}

			if (!field.getType().isEnum() && field.getType().getName().equalsIgnoreCase("String") || field.getType().isPrimitive()) {
				String type = field.getType().getName();

				if (type.equalsIgnoreCase("byte")) {
					nbt.setByte(name, field.getByte(parent));
				} else if (type.equalsIgnoreCase("boolean")) {
					nbt.setBoolean(name, field.getBoolean(parent));
				} else if (type.equalsIgnoreCase("short")) {
					nbt.setShort(name, field.getShort(parent));
				} else if (type.equalsIgnoreCase("int")) {
					nbt.setInteger(name, field.getInt(parent));
				} else if (type.equalsIgnoreCase("long")) {
					nbt.setLong(name, field.getLong(parent));
				} else if (type.equalsIgnoreCase("float")) {
					nbt.setFloat(name, field.getFloat(parent));
				} else if (type.equalsIgnoreCase("double")) {
					nbt.setDouble(name, field.getDouble(parent));
				} else if (type.equalsIgnoreCase("String")) {
					nbt.setString(name, field.get(parent).toString());
				}
			} else {
				// Not a primitive or a String, so we switch to defined serializers :D

				for (AbstractSerializer<?> serializer : AbstractSerializer.serializerList) {
					if (serializer.canHandle(field)) {
						serializer.serialize(name, field.get(parent), nbt);
						break;
					}
				}
			}
		} catch (IllegalAccessException ex) {
			// Throw error
		}
	}

	private void readField(String name, NBTTagCompound nbt) {
		Field field = fields.get(name);
		if (field == null) {
			// Throw error
			return;
		}

		try {
			if (!nbt.hasKey(name)) {
				field.set(parent, null);
				return;
			}

			if (!field.getType().isEnum() && field.getType().getName().equalsIgnoreCase("String") || field.getType().isPrimitive()) {
				String type = field.getType().getName();

				if (type.equalsIgnoreCase("byte")) {
					field.setByte(parent, nbt.getByte(name));
				} else if (type.equalsIgnoreCase("boolean")) {
					field.setBoolean(parent, nbt.getBoolean(name));
				} else if (type.equalsIgnoreCase("short")) {
					field.setShort(parent, nbt.getShort(name));
				} else if (type.equalsIgnoreCase("int")) {
					field.setInt(parent, nbt.getInteger(name));
				} else if (type.equalsIgnoreCase("long")) {
					field.setLong(parent, nbt.getLong(name));
				} else if (type.equalsIgnoreCase("float")) {
					field.setFloat(parent, nbt.getFloat(name));
				} else if (type.equalsIgnoreCase("double")) {
					field.setDouble(parent, nbt.getDouble(name));
				} else if (type.equalsIgnoreCase("String")) {
					field.set(parent, nbt.getString(name));
				}
			} else {
				// Not a primitive or a String, so we switch to defined serializers :D

				for (AbstractSerializer<?> serializer : AbstractSerializer.serializerList) {
					if (serializer.canHandle(field)) {
						field.set(parent, serializer.deserialize(name, nbt));
						break;
					}
				}
			}
		} catch (IllegalAccessException ex) {
			// Throw error
		}
	}
}
