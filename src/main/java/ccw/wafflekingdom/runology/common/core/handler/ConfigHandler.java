/**
 * Nearly all the code used in this file originates
 * from Botania made by <Vazkii>. It has been altered
 * to work for Runology by <codycoolwaffle>.
 * <p>
 * Check out the corresponding Github's here:
 * https://github.com/Vazkii/Botania
 * https://github.com/codycoolwaffle/Runology
 */

package ccw.wafflekingdom.runology.common.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ccw.wafflekingdom.runology.common.Runology;
import ccw.wafflekingdom.runology.common.lib.LibMisc;

public class ConfigHandler
{
	public static Configuration config;
	public static ConfigAdaptor adaptor;
	
	
	public static boolean useAdaptativeConfig = true;
	
	public static int binary = 0;
	
	public static void loadConfig(File configFile)
	{
		config = new Configuration(configFile);
		
		config.load();
		load();
	}
	
	public static void load()
	{
		String desc;
		
		desc
				= "Set this to false to disable the Adaptative Config. Adaptative Config changes any default config values from old versions to the new defaults to make sure you aren't missing out on changes because of old configs. It will not touch any values that were changed manually.";
		useAdaptativeConfig = loadPropBool("adaptativeConfig.enabled", desc, useAdaptativeConfig);
		adaptor = new ConfigAdaptor(useAdaptativeConfig);
		
		desc = "Literally does nothing.";
		binary = loadPropInt("runologyMisc.binary", desc, binary);
		
	}
	
	public static void loadPostInit()
	{
		if(config.hasChanged())
		{
			config.save();
		}
	}
	
	public static int loadPropInt(String propName, String desc, int default_)
	{
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_);
		prop.setComment(desc);
		
		if(adaptor != null)
		{
			adaptor.adaptPropertyInt(prop, prop.getInt(default_));
		}
		
		return prop.getInt(default_);
	}
	
	public static double loadPropDouble(String propName, String desc, double default_)
	{
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_);
		prop.setComment(desc);
		
		if(adaptor != null)
		{
			adaptor.adaptPropertyDouble(prop, prop.getDouble(default_));
		}
		
		return prop.getDouble(default_);
	}
	
	public static boolean loadPropBool(String propName, String desc, boolean default_)
	{
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_);
		prop.setComment(desc);
		
		if(adaptor != null)
		{
			adaptor.adaptPropertyBool(prop, prop.getBoolean(default_));
		}
		
		return prop.getBoolean(default_);
	}
	
	public static class ConfigAdaptor
	{
		
		private boolean enabled;
		private int lastBuild;
		private int currentBuild;
		
		private final Map<String, List<AdaptableValue>> adaptableValues = new HashMap<>();
		private final List<String> changes = new ArrayList<>();
		
		public ConfigAdaptor(boolean enabled)
		{
			this.enabled = enabled;
			
			String lastVersion = Runology.proxy.getLastVersion();
			try
			{
				lastBuild = Integer.parseInt(lastVersion);
				currentBuild = Integer.parseInt(LibMisc.BUILD);
			}
			catch(NumberFormatException e)
			{
				this.enabled = false;
			}
		}
		
		public <T> void adaptProperty(Property prop, T val)
		{
			if(!enabled)
			{
				return;
			}
			
			String name = prop.getName();
			
			if(!adaptableValues.containsKey(name))
			{
				return;
			}
			
			AdaptableValue<T> bestValue = null;
			for(AdaptableValue<T> value : adaptableValues.get(name))
			{
				if(value.version
				   >= lastBuild) // If version is newer than what we last used we don't care about it
				{
					continue;
				}
				
				if(bestValue == null || value.version > bestValue.version)
				{
					bestValue = value;
				}
			}
			
			if(bestValue != null)
			{
				T expected = bestValue.value;
				T def = (T) prop.getDefault();
				
				if(areEqualNumbers(val, expected) && !areEqualNumbers(val, def))
				{
					prop.setValue(def.toString());
					changes.add(" " + prop.getName() + ": " + val + " -> " + def);
				}
			}
		}
		
		public <T> void addMapping(int version, String key, T val)
		{
			if(!enabled)
			{
				return;
			}
			
			AdaptableValue<T> adapt = new AdaptableValue<>(version, val);
			if(!adaptableValues.containsKey(key))
			{
				adaptableValues.put(key, new ArrayList<>());
			}
			
			List<AdaptableValue> list = adaptableValues.get(key);
			list.add(adapt);
		}
		
		public boolean areEqualNumbers(Object v1, Object v2)
		{
			double epsilon = 1.0E-6;
			float v1f = ((Number) v1).floatValue();
			float v2f;
			
			if(v2 instanceof String)
			{
				v2f = Float.parseFloat((String) v2);
			}
			else
			{
				v2f = ((Number) v2).floatValue();
			}
			
			return Math.abs(v1f - v2f) < epsilon;
		}
		
		public void tellChanges(EntityPlayer player)
		{
			if(changes.size() == 0)
			{
				return;
			}
			
			player.sendMessage(new TextComponentTranslation("runologymisc.adaptativeConfigChanges")
					                   .setStyle(new Style().setColor(TextFormatting.GOLD)));
			for(String change : changes)
			{
				player.sendMessage(new TextComponentString(change).setStyle(
						new Style().setColor(TextFormatting.LIGHT_PURPLE)));
			}
		}
		
		public void addMappingInt(int version, String key, int val)
		{
			this.addMapping(version, key, val);
		}
		
		public void addMappingDouble(int version, String key, double val)
		{
			this.addMapping(version, key, val);
		}
		
		public void addMappingBool(int version, String key, boolean val)
		{
			this.addMapping(version, key, val);
		}
		
		public void adaptPropertyInt(Property prop, int val)
		{
			this.adaptProperty(prop, val);
		}
		
		public void adaptPropertyDouble(Property prop, double val)
		{
			this.adaptProperty(prop, val);
		}
		
		public void adaptPropertyBool(Property prop, boolean val)
		{
			this.adaptProperty(prop, val);
		}
		
		public static class AdaptableValue<T>
		{
			
			public final int version;
			public final T value;
			public final Class<? extends T> valueType;
			
			public AdaptableValue(int version, T value)
			{
				this.version = version;
				this.value = value;
				valueType = (Class<? extends T>) value.getClass();
			}
			
		}
		
	}
	
	@Mod.EventBusSubscriber
	public static class ChangeListener
	{
		
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
		{
			if(eventArgs.getModID().equals(LibMisc.MOD_ID))
			{
				load();
			}
		}
		
	}
}