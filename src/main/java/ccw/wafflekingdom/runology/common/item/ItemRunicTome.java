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

package ccw.wafflekingdom.runology.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import ccw.wafflekingdom.runology.common.lib.LibItemNames;

public class ItemRunicTome extends ItemMod
{
	public ItemRunicTome()
	{
		super(LibItemNames.RUNIC_TOME);
		setMaxStackSize(1);
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