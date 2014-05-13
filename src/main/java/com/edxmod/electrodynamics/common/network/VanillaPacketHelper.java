package com.edxmod.electrodynamics.common.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;

/**
 * @author dmillerw
 */
public class VanillaPacketHelper {

    public static void sendToAllInRange(int dimension, int x, int y, int z, int range, Packet packet) {
        ServerConfigurationManager manager = MinecraftServer.getServer().getConfigurationManager();

        for (Object obj : manager.playerEntityList) {
            EntityPlayerMP player = (EntityPlayerMP) obj;

            if (player.getEntityWorld().provider.dimensionId == dimension && player.getDistance(x, y, z) <= range) {
                player.playerNetServerHandler.sendPacket(packet);
            }
        }
    }

}
