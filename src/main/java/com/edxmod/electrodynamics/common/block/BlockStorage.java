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

    public static final String[] NAMES = new String[]{"copper", "lead", "nickel", "silver", "steel", "tin"};
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
        textures = new IIcon[NAMES.length];
        for (int i = 0; i < NAMES.length; i++) {
            textures[i] = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "ore/storage/" + NAMES[i]);
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
        return ArrayHelper.getArrayIndexes(NAMES.length);
    }

    @Override
    public String getNameForType(int type) {
        return NAMES[type];
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }
}
