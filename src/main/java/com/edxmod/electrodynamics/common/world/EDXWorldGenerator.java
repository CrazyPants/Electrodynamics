package com.edxmod.electrodynamics.common.world;

import com.edxmod.electrodynamics.common.world.gen.feature.GenLimestone;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Royalixor.
 */
public class EDXWorldGenerator {

    public static void init() {
        GameRegistry.registerWorldGenerator(new GenLimestone(), 1);
    }
}
