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

import ccw.wafflekingdom.runology.client.lib.LibResources;
import ccw.wafflekingdom.runology.common.Runology;
import ccw.wafflekingdom.runology.common.item.block.ItemBlockMod;
import ccw.wafflekingdom.runology.common.lib.LibBlockNames;
import ccw.wafflekingdom.runology.common.lib.LibMisc;
import ccw.wafflekingdom.runology.common.tile.TileSimpleInventory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID)
public class ModBlocks
{
	public static final Block runeEtcher = new BlockRuneEtcher();
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event)
	{
		Runology.LOGGER.info("Registering Blocks!");
		final IForgeRegistry<Block> r = event.getRegistry();
		
		r.register(runeEtcher);
	}
	
	@SubscribeEvent
	public static void registerItemBlocks(final RegistryEvent.Register<Item> event)
	{
		Runology.LOGGER.info("Registering ItemBlocks!");
		final IForgeRegistry<Item> r = event.getRegistry();
		
		r.register(new ItemBlockMod(runeEtcher).setRegistryName(runeEtcher.getRegistryName()));
		
		initTileEntities();
	}
	
	private static void initTileEntities()
	{
		Runology.LOGGER.info("Initializing Tile Entities!");
		registerTile(TileSimpleInventory.class, LibBlockNames.RUNE_ETCHER);
	}
	
	private static void registerTile(Class<? extends TileEntity> clazz, String key)
	{
		GameRegistry.registerTileEntity(clazz, LibResources.PREFIX_MOD + key);
	}
}