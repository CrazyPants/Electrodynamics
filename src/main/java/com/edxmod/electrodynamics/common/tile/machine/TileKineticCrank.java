package com.edxmod.electrodynamics.common.tile.machine;

import com.edxmod.electrodynamics.common.tile.core.TileCoreMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmillerw
 */
public class TileKineticCrank extends TileCoreMachine {

	private static final int MAX_LENGTH = 8;

	@SideOnly(Side.CLIENT)
	public float angle = 0F;

	public float speed = 0F;

	public boolean stopTick = false;

	private List<TileEntity> connectedTilesCache;

	@Override
	public void updateEntity() {
		if (connectedTilesCache == null || worldObj.getTotalWorldTime() % 20 == 0) {
			connectedTilesCache = getConnectedTiles();
		}

		if (!worldObj.isRemote) {
			for (TileEntity tile : connectedTilesCache) {
				if (tile != null && tile instanceof TileHammerMill && worldObj.getTotalWorldTime() % (TileWaterMill.MAX_SPEED - speed) == 0) {
					((TileHammerMill)tile).charge++;
				}
			}
		} else {
			for (TileEntity tile : connectedTilesCache) {
				if (tile != null) {
					if (tile instanceof TileHammerMill) {
						((TileHammerMill) tile).angle = angle;
					} else if (tile instanceof TileMetalShaft) {
						((TileMetalShaft) tile).angle = angle;
					}
				}
			}
		}
	}

	/** Updates orientation so "forward" is facing away from the water mill */
	public void updateOrientation() {
		ForgeDirection forward = orientation;
		ForgeDirection back = forward.getOpposite();

		TileEntity forwardTile = worldObj.getTileEntity(xCoord + forward.offsetX, yCoord + forward.offsetY, zCoord + forward.offsetZ);
		TileEntity backwardTile = worldObj.getTileEntity(xCoord + back.offsetX, yCoord + back.offsetY, zCoord + back.offsetZ);

		if (forwardTile != null && forwardTile instanceof TileWaterMill) {
			orientation = forward;
		} else if (backwardTile != null && backwardTile instanceof TileWaterMill) {
			orientation = back;
		}
	}

	private List<TileEntity> getConnectedTiles() {
		List<TileEntity> list = new ArrayList<TileEntity>();

		int x = xCoord;
		int y = yCoord;
		int z = zCoord;

		int length = 0;

		boolean foundEnd = false;

		while (!foundEnd) {
			if (length > MAX_LENGTH) {
				foundEnd = true;
				break;
			}

			length++;
			x += orientation.getOpposite().offsetX;
			y += orientation.getOpposite().offsetY;
			z += orientation.getOpposite().offsetZ;

			TileEntity tile = worldObj.getTileEntity(x, y, z);
			TileEntity beyond = worldObj.getTileEntity(x + orientation.getOpposite().offsetX, y + orientation.getOpposite().offsetY, z + orientation.getOpposite().offsetZ);

			if (tile instanceof TileHammerMill) {
				// Don't let hammer mills get strung together
				if (list.size() > 0 && list.get(list.size() - 1) instanceof TileHammerMill) {
					foundEnd = true;
					break;
				}

				if (beyond == null || !(beyond instanceof TileHandCrank) && !(beyond instanceof TileKineticCrank)) {
					list.add(tile);
				}
			} else if (tile instanceof TileMetalShaft) {
				if (((TileMetalShaft)tile).orientation != orientation.getOpposite().getRotation(ForgeDirection.UP)) {
					((TileMetalShaft)tile).orientation = orientation.getOpposite().getRotation(ForgeDirection.UP);
					((TileMetalShaft)tile).markForUpdate();
				}

				list.add(tile);
			} else {
				foundEnd = true;
				break;
			}
		}

		return list;
	}
}
