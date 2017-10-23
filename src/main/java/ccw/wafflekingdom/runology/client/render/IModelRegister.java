/**
 * This class was created by <codycoolwaffle>. It's
 * distributed as part of the Runology mod. Nearly all
 * the code used in this file originates from Botania
 * made by <Vazkii>. It has been altered to work for
 * the Runology mod.
 * <p>
 * Check out the corresponding Github's here:
 * https://github.com/Vazkii/Botania
 * https://github.com/codycoolwaffle/Runology
 */

package ccw.wafflekingdom.runology.client.render;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModelRegister
{
	@SideOnly(Side.CLIENT)
	void registerModels();
}