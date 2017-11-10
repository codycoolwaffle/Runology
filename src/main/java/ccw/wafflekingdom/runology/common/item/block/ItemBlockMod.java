/*
  Nearly all the code used in this file originates
  from Botania made by <Vazkii>. It has been altered
  to work for, and distributed as part of,
  the Runology mod created by <codycoolwaffle>.
  
  Runology is Open Source and distributed under a
  Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
  http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_US
  
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
