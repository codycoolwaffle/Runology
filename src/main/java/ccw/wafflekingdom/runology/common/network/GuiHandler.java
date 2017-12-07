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
package ccw.wafflekingdom.runology.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import ccw.wafflekingdom.runology.client.gui.tome.GuiTome;
import ccw.wafflekingdom.runology.common.lib.LibGuiIDs;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int handId,
	                                  int unused1, int unused2)
	{
		EnumHand hand = handId == 1 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND; return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int handId,
	                                  int unused1, int unused2)
	{
		EnumHand hand = handId == 1 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
		
		switch(ID)
		{
			case LibGuiIDs.TOME:
				return GuiTome.currentOpenTome;
		}
		
		return null;
	}
}
