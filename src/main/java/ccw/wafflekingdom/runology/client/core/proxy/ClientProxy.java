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
package ccw.wafflekingdom.runology.client.core.proxy;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import ccw.wafflekingdom.runology.api.tome.TomeEntry;
import ccw.wafflekingdom.runology.client.gui.tome.GuiTome;
import ccw.wafflekingdom.runology.client.gui.tome.GuiTomeEntry;
import ccw.wafflekingdom.runology.client.gui.tome.GuiTomeIndex;
import ccw.wafflekingdom.runology.common.core.proxy.IProxy;

public class ClientProxy implements IProxy
{
	//public static boolean jingleTheBells = false;//TODO add holiday stuff
	
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		initRenderers();
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		//if(ConfigHandler.useAdaptativeConfig)
		//	MinecraftForge.EVENT_BUS.register(AdaptorNotifier.class);//TODO uncomment when adaptive config is finished
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
	
	}
	
	private void initRenderers()
	{
	
	}
	
	@Override
	public String getLastVersion()
	{
		return null;
	}//TODO add proper update thingy
	
	@Override
	public void setEntryToOpen(TomeEntry entry)
	{
		GuiTome.currentOpenTome = new GuiTomeEntry(entry, new GuiTomeIndex(entry.category));
	}
	
	@Override
	public void setTomeStack(ItemStack stack)
	{
		GuiTome.stackUsed = stack;
	}
}