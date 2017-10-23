/**
 * Nearly all the code used in this file originates
 * from Botania made by <Vazkii>. It has been altered
 * to work for Runology by <codycoolwaffle>.
 * <p>
 * Check out the corresponding Github's here:
 * https://github.com/Vazkii/Botania
 * https://github.com/codycoolwaffle/Runology
 */

package ccw.wafflekingdom.runology.common.item;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import ccw.wafflekingdom.runology.common.Runology;

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