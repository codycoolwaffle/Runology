/**
 This class was created by <codycoolwaffle>. It's
 distributed as part of the Runology mod. Nearly all
 the code used in this file originates from Botania
 made by <Vazkii>. It has been altered to work for
 the Runology mod.
 <p>
 Check out the corresponding Github's here:
 https://github.com/Vazkii/Botania
 https://github.com/codycoolwaffle/Runology
 */

package ccw.wafflekingdom.runology.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

import ccw.wafflekingdom.runology.client.lib.LibResources;

public class ItemBlockMod extends ItemBlock
{
	
	public ItemBlockMod(Block block)
	{
		super(block);
	}
	
	@Nonnull
	@Override
	public String getUnlocalizedNameInefficiently(@Nonnull ItemStack stack)
	{
		return getUnlocalizedNameInefficiently_(stack)
				.replaceAll("tile.", "tile." + LibResources.PREFIX_MOD);
	}
	
	public String getUnlocalizedNameInefficiently_(ItemStack stack)
	{
		return super.getUnlocalizedNameInefficiently(stack);
	}
}
