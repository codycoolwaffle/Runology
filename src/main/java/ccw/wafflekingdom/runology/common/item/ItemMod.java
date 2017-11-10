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
package ccw.wafflekingdom.runology.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

import ccw.wafflekingdom.runology.client.lib.LibResources;
import ccw.wafflekingdom.runology.client.render.IModelRegister;
import ccw.wafflekingdom.runology.common.core.RunologyCreativeTab;
import ccw.wafflekingdom.runology.common.lib.LibMisc;

public abstract class ItemMod extends Item implements IModelRegister
{
	public ItemMod(String name)
	{
		setCreativeTab(RunologyCreativeTab.INSTANCE);
		setRegistryName(new ResourceLocation(LibMisc.MOD_ID, name));
		setUnlocalizedName(name);
	}
	
	@Nonnull
	@Override
	public String getUnlocalizedNameInefficiently(@Nonnull ItemStack par1ItemStack)
	{
		return super.getUnlocalizedNameInefficiently(par1ItemStack)
		            .replaceAll("item\\.", "item." + LibResources.PREFIX_MOD);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels()
	{
		ModelLoader.setCustomModelResourceLocation(this, 0,
		                                           new ModelResourceLocation(getRegistryName(),
		                                                                     "inventory"));
	}
}