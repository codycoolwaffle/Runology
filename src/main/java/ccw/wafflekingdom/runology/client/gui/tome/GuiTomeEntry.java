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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.List;

import ccw.wafflekingdom.runology.api.RunologyAPI;
import ccw.wafflekingdom.runology.api.internal.IGuiTomeEntry;
import ccw.wafflekingdom.runology.api.tome.TomeEntry;
import ccw.wafflekingdom.runology.api.tome.TomePage;

public class GuiTomeEntry extends GuiTome implements IGuiTomeEntry
{
	private static final String TAG_ENTRY = "entry";
	private static final String TAG_PAGE = "page";
	
	public int page = 0;
	private boolean firstEntry = false;
	TomeEntry entry;
	private GuiScreen parent;
	private String title;
	private String subtitle;
	
	private GuiButton leftButton, rightButton, backButton;
	
	public GuiTomeEntry()
	{
		parent = new GuiTome(); setTitle();
	}
	
	public GuiTomeEntry(TomeEntry entry, GuiScreen parent)
	{
		this.entry = entry; this.parent = parent; setTitle();
	}
	
	private void setTitle()
	{
		if(entry == null)
		{
			title = "(null)"; return;
		}
		
		title = I18n.format(entry.getUnlocalizedName());
		//if(entry instanceof IAddonEntry) //TODO add addon support
		//	subtitle = I18n.format(((IAddonEntry) entry).getSubtitle());
		/*else*/
		subtitle = null;
	}
	
	@Override
	public void onInitGui()
	{
		super.onInitGui();
		//TODO add Gui button for navigation
		//buttonList.add(backButton = new GuiButtonBackWithShift(0, left + guiWidth / 2 - 8, top + guiHeight + 2));
		//buttonList.add(leftButton = new GuiButtonPage(1, left, top + guiHeight - 10, false));
		//buttonList.add(rightButton = new GuiButtonPage(2, left + guiWidth - 18, top + guiHeight - 10, true));
		//buttonList.add(new GuiButtonShare(3, left + guiWidth - 6, top - 2));
		//if(entry.getWebLink() != null)
		//	buttonList.add(new GuiButtonViewOnline(4, left - 8, top + 12));
		
		if(!GuiTome.isValidTomeGui(this))
		{
			currentOpenTome = new GuiTome(); mc.displayGuiScreen(currentOpenTome); return;
		}
		
		TomePage page = entry.pages.get(this.page);
		
		page.onOpened(this); updatePageButtons();
		//GuiTomeHistory.visit(entry);
	}
	
	@Override
	public TomeEntry getEntry()
	{
		return entry;
	}
	
	@Override
	public int getPageOn()
	{
		return page;
	}
	
	@Override
	boolean isMainPage()
	{
		return false;
	}
	
	@Override
	String getTitle()
	{
		return String.format("%s " + TextFormatting.ITALIC + "(%s/%s)", title, page + 1,
		                     entry.pages.size());
	}
	
	@Override
	String getSubtitle()
	{
		return subtitle;
	}
	
	@Override
	boolean isCategoryIndex()
	{
		return false;
	}
	
	@Override
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		TomePage currentPage = entry.pages.get(page); TomePage newPage;
		
		
		switch(par1GuiButton.id)
		{
			case 0:
				currentPage.onClosed(this); mc
					.displayGuiScreen(GuiScreen.isShiftKeyDown() ? new GuiTome() : parent); break;
			case 1:
				currentPage.onClosed(this); page--; newPage = entry.pages.get(page); newPage
					.onOpened(this);
				
				break; case 2:
			currentPage.onClosed(this); page++; newPage = entry.pages.get(page); newPage
					.onOpened(this);
			
			break; case 3:
			Minecraft mc = Minecraft.getMinecraft(); String cmd = "/botania-share " + entry
					.getUnlocalizedName();
			
			mc.ingameGUI.getChatGUI().addToSentMessages(cmd); mc.player.sendChatMessage(cmd); break;
			case 4:
				try
				{
					//if(Desktop.isDesktopSupported()) //TODO add wiki support (?)
					//Desktop.getDesktop().browse(new URI(entry.getWebLink()));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		}
		
		updatePageButtons(); currentPage.onActionPerformed(this, par1GuiButton);
	}
	
	public GuiTomeEntry setFirstEntry()
	{
		firstEntry = true; return this;
	}
	
	public void updatePageButtons()
	{
		leftButton.enabled = page != 0; rightButton.enabled = page + 1 < entry.pages.size();
		if(firstEntry)
			backButton.enabled = !rightButton.enabled;
	}
	
	@Override
	public void drawScreenAfterScale(int xCoord, int yCoord, float newPartialTicks)
	{
		super.drawScreenAfterScale(xCoord, yCoord, newPartialTicks);
		
		TomePage page = entry.pages.get(this.page); page.renderScreen(this, xCoord, yCoord);
	}
	
	@Override
	public void updateScreen()
	{
		super.updateScreen();
		
		TomePage page = entry.pages.get(this.page); page.updateScreen(this);
	}
	
	@Override
	public int getLeft()
	{
		return left;
	}
	
	@Override
	public int getTop()
	{
		return top;
	}
	
	@Override
	public int getWidth()
	{
		return guiWidth;
	}
	
	@Override
	public int getHeight()
	{
		return guiHeight;
	}
	
	@Override
	public float getZLevel()
	{
		return zLevel;
	}
	
	//@Override //TODO add IParented interface
	//public void setParent(GuiTome gui) {
	//	parent = gui;
	//}
	
	private int fx = 0;
	private boolean swiped = false;
	
	@Override
	protected void mouseClickMove(int x, int y, int button, long time)
	{
		if(button == 0 && Math.abs(x - fx) > 100 && mc.gameSettings.touchscreen && !swiped)
		{
			double swipe = (x - fx) / Math.max(1, (double) time); if(swipe < 0.5)
		{
			nextPage(); swiped = true;
		}
		else
			if(swipe > 0.5)
			{
				prevPage(); swiped = true;
			}
		}
	}
	
	@Override
	protected void mouseClicked(int par1, int par2, int par3) throws IOException
	{
		super.mouseClicked(par1, par2, par3);
		
		fx = par1; switch(par3)
	{
		case 1:
			back(); break; case 3:
		nextPage(); break; case 4:
		prevPage(); break;
	}
	}
	
	@Override
	public void handleMouseInput() throws IOException
	{
		super.handleMouseInput();
		
		if(Mouse.getEventButton() == 0)
			swiped = false;
		
		int w = Mouse.getEventDWheel(); if(w < 0)
		nextPage();
	else
		if(w > 0)
			prevPage();
	}
	
	@Override
	protected void keyTyped(char par1, int par2)
	{
		TomePage page = entry.pages.get(this.page); page.onKeyPressed(par1, par2);
		
		if(par2 == 1)
		{
			mc.displayGuiScreen(null); mc.setIngameFocus();
		}
		else
			if(par2 == 203 || par2 == 200 || par2 == 201) // Left, Up, Page Up
				prevPage();
			else
				if(par2 == 205 || par2 == 208 || par2 == 209) // Right, Down Page Down
					nextPage(); if(par2 == 14) // Backspace
		back();
	else
		if(par2 == 199)
		{ // Home
			mc.displayGuiScreen(new GuiTome());
		}
	}
	
	private void back()
	{
		if(backButton.enabled)
		{
			actionPerformed(backButton); backButton.playPressSound(mc.getSoundHandler());
		}
	}
	
	private void nextPage()
	{
		if(rightButton.enabled)
		{
			actionPerformed(rightButton); rightButton.playPressSound(mc.getSoundHandler());
		}
	}
	
	private void prevPage()
	{
		if(leftButton.enabled)
		{
			actionPerformed(leftButton); leftButton.playPressSound(mc.getSoundHandler());
		}
	}
	
	@Override
	public List<GuiButton> getButtonList()
	{
		return buttonList;
	}
	
	@Override
	public float getElapsedTicks()
	{
		return lastTime;
	}
	
	@Override
	public float getPartialTicks()
	{
		return partialTicks;
	}
	
	@Override
	public float getTickDelta()
	{
		return timeDelta;
	}
	
	@Override
	public void serialize(NBTTagCompound cmp)
	{
		super.serialize(cmp); cmp.setString(TAG_ENTRY, entry.getUnlocalizedName());
		cmp.setInteger(TAG_PAGE, page);
	}
	
	@Override
	public void load(NBTTagCompound cmp)
	{
		super.load(cmp);
		
		String entryStr = cmp.getString(TAG_ENTRY); for(TomeEntry entry : RunologyAPI.getEntries())
		if(entry.getUnlocalizedName().equals(entryStr))
		{
			this.entry = entry; break;
		}
		
		page = cmp.getInteger(TAG_PAGE);
		
		setTitle();
	}
	
	@Override
	public GuiTome copy()
	{
		GuiTomeEntry gui = new GuiTomeEntry(entry, parent); gui.page = page; gui.setTitle();
		return gui;
	}
}
