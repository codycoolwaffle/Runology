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

import net.minecraft.util.ResourceLocation;

import ccw.wafflekingdom.runology.api.tome.TomeCategory;
import ccw.wafflekingdom.runology.client.lib.LibResources;
import ccw.wafflekingdom.runology.common.lib.LibTome;

public class BTomeCategory extends TomeCategory
{
	BTomeCategory(String unlocalizedName, int priority)
	{
		super(LibTome.CATEGORY_PREFIX + unlocalizedName);
		setIcon(new ResourceLocation(LibResources.PREFIX_CATEGORIES + unlocalizedName + ".png"));
		setPriority(priority);
	}
}
