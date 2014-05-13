package com.edxmod.electrodynamics.common.recipe;

import com.edxmod.electrodynamics.api.EDXBlockHelper;
import com.edxmod.electrodynamics.api.EDXItemHelper;
import com.edxmod.electrodynamics.common.recipe.generic.GenericManager;
import net.minecraft.init.Blocks;

/**
 * @author dmillerw
 */
public class RecipeManager {

    public static final RecipeManager INSTANCE = new RecipeManager();

    public void init() {
        // TABLE
        // Cobaltite
        table.register(EDXBlockHelper.get("ore", 1), EDXItemHelper.get("grindings", 2));

        // Chalcopyrite
        table.register(EDXBlockHelper.get("ore", 0), EDXItemHelper.get("grindings", 3));

        // Galena
        table.register(EDXBlockHelper.get("ore", 2), EDXItemHelper.get("grindings", 4));

        // Graphite
        table.register(EDXBlockHelper.get("ore", 3), EDXItemHelper.get("grindings", 5));

        // Magnetite
        table.register(EDXBlockHelper.get("ore", 4), EDXItemHelper.get("grindings", 6));

        // Nickel
        table.register(EDXBlockHelper.get("ore", 5), EDXItemHelper.get("grindings", 7));

        // Wolframite
        table.register(EDXBlockHelper.get("ore", 6), EDXItemHelper.get("grindings", 8));

        // Stone -> Gravel
        table.register(Blocks.stone, Blocks.gravel);

        // Gravel -> Sand
        table.register(Blocks.gravel, Blocks.sand);

        //KILN
        // Limestone -> Scorched Limestone
        kiln.register(EDXBlockHelper.get("limestone", 0), EDXBlockHelper.get("limestone", 3));
    }

    public GenericManager table = new GenericManager();
    public GenericManager kiln = new GenericManager();

}
