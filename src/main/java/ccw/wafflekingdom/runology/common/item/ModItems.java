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

import ccw.wafflekingdom.runology.common.Runology;
import ccw.wafflekingdom.runology.common.lib.LibMisc;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID)
public final class ModItems
{
	public static final Item runicTome = new ItemRunicTome();
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		Runology.LOGGER.info("Registering Items!");
		IForgeRegistry<Item> r = event.getRegistry();
		
		r.register(runicTome);
	}
}