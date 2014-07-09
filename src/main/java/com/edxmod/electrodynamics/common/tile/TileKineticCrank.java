package com.edxmod.electrodynamics.common.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;

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

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			for (TileEntity tile : getConnectedTiles()) {
				if (tile != null && tile instanceof TileHammerMill && worldObj.getTotalWorldTime() % (TileWaterMill.MAX_SPEED - speed) == 0) {
					((TileHammerMill)tile).charge++;
				}
			}
		} else {
			for (TileEntity tile : getConnectedTiles()) {
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
				if (list.get(list.size() - 1) instanceof TileHammerMill) {
					foundEnd = true;
					break;
				}

				if (beyond == null || !(beyond instanceof TileHandCrank) && !(beyond instanceof TileKineticCrank)) {
					list.add(tile);
				}
			} else if (tile instanceof TileMetalShaft) {
				list.add(tile);
			} else {
				foundEnd = true;
				break;
			}
		}

		return list;
	}
}
