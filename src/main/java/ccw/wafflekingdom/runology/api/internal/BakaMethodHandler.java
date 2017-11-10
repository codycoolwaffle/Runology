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
package ccw.wafflekingdom.runology.api.internal;

import ccw.wafflekingdom.runology.api.tome.TomePage;

public class BakaMethodHandler implements IInternalMethodHandler
{
	@Override
	public TomePage textPage(String key)
	{
		return bakaPage(key);
	}
	
	@Override
	public TomePage imagePage(String key, String resource)
	{
		return null;
	}
	
	private TomePage bakaPage(String key)
	{
		return new BakaPage(key);
	}
}
