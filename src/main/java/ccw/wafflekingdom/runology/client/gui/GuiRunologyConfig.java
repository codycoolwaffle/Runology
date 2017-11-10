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
package ccw.wafflekingdom.runology.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

import ccw.wafflekingdom.runology.common.core.handler.ConfigHandler;
import ccw.wafflekingdom.runology.common.lib.LibMisc;

public class GuiRunologyConfig extends GuiConfig
{
	public GuiRunologyConfig(GuiScreen parentScreen)
	{
		super(parentScreen,
		      new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL))
				      .getChildElements(), LibMisc.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
	}
}