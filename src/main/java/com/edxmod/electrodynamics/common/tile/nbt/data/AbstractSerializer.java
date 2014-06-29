package com.edxmod.electrodynamics.common.tile.nbt.data;

import com.google.common.collect.Lists;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author dmillerw
 */
public abstract class AbstractSerializer<T> {

	public static List<AbstractSerializer<?>> serializerList = Lists.newArrayList();

	public static void initialize() {
		serializerList.add(new EnumSerializer());
		serializerList.add(new StackSerializer());
		serializerList.add(new ArraySerializer.ArrayItemStack());
	}

	public abstract boolean canHandle(Field field);

	public abstract void serialize(String name, Object object, NBTTagCompound nbt);

	public abstract T deserialize(String name, NBTTagCompound nbt);
}
