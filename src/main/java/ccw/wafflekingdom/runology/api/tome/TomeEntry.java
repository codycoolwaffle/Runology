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

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import ccw.wafflekingdom.runology.api.RunologyAPI;

public class TomeEntry implements Comparable<TomeEntry>
{
	public final String unlocalizedName;
	public final TomeCategory category;
	
	private KnowledgeType type = RunologyAPI.basicKnowledge;
	
	public final List<TomePage> pages = new ArrayList<>();
	private boolean priority = false;
	private ItemStack icon = ItemStack.EMPTY;
	
	private final List<ItemStack> extraDisplayedRecipes = new ArrayList<>();
	
	/**
	 @param unlocalizedName
	 The unlocalized name of this entry. This will be localized by the client display.
	 */
	public TomeEntry(String unlocalizedName, TomeCategory category)
	{
		this.unlocalizedName = unlocalizedName;
		this.category = category;
	}
	
	/**
	 Set's this page as prioritized, as in, will appear before others in the tome.
	 */
	public TomeEntry setPriority()
	{
		priority = true;
		return this;
	}
	
	/**
	 Set's the knowledge type of this entry
	 */
	public TomeEntry setKnowledgeType(KnowledgeType type)
	{
		this.type = type;
		return this;
	}
	
	public KnowledgeType getKnowledgeType()
	{
		return type;
	}
	
	/**
	 Sets the display icon for this entry. Overriding the one already there. When adding recipe pages to the
	 entry, this will be called once for the result of the first found recipe.
	 */
	public void setIcon(ItemStack stack)
	{
		icon = stack;
	}
	public ItemStack getIcon()
	{
		return icon;
	}
	
	public boolean getPriority()
	{
		return priority;
	}
	
	public String getUnlocalizedName()
	{
		return unlocalizedName;
	}
	
	public String getTagline()
	{
		return null; //Override this if you want a tagline.
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isVisible()
	{
		return true;
	}
	
	/**
	 Sets what pages you want this entry to have.
	 */
	public TomeEntry setTomePages(TomePage... pages)
	{
		this.pages.addAll(Arrays.asList(pages));
		
		for(int i = 0; i < this.pages.size(); i++)
		{
			TomePage page = this.pages.get(i);
			if(!page.skipRegistry)
			{
				page.onPageAdded(this, i);
			}
		}
		
		return this;
	}
	
	/**
	 Returns the web link for this entry. If this isn't null, looking at this entry will
	 show a "View Online" button in the tome. The String returned should be the URL to
	 open when the button is clicked.
	 */
	public String getWeblink()
	{
		return null;
	}
	
	/**
	 Adds a page to the list of pages.
	 */
	public void addPage(TomePage page)
	{
		pages.add(page);
	}
	
	public final String getNameForSorting()
	{
		return (priority ? 0 : 1) + I18n.translateToLocal(getUnlocalizedName());
	}
	
	public List<ItemStack> getDisplayedRecipes()
	{
		ArrayList<ItemStack> list = new ArrayList<>();
		for(TomePage page : pages)
		{
			ArrayList<ItemStack> itemsAddedThisPage = new ArrayList<>();
			
			for(ItemStack s : page.getDisplayedRecipes())
			{
				addItem:
				{
					for(ItemStack s1 : itemsAddedThisPage)
					{
						if(s1.getItem() == s.getItem())
						{
							break addItem;
						}
					}
					for(ItemStack s1 : list)
					{
						if(s1.isItemEqual(s) && ItemStack.areItemStackTagsEqual(s1, s))
						{
							break addItem;
						}
					}
					
					itemsAddedThisPage.add(s);
					list.add(s);
				}
			}
		}
		
		list.addAll(extraDisplayedRecipes);
		
		return list;
	}
	
	public void addExtraDisplayedRecipe(ItemStack stack)
	{
		extraDisplayedRecipes.add(stack);
	}
	
	@Override
	public int compareTo(@Nonnull TomeEntry o)
	{
		return getNameForSorting().compareTo(o.getNameForSorting());
	}
}
