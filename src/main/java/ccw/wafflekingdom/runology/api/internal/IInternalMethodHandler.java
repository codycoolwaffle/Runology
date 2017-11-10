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

/**
 Any methods that refer to internal methods in Runology are here.
 This is defaulted to a dummy handler, whose methods do nothing.
 This handler is set to a proper one on PreInit. Make sure to
 make your mod load after Runology if you have any intention of
 doing anything with this on PreInit.
 */
public interface IInternalMethodHandler
{
	public TomePage textPage(String key);
	
	public TomePage imagePage(String key, String resource);
}