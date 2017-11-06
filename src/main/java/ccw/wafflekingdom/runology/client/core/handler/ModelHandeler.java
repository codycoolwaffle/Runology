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

package ccw.wafflekingdom.runology.client.core.handler;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IRegistryDelegate;

import java.util.Locale;
import java.util.Map;
import java.util.function.IntFunction;

import ccw.wafflekingdom.runology.client.render.IModelRegister;
import ccw.wafflekingdom.runology.common.lib.LibMisc;

@Mod.EventBusSubscriber(Side.CLIENT)
public final class ModelHandeler
{
	private static final Map<IRegistryDelegate<Block>, IStateMapper> customStateMappers
			= ReflectionHelper.getPrivateValue(ModelLoader.class, null, "customStateMappers");
	private static final DefaultStateMapper fallbackMapper = new DefaultStateMapper();
	
	private ModelHandeler()
	{
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent evt)
	{
		OBJLoader.INSTANCE.addDomain(LibMisc.MOD_ID.toLowerCase(Locale.ROOT));
		
		registerSubtiles();
		
		for(Block block : Block.REGISTRY)
		{
			if(block instanceof IModelRegister)
			{
				((IModelRegister) block).registerModels();
			}
		}
		
		for(Item item : Item.REGISTRY)
		{
			if(item instanceof IModelRegister)
			{
				((IModelRegister) item).registerModels();
			}
		}
	}
	
	private static void registerSubtiles()
	{
		//BotaniaAPIClient.registerIslandTypeModel(IFloatingFlower.IslandType.GRASS, new ModelResourceLocation("botania:miniIsland", "variant=grass"));
		
	}
	
	public static void registerItemAllMeta(Item item, int range)
	{
		registerItemMetas(item, range, i -> item.getRegistryName().getResourcePath());
	}
	
	public static void registerItemAppendMeta(Item item, int maxExclusive, String loc)
	{
		registerItemMetas(item, maxExclusive, i -> loc + i);
	}
	
	public static void registerItemMetas(Item item, int maxExclusive,
	                                     IntFunction<String> metaToName)
	{
		for(int i = 0; i < maxExclusive; i++)
		{
			ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(
					LibMisc.MOD_ID + ":" + metaToName.apply(i), "inventory"));
		}
	}
	
	private static ModelResourceLocation getMrlForState(IBlockState state)
	{
		return customStateMappers.getOrDefault(state.getBlock().delegate, fallbackMapper)
		                         .putStateModelLocations(state.getBlock()).get(state);
	}
	
	public static void registerBlockToState(Block b, int meta, IBlockState state)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), meta,
		                                           getMrlForState(state));
	}
	
	public static void registerBlockToState(Block b, int maxExclusive)
	{
		for(int i = 0; i < maxExclusive; i++)
		{
			registerBlockToState(b, i, b.getStateFromMeta(i));
		}
	}
	
	// Registers the ItemBlock to models/item/<registryname>#inventory
	public static void registerInventoryVariant(Block b)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0,
		                                           new ModelResourceLocation(b.getRegistryName(),
		                                                                     "inventory"));
	}
	
	// Registers the ItemBlock to a custom path in models/item/itemblock/
	public static void registerCustomItemblock(Block b, String path)
	{
		registerCustomItemblock(b, 1, i -> path);
	}
	
	public static void registerCustomItemblock(Block b, int maxExclusive,
	                                           IntFunction<String> metaToPath)
	{
		Item item = Item.getItemFromBlock(b);
		for(int i = 0; i < maxExclusive; i++)
		{
			ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(
					LibMisc.MOD_ID + ":itemblock/" + metaToPath.apply(i), "inventory"));
		}
	}
}