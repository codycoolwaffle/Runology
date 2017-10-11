/**
 * Nearly all the code used in this file originates
 * from Botania made by <Vazkii>. It has been altered
 * to work for Runology by <codycoolwaffle>.
 *
 * Check out the corresponding Github's here:
 * https://github.com/Vazkii/Botania
 * https://github.com/Runology/
 */

package ccw.wafflekingdom.runology.client.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

import java.util.Set;

public class GuiFactory implements IModGuiFactory
{
	@Override
	public void initialize(Minecraft minecraftInstance){
	
	}
	
	@Override
	public boolean hasConfigGui()
	{
		return false;
	}
	
	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen)
	{
		return null;
	}
	
	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
	{
		return null;
	}
}