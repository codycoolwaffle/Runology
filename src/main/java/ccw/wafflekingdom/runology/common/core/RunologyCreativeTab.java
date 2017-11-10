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
package ccw.wafflekingdom.runology.common.core;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

import ccw.wafflekingdom.runology.common.Runology;
import ccw.wafflekingdom.runology.common.block.ModBlocks;
import ccw.wafflekingdom.runology.common.core.handler.ConfigHandler;
import ccw.wafflekingdom.runology.common.item.ModItems;
import ccw.wafflekingdom.runology.common.lib.LibMisc;

public final class RunologyCreativeTab extends CreativeTabs
{
	public static final RunologyCreativeTab INSTANCE = new RunologyCreativeTab();
	NonNullList<ItemStack> list;
	
	public RunologyCreativeTab()
	{
		super(LibMisc.MOD_ID);
		Runology.LOGGER.info("Creating new creative tab!");
		setNoTitle();
		//setBackgroundImageName(LibResources.GUI_CREATIVE);
	}
	
	@Override
	public ItemStack getTabIconItem()
	{
		return getIconItemStack();
	}
	
	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(ModItems.runicTome);
	}
	
	@Override
	public boolean hasSearchBar()
	{
		return true;
	}
	
	@Override
	public void displayAllRelevantItems(@Nonnull NonNullList<ItemStack> list)
	{
		this.list = list;
		
		addBlock(ModBlocks.runeEtcher);
		addItem(ModItems.runicTome);
		
		if(ConfigHandler.printDebugMessages)
		{
			Runology.LOGGER.info("Creative tab items:");
			for(ItemStack s : this.list)
			{
				Runology.LOGGER.info(s);
			}
		}
	}
	
	private void addItem(Item item)
	{
		item.getSubItems(this, list);
	}
	
	private void addBlock(Block block)
	{
		ItemStack stack = new ItemStack(block);
		block.getSubBlocks(this, list);
	}
}