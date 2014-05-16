package com.edxmod.electrodynamics.common.core.handler;

import com.edxmod.electrodynamics.common.block.world.BlockOre;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.IIcon;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author dmillerw
 */
@SideOnly(Side.CLIENT)
public class DynamicOreCache {

    public static final DynamicOreCache INSTANCE = new DynamicOreCache();

    private Map<WorldPoint, IIcon> iconCache = new HashMap<WorldPoint, IIcon>();

    public static class WorldPoint {

        public final int x;
        public final int y;
        public final int z;

        public WorldPoint(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            WorldPoint that = (WorldPoint) o;

            if (x != that.x)
                return false;
            if (y != that.y)
                return false;
            if (z != that.z)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + z;
            return result;
        }
    }

    public void cache(int x, int y, int z, IIcon icon) {
        cache(new WorldPoint(x, y, z), icon);
    }

    public void cache(WorldPoint point, IIcon icon) {
        iconCache.put(point, icon);
    }

    public void remove(int x, int y, int z) {
        remove(new WorldPoint(x, y, z));
    }

    public void remove(WorldPoint point) {
        iconCache.remove(point);
    }

    public IIcon get(int x, int y, int z) {
        return get(new WorldPoint(x, y, z));
    }

    public IIcon get(WorldPoint point) {
        return iconCache.get(point);
    }

    public boolean contains(int x, int y, int z) {
        return contains(new WorldPoint(x, y, z));
    }

    public boolean contains(WorldPoint point) {
        return iconCache.containsKey(point);
    }

    /* EVENT HANDLERS */
    @SubscribeEvent
    public void onDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        iconCache.clear();
    }

    @SubscribeEvent
    public void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        iconCache.clear();
    }

    /* TICK HANDLING */
    @SubscribeEvent
    public void onClientPostTick(TickEvent event) {
        // At the end of every client tick, we clear out texture caches that no longer exist
        if (event.type == TickEvent.Type.CLIENT && event.phase == TickEvent.Phase.END) {
            Iterator<WorldPoint> iterator = iconCache.keySet().iterator();

            while (iterator.hasNext()) {
                WorldPoint point = iterator.next();

                Minecraft minecraft = Minecraft.getMinecraft();
                if (minecraft == null)
                    continue;
                WorldClient world = minecraft.theWorld;
                if (world == null)
                    continue;

                if (!(world.getBlock(point.x, point.y, point.z) instanceof BlockOre)) {
                    iterator.remove();
                }
            }
        }
    }

}
