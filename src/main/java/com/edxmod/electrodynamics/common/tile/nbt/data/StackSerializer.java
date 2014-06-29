package com.edxmod.electrodynamics.common.tile.nbt.data;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Field;

/**
 * @author dmillerw
 */
public class StackSerializer extends AbstractSerializer<ItemStack> {

	@Override
	public boolean canHandle(Field field) {
		return field.getType() == ItemStack.class;
	}

	@Override
	public void serialize(String name, Object object, NBTTagCompound nbt) {
		NBTTagCompound tag = new NBTTagCompound();
		((ItemStack)object).writeToNBT(tag);
		nbt.setTag(name, tag);
	}

	@Override
	public ItemStack deserialize(String name, NBTTagCompound nbt) {
		return ItemStack.loadItemStackFromNBT(nbt.getCompoundTag(name));
	}
}
