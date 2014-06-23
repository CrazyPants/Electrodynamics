package com.edxmod.electrodynamics.common.tile.nbt.data;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Field;

/**
 * @author dmillerw
 */
public class EnumSerializer extends AbstractSerializer<Enum<?>> {

	@Override
	public boolean canHandle(Field field) {
		return field.getType().isEnum();
	}

	@Override
	public void serialize(String name, Object object, NBTTagCompound nbt) {
		Enum<?> instance = (Enum<?>) object;
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("class", instance.getClass().getCanonicalName());
		tag.setString("name", instance.name());
		nbt.setTag(name, tag);
	}

	@Override
	public Enum<?> deserialize(String name, NBTTagCompound nbt) {
		Enum<?> object = null;
		try {
			NBTTagCompound tag = nbt.getCompoundTag(name);
			Class clazz = Class.forName(tag.getString("class"));
			object = Enum.valueOf(clazz, tag.getString("name"));
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return object;
	}
}
