package com.edxmod.electrodynamics.common.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author dmillerw
 */
public class TileWaterMill extends TileCoreMachine {

	private float lastSpeed = 0F;

	public float speed = 0F;

	@SideOnly(Side.CLIENT)
	private float clientSpeed = 0F;

	@SideOnly(Side.CLIENT)
	public float angle = 0F;

	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {

	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {

	}

	@Override
	public void onClientUpdate(NBTTagCompound nbt) {
		if (nbt.hasKey("speed")) {
			speed = nbt.getFloat("speed");
		}
	}

	@Override
	public void updateEntity() {
		// Roughly two times per second
		if (!worldObj.isRemote && worldObj.getTotalWorldTime() % 10 == 0) {
			updateSpeed();
		} else if (worldObj.isRemote) {
			if (clientSpeed > speed) {
				clientSpeed--;
			} else if (clientSpeed < speed) {
				clientSpeed++;
			}

			angle += clientSpeed;
			if (angle > 360F) {
				angle = angle - 360F;
			}
		}
	}

	public void updateSpeed() {
		// Basic for now, may change later
		// Basically, we check for the number of FLOWING water blocks in the 3x3 PERIMATER of the mill
		// and use that to determine the current speed

		//TODO Rotate based on flow direction?

		ForgeDirection right = orientation.getRotation(ForgeDirection.UP).getOpposite();
		boolean xAxis = right.offsetX != 0;
		boolean reverse = (xAxis ? right.offsetX < 0 : right.offsetY < 0);

		int count = 0;
		for (int ix=-1; ix<2; ix++) {
			for (int iy=-1; iy<2; iy++) {
				for (int iz=-1; iz<2; iz++) {
					int sx = xAxis ? xCoord + ix : xCoord;
					int sy = yCoord + iy;
					int sz = xAxis ? zCoord : zCoord + iz;

					if (sy != 0 && (xAxis ? (iz == -1) : (ix == -1))) {
						Block block = worldObj.getBlock(sx, sy, sz);
						int meta = worldObj.getBlockMetadata(sx, sy, sz);

						if ((block == Blocks.water || block == Blocks.flowing_water) && meta > 0) {
							count++;
						}
					}
				}
			}
		}

		speed = 10F * ((float)count / (float)8);

		if (lastSpeed != speed) {
			lastSpeed = speed;

			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setFloat("speed", speed);
			sendClientUpdate(nbt);
		}
	}
}
