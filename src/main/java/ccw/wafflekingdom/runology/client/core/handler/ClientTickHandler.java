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
package ccw.wafflekingdom.runology.client.core.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import ccw.wafflekingdom.runology.client.gui.tome.GuiTome;

@Mod.EventBusSubscriber(Side.CLIENT)
public final class ClientTickHandler
{
	private ClientTickHandler(){}
	
	public static int ticksWithTomeOpen = 0;
	public static int ticksInGame = 0;
	public static float partialTicks = 0;
	public static float delta = 0;
	public static float total = 0;
	
	private static void calcDelta()
	{
		float oldTotal = total; total = ticksInGame + partialTicks; delta = total - oldTotal;
	}
	
	@SubscribeEvent
	public static void renderTick(TickEvent.RenderTickEvent event)
	{
		if(event.phase == TickEvent.Phase.START)
			partialTicks = event.renderTickTime;
		else
			calcDelta();
	}
	
	@SubscribeEvent
	public static void clientTickEnd(TickEvent.ClientTickEvent event)
	{
		if(event.phase == TickEvent.Phase.END)
		{
			GuiScreen gui = Minecraft.getMinecraft().currentScreen;
			
			int ticksToOpen = 10; if(gui instanceof GuiTome)
		{
			if(ticksWithTomeOpen < 0)
				ticksWithTomeOpen = 0; if(ticksWithTomeOpen < ticksToOpen)
			ticksWithTomeOpen++;
		}
		else
		{
			if(ticksWithTomeOpen > 0)
			{
				if(ticksWithTomeOpen > ticksToOpen)
				{
					ticksWithTomeOpen = ticksToOpen;
				}
			}
		}
			
			calcDelta();
		}
	}
}
