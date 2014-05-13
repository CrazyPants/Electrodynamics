package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.network.VanillaPacketHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * @author dmillerw
 */
public abstract class TileCore extends TileEntity {

    public static final int DATA_FULL = 0;
    public static final int DATA_POKE = 1;

    public abstract void readCustomNBT(NBTTagCompound nbt);

    public abstract void writeCustomNBT(NBTTagCompound nbt);

    public void poke() {
        VanillaPacketHelper.sendToAllInRange(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 64, new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, DATA_POKE, new NBTTagCompound()));
    }

    public void onPoke() {

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        readCustomNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        writeCustomNBT(nbt);
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, DATA_FULL, nbt);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        switch (pkt.func_148853_f()) {
            case DATA_FULL:
                readFromNBT(pkt.func_148857_g());
                break;
            case DATA_POKE:
                onPoke();
                break;
        }
        getWorldObj().markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
    }

}
