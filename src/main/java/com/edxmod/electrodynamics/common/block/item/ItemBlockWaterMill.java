package com.edxmod.electrodynamics.common.block.item;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.block.prefab.item.EDXItemBlock;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.tile.TileCrank;
import com.edxmod.electrodynamics.common.tile.TileHammerMill;
import com.edxmod.electrodynamics.common.tile.TileKineticCrank;
import com.edxmod.electrodynamics.common.tile.TileWaterMill;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author dmillerw
 */
public class ItemBlockWaterMill extends EDXItemBlock {

	private IIcon icon;

	public ItemBlockWaterMill(Block block) {
		super(block);
	}

	@Override
	public int getSpriteNumber() {
		return 1;
	}

	@Override
	public IIcon getIconFromDamage(int par1) {
		return icon;
	}

	@Override
	public void registerIcons(IIconRegister register) {
		icon = register.registerIcon(EDXProps.RESOURCE_PREFIX + "tools/hammerCrank");
	}

	@Override
	public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
		return true;
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		if (side == 0 || side == 1) {
			return false;
		}

		ForgeDirection opposite = ForgeDirection.getOrientation(side).getOpposite();

		if (world.getBlock(x + opposite.offsetX, y + opposite.offsetY, z + opposite.offsetZ) != EDXBlocks.kineticCrank) {
			return false;
		}

		TileKineticCrank tile = (TileKineticCrank) world.getTileEntity(x + opposite.offsetX, y + opposite.offsetY, z + opposite.offsetZ);

		// Prevents the mill from being placed on the front/back of the block
		if (!(opposite == tile.orientation || opposite == tile.orientation.getOpposite())) {
			return false;
		}

		boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);

		if (result) {
			TileWaterMill mill = (TileWaterMill) world.getTileEntity(x, y, z);
			mill.orientation = ForgeDirection.getOrientation(side).getOpposite();
		}

		return result;
	}
}
