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
package ccw.wafflekingdom.runology.api;

import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ccw.wafflekingdom.runology.api.internal.BakaMethodHandler;
import ccw.wafflekingdom.runology.api.internal.IInternalMethodHandler;
import ccw.wafflekingdom.runology.api.tome.KnowledgeType;
import ccw.wafflekingdom.runology.api.tome.TomeCategory;
import ccw.wafflekingdom.runology.api.tome.TomeEntry;

public class RunologyAPI
{
	private static final List<TomeCategory> categories = new ArrayList<>();
	private static final List<TomeEntry> allEntries = new ArrayList<>();
	
	public static final Map<String, KnowledgeType> knowledgeTypes = new HashMap<>();
	
	public static final KnowledgeType basicKnowledge;
	
	//All of these categories are initialized during Runology's PreInit stage.
	public static TomeCategory categoryBasics;
	
	static
	{
		basicKnowledge = registerKnowledgeType("minecraft", TextFormatting.RESET, true);
	}
	
	/**
	 The internal method handler in use.
	 
	 @see IInternalMethodHandler
	 */
	public static IInternalMethodHandler internalHandler = new BakaMethodHandler();
	
	/**
	 Registers a new knowledge type.
	 
	 @param id
	 The ID for this knowledge type.
	 @param color
	 The color to display this knowledge type as.
	 */
	public static KnowledgeType registerKnowledgeType(String id, TextFormatting color,
	                                                  boolean autoUnlock)
	{
		KnowledgeType type = new KnowledgeType(id, color, autoUnlock);
		knowledgeTypes.put(id, type);
		return type;
	}
	
	/**
	 Adds a category to the list of registered categories to appear in the Tome.
	 */
	public static void addCategory(TomeCategory category)
	{
		categories.add(category);
	}
	
	/**
	 Gets all registered categories.
	 */
	public static List<TomeCategory> getCategories()
	{
		return categories;
	}
	
	/**
	 Gets all registered entries.
	 */
	public static List<TomeEntry> getEntries()
	{
		return allEntries;
	}
	
	/**
	 Registers a Tome entry and adds it to the category passed in.
	 */
	public static void addEntry(TomeEntry entry, TomeCategory category)
	{
		allEntries.add(entry);
		category.entries.add(entry);
	}
}
