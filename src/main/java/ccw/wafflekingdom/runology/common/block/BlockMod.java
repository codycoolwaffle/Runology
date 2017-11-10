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
package ccw.wafflekingdom.runology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import ccw.wafflekingdom.runology.client.core.handler.ModelHandeler;
import ccw.wafflekingdom.runology.client.render.IModelRegister;
import ccw.wafflekingdom.runology.common.core.RunologyCreativeTab;
import ccw.wafflekingdom.runology.common.lib.LibMisc;

public abstract class BlockMod extends Block implements IModelRegister
{
	public BlockMod(Material blockMaterialIn, String name)
	{
		super(blockMaterialIn);
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(LibMisc.MOD_ID, name));
		if(registerInCreative())
		{
			setCreativeTab(RunologyCreativeTab.INSTANCE);
		}
	}
	
	protected boolean registerInCreative()
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels()
	{
		if(Item.getItemFromBlock(this) != Items.AIR)
		{
			ModelHandeler.registerBlockToState(this, 0, getDefaultState());
		}
	}
}