package com.edxmod.electrodynamics.common.block.prefab;

import com.edxmod.electrodynamics.common.tabs.EDXCreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * @author Royalixor.
 */
public abstract class EDXMultiBlock extends Block {

    public EDXMultiBlock(Material material) {
        super(material);
        this.setCreativeTab(EDXCreativeTab.BLOCKS.get());
    }

    public EDXMultiBlock() {
        this(Material.iron);
    }

    public EDXMultiBlock(Material material, float hardness, float resistance) {
        this(material);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    public abstract int[] getSubtypes();

    public abstract String getNameForType(int type);

    @Override
    public void getSubBlocks(Item block, CreativeTabs tab, List list) {
        if (getSubtypes() == null || getSubtypes().length == 0) {
            list.add(new ItemStack(this, 1, 0));
        } else {
            for (int type : getSubtypes()) {
                list.add(new ItemStack(this, 1, type));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public abstract boolean useCustomRender();

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isOpaqueCube() {
        return !this.useCustomRender();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean renderAsNormalBlock() {
        return !this.useCustomRender();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType() {
        return this.useCustomRender() ? -1 : 0;
    }
}
