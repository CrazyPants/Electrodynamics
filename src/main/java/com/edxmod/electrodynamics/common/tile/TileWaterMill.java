package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author dmillerw
 */
public class TileWaterMill extends TileCoreMachine {

	public static final float MAX_SPEED = 10F;

	private float lastSpeed = 0F;

	public float speed = 0F;

	@SideOnly(Side.CLIENT)
	private float clientSpeed = 0F;

	@SideOnly(Side.CLIENT)
	public float angle = 0F;

	public boolean flowLeft;

	@Override
	public void onClientUpdate(NBTTagCompound nbt) {
		if (nbt.hasKey("speed")) {
			speed = nbt.getFloat("speed");
			flowLeft = nbt.getBoolean("flowLeft");
			if (nbt.getBoolean("force")) {
				clientSpeed = speed;
			}
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

			TileKineticCrank crank = (TileKineticCrank) worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord, zCoord + orientation.offsetZ);

			if (crank != null) {
				crank.angle = angle;
				crank.flowLeft = flowLeft;
			}
		}
	}

	public void updateSpeed() {
		// Basic for now, may change later
		// Basically, we check for the number of FLOWING water blocks in the 3x3 PERIMATER of the mill
		// and use that to determine the current speed

		//TODO Rotate based on flow direction?

		TileKineticCrank crank = (TileKineticCrank) worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord, zCoord + orientation.offsetZ);
		ForgeDirection right = orientation.getRotation(ForgeDirection.UP).getOpposite();
		boolean xAxis = right.offsetX != 0;

		int count = 0;
		crank.stopTick = false;
		for (int ix=-1; ix<2; ix++) {
			for (int iy=-1; iy<2; iy++) {
				for (int iz=-1; iz<2; iz++) {
					int sx = xAxis ? xCoord + ix : xCoord;
					int sy = yCoord + iy;
					int sz = xAxis ? zCoord : zCoord + iz;

					if (sy != 0 && (xAxis ? (iz == -1) : (ix == -1))) {
						Block block = worldObj.getBlock(sx, sy, sz);
						int meta = worldObj.getBlockMetadata(sx, sy, sz);

						if ((block == Blocks.water || block == Blocks.flowing_water)) {
							// Is the block flowing on the ground?
							Block below = worldObj.getBlock(sx, sy - 1, sz);

							if (!below.isAir(worldObj, sx, sy, sz) && block != Blocks.water && block != Blocks.flowing_water) {
								// It's flowing on something solid
								count++;
							} else {
								double flow = ((BlockLiquid)block).getFlowDirection(worldObj, sx, sy, sz, Material.water);
								switch (orientation) {
									case NORTH:
										if (flow >= 0) {
										   flowLeft = true;
									   } else {
										   flowLeft = false;
									   }
									   break;
								   case SOUTH:
									   if (flow >= 0) {
										   flowLeft = false;
									   } else {
										   flowLeft = true;
									   }
									   break;
								   case WEST:
									   if (flow >= 0) {
										   flowLeft = true;
									   } else {
										   flowLeft = false;
									   }
									   break;
								   case EAST:
									   if (flow >= 0) {
										   flowLeft = false;
									   } else {
										   flowLeft = true;
									   }
									   break;
								}
								count += 2;
							}
						} else {
							if (block != null && block != EDXBlocks.waterMill && !block.isAir(worldObj, sx, sy, sz)) {
								crank.stopTick = true;
								count = 0;
								break;
							}
						}
					}
				}
			}
		}

		speed = MAX_SPEED * ((float)count / (float)8);
		crank.speed = speed;

		if (lastSpeed != speed) {
			if (lastSpeed > 0 && speed <= 0) {
				crank.stopTick = true;
			}
			lastSpeed = speed;

			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setBoolean("force", crank.stopTick);
			nbt.setBoolean("flowLeft", flowLeft);
			nbt.setFloat("speed", speed);
			sendClientUpdate(nbt);
		}
	}
}
