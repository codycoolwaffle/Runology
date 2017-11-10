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
package ccw.wafflekingdom.runology.api.tome;

import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class TomeCategory implements Comparable<TomeCategory>
{
	private static int count = 0;
	
	public final String unlocalizedName;
	public final List<TomeEntry> entries = new ArrayList<>();
	private final int sortingId;
	private ResourceLocation icon;
	private int priority = 5;
	
	/**
	 @param unlocalizedName
	 The unlocalized name of this category. This will be localized by the client display.
	 */
	public TomeCategory(String unlocalizedName)
	{
		this.unlocalizedName = unlocalizedName;
		sortingId = count;
		count++;
	}
	
	public String getUnlocalizedName()
	{
		return unlocalizedName;
	}
	
	/**
	 Sets the priority for this category for sorting. Higher numbers
	 means they'll appear first in the book. The basics category
	 is 9, the miscellaneous category is 0, other vanilla botania categories
	 are 5. Using 9 and 0 is <b>not</b> recommended, since having your
	 categories before basics or after miscellaneous is a bad idea.
	 If two categories have the same priority they'll be sorted
	 by insertion order.
	 */
	public TomeCategory setPriority(int priority)
	{
		this.priority = priority;
		return this;
	}
	
	public int getSortingPriority()
	{
		return priority;
	}
	
	public final int getSortingId()
	{
		return sortingId;
	}
	
	public TomeCategory setIcon(ResourceLocation icon)
	{
		this.icon = icon;
		return this;
	}
	
	public ResourceLocation getIcon()
	{
		return icon;
	}
	
	
	@Override
	public int compareTo(@Nonnull TomeCategory category)
	{
		return priority == category.priority ? sortingId - category.sortingId :
		       category.priority - priority;
	}
}
