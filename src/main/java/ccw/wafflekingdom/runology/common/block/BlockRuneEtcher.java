/*
  This class was created by <codycoolwaffle>. It's
  distributed as part of the Runology mod. Nearly all
  the code used in this file originates from Botania
  made by <Vazkii>. It has been altered to work for
  the Runology mod.
  
  Check out the corresponding Github's here:
  https://github.com/Vazkii/Botania
  https://github.com/codycoolwaffle/Runology
 */

package ccw.wafflekingdom.runology.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

import ccw.wafflekingdom.runology.common.lib.LibBlockNames;
import ccw.wafflekingdom.runology.common.tile.TileRuneEtcher;

public class BlockRuneEtcher extends BlockMod
{
	
	public BlockRuneEtcher()
	{
		super(Material.ROCK, LibBlockNames.RUNE_ETCHER);
		setHardness(2.0F);
		setResistance(10.0F);
		setSoundType(SoundType.STONE);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
	
	@Nonnull
	@Override
	public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state)
	{
		return new TileRuneEtcher();
	}
}