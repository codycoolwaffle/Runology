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
package ccw.wafflekingdom.runology.client.gui.tome;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

import java.util.ArrayList;
import java.util.List;

import ccw.wafflekingdom.runology.api.tome.TomeCategory;
import ccw.wafflekingdom.runology.api.tome.TomeEntry;

public class GuiTomeIndex extends GuiTome
{
	private static final String TAG_CATEGORY = "category";
	private static final String TAG_PAGE = "page";
	
	TomeCategory category;
	String title;
	private int page = 0;
	
	private GuiButton leftButton, rightButton, backButton;
	private GuiTome parent;
	GuiTextField searchField;
	
	private GuiButton currentButton;
	private TomeEntry currentEntry;
	private float infoTime;
	
	final List<TomeEntry> entriesToDisplay = new ArrayList<>();
	
	public GuiTomeIndex(TomeCategory category)
	{
		this.category = category; parent = new GuiTome(); setTitle();
	}
	
	private void setTitle()
	{
		title = I18n.format(category == null ? "runologymisc.tomeIndex" :
		                    category.getUnlocalizedName());
	}
}
