/**
 * Nearly all the code used in this file originates
 * from Botania made by <Vazkii>. It has been altered
 * to work for Runology by <codycoolwaffle>.
 *
 * Check out the corresponding Github's here:
 * https://github.com/Vazkii/Botania
 * https://github.com/Runology/
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
	public GuiRunologyConfig(GuiScreen parentScreen) {
		super(parentScreen, new ConfigElement(ConfigHandler.config.getCategory(
				Configuration.CATEGORY_GENERAL)).getChildElements(), LibMisc.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(
				ConfigHandler.config.toString()));
	}
}