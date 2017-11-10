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

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import ccw.wafflekingdom.runology.api.tome.TomePage;

/**
 A dummy page. It does absolutely nothing and is only
 existent to make sure everything goes right even if
 Runology isn't loaded.
 */
public class BakaPage extends TomePage
{
	public BakaPage(String unlocalizedName)
	{
		super(unlocalizedName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderScreen(IGuiTomeEntry gui, int mx, int my)
	{
	}
}
