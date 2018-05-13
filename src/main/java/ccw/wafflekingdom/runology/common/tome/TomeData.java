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

import ccw.wafflekingdom.runology.api.RunologyAPI;
import ccw.wafflekingdom.runology.api.tome.TomeCategory;
import ccw.wafflekingdom.runology.api.tome.TomeEntry;
import ccw.wafflekingdom.runology.common.lib.LibTome;

public class TomeData
{
	public static TomeEntry welcome;
	
	public static void init()
	{
		RunologyAPI.addCategory(
				RunologyAPI.categoryBasics = new BTomeCategory(LibTome.CATEGORY_BASICS, 9));
		RunologyAPI.addCategory(
				RunologyAPI.categoryAura = new BTomeCategory(LibTome.CATEGORY_AURA, 5)); RunologyAPI
			.addCategory(RunologyAPI.categoryAuraProducers = new BTomeCategory(
					LibTome.CATEGORY_AURA_PRODUCERS, 5)); RunologyAPI.addCategory(
			RunologyAPI.categoryAuraConsumers = new BTomeCategory(LibTome.CATEGORY_AURA_CONSUMERS,
			                                                      5)); RunologyAPI.addCategory(
			RunologyAPI.categoryMechanisms = new BTomeCategory(LibTome.CATEGORY_MECHANISMS, 5));
		RunologyAPI.addCategory(
				RunologyAPI.categoryTools = new BTomeCategory(LibTome.CATEGORY_TOOLS, 5));
		RunologyAPI.addCategory(
				RunologyAPI.categoryAccessories = new BTomeCategory(LibTome.CATEGORY_ACCESSORIES,
				                                                    5)); RunologyAPI
			.addCategory(RunologyAPI.categoryRunes = new BTomeCategory(LibTome.CATEGORY_RUNES, 5));
		RunologyAPI.addCategory(
				RunologyAPI.categoryMisc = new BTomeCategory(LibTome.CATEGORY_MISC, 1));
		
		TomeCategory categoryBasics = RunologyAPI.categoryBasics;
		TomeCategory categoryAura = RunologyAPI.categoryAura;
		TomeCategory categoryAuraProducers = RunologyAPI.categoryAuraProducers;
		TomeCategory categoryAuraConsumers = RunologyAPI.categoryAuraConsumers;
		TomeCategory categoryMechanisms = RunologyAPI.categoryMechanisms;
		TomeCategory categoryTools = RunologyAPI.categoryTools;
		TomeCategory categoryAccessories = RunologyAPI.categoryAccessories;
		TomeCategory categoryRunes = RunologyAPI.categoryRunes;
		TomeCategory categoryMisc = RunologyAPI.categoryMisc;
		
		welcome = new WelcomeTomeEntry();
	}
}
