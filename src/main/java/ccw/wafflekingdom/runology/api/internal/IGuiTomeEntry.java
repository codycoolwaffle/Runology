/*
  Nearly all the code used in this file originates
  from Botania made by <Vazkii>. It has been altered
  to work for Runology by <codycoolwaffle>.
  
  Check out the corresponding Github's here:
  https://github.com/Vazkii/Botania
  https://github.com/codycoolwaffle/Runology
 */

package ccw.wafflekingdom.runology.api.internal;

import net.minecraft.client.gui.GuiButton;

import java.util.List;

import ccw.wafflekingdom.runology.api.tome.TomeEntry;

/**
 Internal interface for the Tome Entry GUI. This contains
 everything that can be accessed from it. It's safe to cast
 this type to GuiScreen.
 */
public interface IGuiTomeEntry
{
	/**
	 Gets the entry currently portrayed in this gui.
	 */
	public TomeEntry getEntry();
	
	/**
	 Gets the current page the tome GUI is browsing.
	 */
	public int getPageOn();
	
	/**
	 Gets the leftmost part of the GUI.
	 */
	public int getLeft();
	
	/**
	 Gets the topmost part of the GUI.
	 */
	public int getTop();
	
	/**
	 Gets the GUI's width.
	 */
	public int getWidth();
	
	/**
	 Gets the GUI's height
	 */
	public int getHeight();
	
	/**
	 Gets the GUI's Z level for rendering.
	 */
	public float getZLevel();
	
	/**
	 Gets the list of buttons in this gui.
	 */
	public List<GuiButton> getButtonList();
	
	/**
	 Gets the total amount of ticks (+ partial ticks) the player
	 has been in this gui.
	 */
	public float getElapsedTicks();
	
	/**
	 Gets the current partial ticks.
	 */
	public float getPartialTicks();
	
	/**
	 Gets the delta (1F = 1 tick) between this render call
	 and the last one.
	 */
	public float getTickDelta();
}