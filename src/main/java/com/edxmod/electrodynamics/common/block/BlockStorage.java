package com.edxmod.electrodynamics.common.block;

import com.edxmod.electrodynamics.common.block.prefab.EDXMultiBlock;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.util.ArrayHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * @author Royalixor
 */
public class BlockStorage extends EDXMultiBlock {

    public static final String[] storageTypes = new String[]{"cobaltStorage", "copperStorage", "leadStorage", "loadstoneStorage", "nickelStorage", "telluriumStorage", "tungstenStorage"};
    private IIcon[] textures;

    public BlockStorage() {
        super(Material.iron);
        setCreativeTab(EDXCreativeTab.BLOCKS.get());
        setStepSound(Block.soundTypeMetal);
        setHardness(4F);
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public boolean useCustomRender() {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        textures = new IIcon[storageTypes.length];
        for (int i = 0; i < storageTypes.length; i++) {
            textures[i] = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "misc/" + storageTypes[i]);
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (meta < 0 || meta >= textures.length) {
            meta = 0;
        }
        return textures[meta];
    }

    @Override
    public int[] getSubtypes() {
        return ArrayHelper.getArrayIndexes(storageTypes.length);
    }

    @Override
    public String getNameForType(int type) {
        return storageTypes[type];
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }
}
