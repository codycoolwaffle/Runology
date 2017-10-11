/**
 * Nearly all the code used in this file originates
 * from Botania made by <Vazkii>. It has been altered
 * to work for Runology by <codycoolwaffle>.
 *
 * Check out the corresponding Github's here:
 * https://github.com/Vazkii/Botania
 * https://github.com/Runology/
 */

package ccw.wafflekingdom.runology.common.core.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import ccw.wafflekingdom.runology.common.lib.LibMisc;

public class ServerProxy implements IProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event){}
	
	@Override
	public void init(FMLInitializationEvent event){}
	
	@Override
	public void postInit(FMLPostInitializationEvent event){}
	
	@Override
	public String getLastVersion() {
		return LibMisc.BUILD;
	}
}