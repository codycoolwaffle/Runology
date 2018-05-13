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
package ccw.wafflekingdom.runology.common.tome;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

import ccw.wafflekingdom.runology.api.RunologyAPI;
import ccw.wafflekingdom.runology.api.tome.TomeEntry;
import ccw.wafflekingdom.runology.api.tome.TomePage;
import ccw.wafflekingdom.runology.common.item.ModItems;
import ccw.wafflekingdom.runology.common.lib.LibTome;
import ccw.wafflekingdom.runology.common.tome.page.PageText;

public class WelcomeTomeEntry extends BasicTomeEntry
{
	private static final int PAGES = 7;
	
	public WelcomeTomeEntry()
	{
		super(LibTome.BASICS_WELCOME, RunologyAPI.categoryBasics); setPriority();
		setIcon(new ItemStack(ModItems.runicTome));
		
		TomePage[] pages = new TomePage[PAGES]; for(int i = 0; i < PAGES; i++)
	{
		pages[i] = new PageText("" + i);
	} setTomePages(pages);
	}
	
	@Override
	public int compareTo(@Nonnull TomeEntry o)
	{
		return -1;
	}
}
