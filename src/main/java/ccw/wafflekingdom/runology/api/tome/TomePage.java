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
package ccw.wafflekingdom.runology.api.tome;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import ccw.wafflekingdom.runology.api.internal.IGuiTomeEntry;

public abstract class TomePage
{
	public String unlocalizedName;
	public boolean skipRegistry;
	
	public TomePage(String unlocalizedName)
	{
		this.unlocalizedName = unlocalizedName;
	}
	
	/**
	 Does the rendering for this page.
	 
	 @param gui
	 The active GuiScreen
	 @param mx
	 The mouse's relative X position.
	 @param my
	 The mouse's relative Y position.
	 */
	@SideOnly(Side.CLIENT)
	public abstract void renderScreen(IGuiTomeEntry gui, int mx, int my);
	
	/**
	 Called per update tick. Non gui-sensitive version, kept for backwards compatibility only.
	 */
	@SideOnly(Side.CLIENT)
	public void updateScreen()
	{
	}
	
	/**
	 Called per update tick. Feel free to override fully, the
	 call to updateScreen() is for backwards compatibility.
	 */
	@SideOnly(Side.CLIENT)
	public void updateScreen(IGuiTomeEntry gui)
	{
		updateScreen();
	}
	
	/**
	 Called when this page is opened, be it via initGui() or when the player changes pages.
	 You can add buttons and whatever you'd do on initGui() here.
	 */
	@SideOnly(Side.CLIENT)
	public void onOpened(IGuiTomeEntry gui)
	{
	}
	
	/**
	 Called when this page is closed, be it via closing the gui or when the player changes page.
	 Make sure to dispose of anything you don't use any more, such as buttons in the gui's buttonList.
	 */
	@SideOnly(Side.CLIENT)
	public void onClosed(IGuiTomeEntry gui)
	{
	}
	
	/**
	 Called when a button is pressed, equivalent to GuiScreen.actionPerformed.
	 */
	@SideOnly(Side.CLIENT)
	public void onActionPerformed(IGuiTomeEntry gui, GuiButton button)
	{
	}
	
	/**
	 Called when a key is pressed.
	 */
	@SideOnly(Side.CLIENT)
	public void onKeyPressed(char c, int key)
	{
	}
	
	/**
	 Called when {@link TomeEntry#setTomePages(TomePage...)} is called.
	 */
	public void onPageAdded(TomeEntry entry, int index)
	{
	}
	
	/**
	 Shows the list of recipes present in this page for display in the category
	 page.
	 */
	public List<ItemStack> getDisplayedRecipes()
	{
		return ImmutableList.of();
	}
	
	public String getUnlocalizedName()
	{
		return unlocalizedName;
	}
	
	public TomePage setSkipRegistry()
	{
		skipRegistry = true;
		return this;
	}
}
