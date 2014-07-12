package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.network.VanillaPacketHelper;
import com.edxmod.electrodynamics.common.tile.nbt.NBTHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * @author dmillerw
 */
public abstract class TileCore extends TileEntity {

	private static final int BLANKET_DESCRIPTION_PACKET = 0;
	private static final int SPECIFIC_DESCRIPTION_PACKET = 1;
	private static final int POKE_PACKET = 2;

	public void writeCustomNBT(NBTTagCompound nbt) {}

	public void readCustomNBT(NBTTagCompound nbt) {}

	public String[] descriptionPacketFields() {
		return handler.getFields();
	}

	public boolean blanketDescriptionPacket() {
		return true;
	}

	public void onPoked() {}

	public void onBlockBroken() {}

	protected NBTHandler handler;

	public TileCore() {
		this(true);
	}

	public TileCore(boolean scan) {
		handler = new NBTHandler(this, scan);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		handler.writeAllToNBT(nbt);
		writeCustomNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		handler.readFromNBT(nbt);
		readCustomNBT(nbt);
	}

	private void writeDescriptionPacket(NBTTagCompound nbt) {
		if (blanketDescriptionPacket()) {
			writeToNBT(nbt);
		} else {
			super.writeToNBT(nbt);
			handler.writeSelectedToNBT(descriptionPacketFields(), nbt);
			writeCustomNBT(nbt);
		}
	}

	private void readDescriptionPacket(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		handler.readSelectedFromNBT(descriptionPacketFields(), nbt);
		readCustomNBT(nbt);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeDescriptionPacket(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, blanketDescriptionPacket() ? BLANKET_DESCRIPTION_PACKET : SPECIFIC_DESCRIPTION_PACKET, tag);
	}

	public void markForUpdate() {
		if (hasWorldObj()) {
			getWorldObj().markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	public void markForRenderUpdate() {
		if (hasWorldObj()) {
			getWorldObj().markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
		}
	}

	public void updateNeighbors() {
		if (hasWorldObj()) {
			getWorldObj().notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType());
		}
	}

	public void sendPoke() {
		VanillaPacketHelper.sendToAllWatchingTile(this, new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, POKE_PACKET, new NBTTagCompound()));
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		switch(pkt.func_148853_f()) {
			case BLANKET_DESCRIPTION_PACKET:
				readFromNBT(pkt.func_148857_g());
				break;
			case SPECIFIC_DESCRIPTION_PACKET:
				readDescriptionPacket(pkt.func_148857_g());
				break;
			case POKE_PACKET:
				onPoked();
				break;
			default:
				break;
		}
		markForRenderUpdate();
	}
}
