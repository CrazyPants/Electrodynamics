package com.edxmod.electrodynamics.common.network;

import com.edxmod.electrodynamics.Electrodynamics;
import com.edxmod.electrodynamics.common.lib.client.EnumParticle;
import com.edxmod.electrodynamics.common.util.UtilItem;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class PacketFX extends AbstractPacket {

    public static void breakFX(World world, int x, int y, int z, ItemStack stack) {
        PacketFX packet = new PacketFX(x, y, z, stack);
        Electrodynamics.packetPipeline.sendToAllAround(packet, new NetworkRegistry.TargetPoint(world.provider.dimensionId, x, y, z, MAX_PARTICLE_RANGE));
    }

    public static final int MAX_PARTICLE_RANGE = 64;

    public static final int FX_PARTICLE = 0;
    public static final int FX_BLOCK_BREAK = 1;

    public double x;
    public double y;
    public double z;

    public int fxType;

    public int[] extraData;

    public PacketFX() {

    }

    public PacketFX(double x, double y, double z, int fxType, int... extraData) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.fxType = fxType;
        this.extraData = extraData;
    }

    public PacketFX(double x, double y, double z, EnumParticle particle) {
        this(x, y, z, FX_PARTICLE, new int[]{particle.ordinal()});
    }

    public PacketFX(double x, double y, double z, ItemStack stack) {
        this(x, y, z, FX_BLOCK_BREAK, new int[]{UtilItem.getID(stack), stack.getItemDamage()});
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        buffer.writeInt(fxType);

        buffer.writeInt(extraData.length);
        for (int i : extraData) {
            buffer.writeInt(i);
        }
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        x = buffer.readDouble();
        y = buffer.readDouble();
        z = buffer.readDouble();
        fxType = buffer.readInt();

        extraData = new int[buffer.readInt()];
        for (int i = 0; i < extraData.length; i++) {
            extraData[i] = buffer.readInt();
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer player) {
        switch (fxType) {
            // Particle
            case 0: {
                EnumParticle particle = EnumParticle.values()[extraData[0]];
                particle.display(FMLClientHandler.instance().getClient().theWorld, x, y, z, 0, 0, 0);
            }

            // Block destroy FX
            case 1: {
                if (extraData[0] > 0 && extraData[0] < 4096) {
                    FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z), UtilItem.getBlock(extraData[0]), extraData[1]);
                } else {
                    EnumParticle.ITEM_BREAK(new ItemStack(UtilItem.getItem(extraData[0]), extraData[1]), FMLClientHandler.instance().getWorldClient(), x, y, z);
                }
            }
        }
    }

    @SideOnly(Side.SERVER)
    @Override
    public void handleServerSide(EntityPlayer player) {
        // NOPE
    }

}
