package com.edxmod.electrodynamics.common.tile.nbt.data;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * @author dmillerw
 */
public class ArraySerializer {

	public static class ArrayItemStack extends AbstractSerializer<ItemStack[]> {
		@Override
		public boolean canHandle(Field field) {
			return field.getType().isArray() && field.getType() == ItemStack[].class;
		}

		@Override
		public void serialize(String name, Object object, NBTTagCompound nbt) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("size", Array.getLength(object));
			NBTTagList list = new NBTTagList();
			for (int i=0; i< Array.getLength(object); i++) {
				ItemStack stack = (ItemStack) Array.get(object, i);
				if (stack == null) {
					NBTTagCompound nullTag = new NBTTagCompound();
					nullTag.setBoolean("null", true);
					list.appendTag(nullTag);
				} else {
					NBTTagCompound stackTag = new NBTTagCompound();
					stack.writeToNBT(stackTag);
					list.appendTag(stackTag);
				}
			}
			tag.setTag("contents", list);
			nbt.setTag(name, tag);
		}

		@Override
		public ItemStack[] deserialize(String name, NBTTagCompound nbt) {
			NBTTagCompound tag = nbt.getCompoundTag(name);
			ItemStack[] array = new ItemStack[nbt.getInteger("size")];
			NBTTagList list = tag.getTagList("contents", Constants.NBT.TAG_COMPOUND);
			for (int i=0; i<array.length; i++) {
				NBTTagCompound stackTag = list.getCompoundTagAt(i);
				if (!stackTag.hasKey("null")) {
					array[i] = ItemStack.loadItemStackFromNBT(stackTag);
				}
			}
			return array;
		}
	}
}
