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

import javax.annotation.Nonnull;

import ccw.wafflekingdom.runology.api.RunologyAPI;
import ccw.wafflekingdom.runology.api.tome.TomeCategory;
import ccw.wafflekingdom.runology.api.tome.TomeEntry;
import ccw.wafflekingdom.runology.api.tome.TomePage;

public class BasicTomeEntry extends TomeEntry
{
	public BasicTomeEntry(String unlocalizedName, TomeCategory category)
	{
		super(unlocalizedName, category); RunologyAPI.addEntry(this, category);
	}
	
	@Override
	public TomeEntry setTomePages(TomePage... pages)
	{
		for(TomePage page : pages)
		{
			page.unlocalizedName = "runology.page." + getLazyUnlocalizedName()
			                       + page.unlocalizedName;
		}
		
		return super.setTomePages(pages);
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "runology.entry." + super.getUnlocalizedName();
	}
	
	@Override
	public String getTagline()
	{
		return "Runology.tagLine." + super.getUnlocalizedName();
	}
	
	public String getLazyUnlocalizedName()
	{
		return super.getUnlocalizedName();
	}
	
	@Override
	public int compareTo(@Nonnull TomeEntry o)
	{
		return o instanceof WelcomeTomeEntry ? 1 : super.compareTo(o);
	}
}
