/*
  Nearly all the code used in this file originates
  from Botania made by <Vazkii>. It has been altered
  to work for Runology by <codycoolwaffle>.
  
  Check out the corresponding Github's here:
  https://github.com/Vazkii/Botania
  https://github.com/codycoolwaffle/Runology
 */

package ccw.wafflekingdom.runology.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ccw.wafflekingdom.runology.common.core.handler.ConfigHandler;
import ccw.wafflekingdom.runology.common.core.proxy.IProxy;
import ccw.wafflekingdom.runology.common.lib.LibMisc;

@Mod(modid = LibMisc.MOD_ID, name = LibMisc.MOD_NAME, version = LibMisc.VERSION,
     dependencies = LibMisc.DEPENDENCIES, useMetadata = true)
public class Runology
{
	@SuppressWarnings("unused")
	@Instance(LibMisc.MOD_ID)
	public static Runology instance;
	
	@SidedProxy(serverSide = LibMisc.PROXY_SERVER, clientSide = LibMisc.PROXY_CLIENT)
	public static IProxy proxy;
	
	public static final Logger LOGGER = LogManager.getLogger(LibMisc.MOD_ID);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		LOGGER.info("Pre-Initializing!");
		ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		LOGGER.info("Initializing!");
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		LOGGER.info("Post-Initializing!");
		ConfigHandler.loadPostInit();
		proxy.postInit(event);
	}
}