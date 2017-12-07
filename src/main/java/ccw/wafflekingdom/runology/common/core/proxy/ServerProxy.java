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
package ccw.wafflekingdom.runology.common.core.proxy;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import ccw.wafflekingdom.runology.api.tome.TomeEntry;
import ccw.wafflekingdom.runology.common.lib.LibMisc;

public class ServerProxy implements IProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
	}
	
	@Override
	public void setEntryToOpen(TomeEntry entry)
	{
	
	}
	
	@Override
	public void setTomeStack(ItemStack stack)
	{
	
	}
	
	@Override
	public String getLastVersion()
	{
		return LibMisc.BUILD;
	}
}