package com.edxmod.electrodynamics.common.network;

import com.edxmod.electrodynamics.common.lib.client.EnumParticle;
import com.edxmod.electrodynamics.common.util.UtilItem;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class PacketFX implements IMessage, IMessageHandler<PacketFX, IMessage> {

    public static void breakFX(World world, int x, int y, int z, ItemStack stack) {
        PacketFX packet = new PacketFX(x, y, z, stack);
		PacketHandler.INSTANCE.sendToAllAround(packet, new NetworkRegistry.TargetPoint(world.provider.dimensionId, x, y, z, MAX_PARTICLE_RANGE));
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
	public void toBytes(ByteBuf buffer) {
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
	public void fromBytes(ByteBuf buffer) {
		x = buffer.readDouble();
		y = buffer.readDouble();
		z = buffer.readDouble();
		fxType = buffer.readInt();

		extraData = new int[buffer.readInt()];
		for (int i = 0; i < extraData.length; i++) {
			extraData[i] = buffer.readInt();
		}
	}

	@Override
	public IMessage onMessage(PacketFX message, MessageContext ctx) {
		Minecraft mc = FMLClientHandler.instance().getClient();
		switch (fxType) {
			case FX_PARTICLE: {
				EnumParticle particle = EnumParticle.values()[extraData[0]];
				particle.display(mc.theWorld, x, y, z, 0, 0, 0);
			}

			case FX_BLOCK_BREAK: {
				if (extraData[0] > 0 && extraData[0] < 4096) {
					mc.effectRenderer.addBlockDestroyEffects((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z), Block.getBlockById(extraData[0]), extraData[1]);
				} else {
					EnumParticle.ITEM_BREAK(new ItemStack(Item.getItemById(extraData[0]), extraData[1]), FMLClientHandler.instance().getWorldClient(), x, y, z);
				}
			}
		}
		return null;
	}
}