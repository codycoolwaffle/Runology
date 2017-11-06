/*
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

package ccw.wafflekingdom.runology.common.core.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy
{
	void preInit(FMLPreInitializationEvent event);
	
	void init(FMLInitializationEvent event);
	
	void postInit(FMLPostInitializationEvent event);
	
	String getLastVersion();
}