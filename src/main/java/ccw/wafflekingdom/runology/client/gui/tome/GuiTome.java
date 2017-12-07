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
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ccw.wafflekingdom.runology.api.RunologyAPI;
import ccw.wafflekingdom.runology.api.tome.TomeCategory;
import ccw.wafflekingdom.runology.client.core.handler.ClientTickHandler;
import ccw.wafflekingdom.runology.client.core.handler.PersistentVariableHelper;
import ccw.wafflekingdom.runology.client.gui.GuiRunologyConfig;
import ccw.wafflekingdom.runology.client.gui.tome.button.GuiButtonCategory;
import ccw.wafflekingdom.runology.client.lib.LibResources;
import ccw.wafflekingdom.runology.common.item.ItemRunicTome;
import ccw.wafflekingdom.runology.common.lib.LibMisc;

public class GuiTome extends GuiScreen
{
	public static GuiTome currentOpenTome = new GuiTome();
	public static ItemStack stackUsed = ItemStack.EMPTY;
	
	private static final String TAG_TYPE = "type";
	
	public static final ResourceLocation texture = new ResourceLocation(LibResources.GUI_TOME);
	
	public float lastTime = 0F;
	public float partialTicks = 0F;
	public float timeDelta = 0F;
	
	public String categoryHighlight = "";
	
	List<TomeCategory> allCategories;
	
	String title;
	final int guiWidth = 146;
	final int guiHeight = 180;
	int left, top;
	
	@Override
	public final void initGui()
	{
		super.initGui();
		
		onInitGui();
	}
	
	public void onInitGui()
	{
		int guiScale = mc.gameSettings.guiScale;
		int persistentScale = Math.min(PersistentVariableHelper.tomeGuiScale, getMaxAllowedScale());
		
		if(persistentScale > 0 && persistentScale != guiScale)
		{
			mc.gameSettings.guiScale = persistentScale;
			ScaledResolution res = new ScaledResolution(mc); width = res.getScaledWidth();
			height = res.getScaledHeight(); mc.gameSettings.guiScale = guiScale;
		}
		
		allCategories = new ArrayList<>(RunologyAPI.getCategories());
		Collections.sort(allCategories);
		
		lastTime = ClientTickHandler.ticksInGame;
		
		title = ItemRunicTome.getTitle(stackUsed); currentOpenTome = this;
		
		left = width / 2 - guiWidth / 2; top = height / 2 - guiHeight / 2;
		
		buttonList.clear(); if(isIndex())
	{
		int x = 18; for(int i = 0; i < 12; i++)
	{
		int y = 16 + i * 12;
	} populateIndex();
	}
	else
		if(isCategoryIndex())
		{
			int categories = allCategories.size(); for(int i = 0; i < categories; i++)
		{
			TomeCategory category; category = i >= categories ? null : allCategories.get(i);
			int perline = 5; int x = i % perline; int y = i / perline;
			
			int size = 22; GuiButtonCategory button = new GuiButtonCategory(i, left + 18 + x * size,
			                                                                top + 50 + y * size,
			                                                                this, category);
			buttonList.add(button);
		}
		} if(isMainPage())
	{
		//buttonList.add(new GuiButtonOptions(-1, left + 20, top + guiHeight - 25)); //TODO add etc. buttons
		//buttonList.add(new GuiButtonAchievement(-2, left + 33, top + guiHeight - 25));
		//buttonList.add(new GuiButtonChallenges(-3, left + 45, top + guiHeight - 25));
		//buttonList.add(new GuiButtonScaleChange(-4, left + 57, top + guiHeight - 25));
			
			/*GuiButtonUpdateWarning button = new GuiButtonUpdateWarning(-98, left - 6, top + guiHeight - 70);
			buttonList.add(button);
			
			if(PersistentVariableHelper.lastBotaniaVersion.equals(LibMisc.VERSION)) {
				button.enabled = false;
				button.visible = false;
			}*/
	}
	}
	
	@Override
	public void updateScreen()
	{
	
	}
	
	@Override
	public final void drawScreen(int x, int y, float partialTicks)
	{
		ScaledResolution res = new ScaledResolution(mc); int guiScale = mc.gameSettings.guiScale;
		
		GlStateManager.pushMatrix();
		int persistentScale = Math.min(PersistentVariableHelper.tomeGuiScale, getMaxAllowedScale());
		
		if(persistentScale > 0 && persistentScale != guiScale)
		{
			mc.gameSettings.guiScale = persistentScale;
			float s = (float) persistentScale / (float) res.getScaleFactor();
			
			GlStateManager.scale(s, s, s);
			
			res = new ScaledResolution(mc); int sw = res.getScaledWidth();
			int sh = res.getScaledHeight(); x = Mouse.getX() * sw / mc.displayWidth;
			y = sh - Mouse.getY() * sh / mc.displayHeight - 1;
		}
		
		drawScreenAfterScale(x, y, partialTicks);
		
		mc.gameSettings.guiScale = guiScale; GlStateManager.popMatrix();
	}
	
	public void drawScreenAfterScale(int xCoord, int yCoord, float newPartialTicks)
	{
		float time = ClientTickHandler.ticksInGame + newPartialTicks;
		timeDelta = time - lastTime + partialTicks; lastTime = time; partialTicks = newPartialTicks;
		
		GlStateManager.color(1F, 1F, 1F, 1F); mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight);
		
		//if(ClientProxy.jingleTheBells)
		//drawTexturedModalRect(left + 3, top + 1, 0, 212, 138, 6);//TODO add christmas content
		
		if(isMainPage())
			drawHeader();
		
		super.drawScreen(xCoord, yCoord, newPartialTicks);
	}
	
	void drawHeader()
	{
		GlStateManager.pushMatrix(); GlStateManager.color(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(left - 8, top + 9, 0, 224, 140, 31);
		
		int color = 0xffd200; boolean unicode = fontRenderer.getUnicodeFlag();
		fontRenderer.drawString(title, left + 18, top + 13, color);
		
		String s = TextFormatting.BOLD + categoryHighlight; fontRenderer
			.drawString(s, left + guiWidth / 2 - fontRenderer.getStringWidth(s) / 2, top + 36, 0);
		
		fontRenderer.setUnicodeFlag(unicode); GlStateManager.popMatrix();
		
		categoryHighlight = "";
	}
	
	boolean isMainPage()
	{
		return true;
	}
	
	@Override
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		if(par1GuiButton instanceof GuiButtonCategory)
		{
			TomeCategory category = ((GuiButtonCategory) par1GuiButton).getCategory();
			
			mc.displayGuiScreen(new GuiTomeIndex(category));
		}
		else
			switch(par1GuiButton.id)
			{
				case -1:
					mc.displayGuiScreen(new GuiRunologyConfig(this)); break; case -2:
				if(mc.player != null)
				{
					GuiScreenAdvancements gui = new GuiScreenAdvancements(
							this.mc.player.connection.getAdvancementManager());
					this.mc.displayGuiScreen(gui);
					ResourceLocation tab = new ResourceLocation(LibMisc.MOD_ID, "main/root");
					gui.setSelectedTab(
							mc.player.connection.getAdvancementManager().getAdvancementList()
							                    .getAdvancement(tab));
				} break; case -3:
				//mc.displayGuiScreen(new GuiTomeChallengesList());
				break; case -4:
				int maxAllowed = getMaxAllowedScale(); if(PersistentVariableHelper.tomeGuiScale
				                                          >= maxAllowed)
					PersistentVariableHelper.tomeGuiScale = 2;
				else
					PersistentVariableHelper.tomeGuiScale++;
				
				PersistentVariableHelper.saveSafe(); mc.displayGuiScreen(new GuiTome()); break;
				case -98:
					if(isShiftKeyDown())
					{
						try
						{
							//if(Desktop.isDesktopSupported()) //TODO add update support
							//Desktop.getDesktop().browse(new URI("http://botaniamod.net/changelog.php#" + PersistentVariableHelper.lastRunologyVersion.replaceAll("\\.|\\s", "-")));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						//PersistentVariableHelper.lastRunologyVersion = LibMisc.VERSION;
						PersistentVariableHelper.saveSafe(); par1GuiButton.visible = false;
						par1GuiButton.enabled = false;
					}
					
					break;
			}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	String getTitle()
	{
		return title;
	}
	
	String getSubtitle()
	{
		return null;
	}
	
	int getTitleHeight()
	{
		return getSubtitle() == null ? 12 : 22;
	}
	
	boolean isIndex()
	{
		return false;
	}
	
	boolean isChallenge()
	{
		return false;
	}
	
	boolean isCategoryIndex()
	{
		return true;
	}
	
	public static int getMaxAllowedScale()
	{
		Minecraft mc = Minecraft.getMinecraft(); int scale = mc.gameSettings.guiScale;
		mc.gameSettings.guiScale = 0; ScaledResolution res = new ScaledResolution(mc);
		mc.gameSettings.guiScale = scale;
		
		return res.getScaleFactor();
	}
	
	void populateIndex()
	{
		List<TomeCategory> categoryList = RunologyAPI.getCategories(); int shift = 2;
		for(int i = shift; i < 12; i++)
		{
			int i_ = i - shift; GuiButton button = buttonList.get(i);
			TomeCategory category = i_ >= categoryList.size() ? null : categoryList.get(i_);
			if(category != null)
				button.displayString = I18n.format(category.getUnlocalizedName());
			else
			{
				button.displayString = I18n.format("runologymisc.tomeIndex"); break;
			}
		}
	}
	
	boolean closeScreenOnInvKey()
	{
		return true;
	}
	
	public static GuiTome create(NBTTagCompound cmp)
	{
		String type = cmp.getString(TAG_TYPE); try
	{
		GuiTome tome = (GuiTome) Class.forName(type).newInstance(); if(tome != null)
		tome.load(cmp); if(isValidTomeGui(tome))
		return tome; return null;
	}
	catch(Exception e)
	{
		e.printStackTrace(); return null;
	}
	}
	
	public void serialize(NBTTagCompound cmp)
	{
		cmp.setString(TAG_TYPE, getClass().getName());
	}
	
	public void load(NBTTagCompound cmp){}
	
	public GuiTome copy()
	{
		return new GuiTome();
	}
	
	public static boolean isValidTomeGui(GuiTome gui)
	{
		if(gui == null)
			return false; if(gui.isCategoryIndex() || gui.isChallenge())
		return true; if(gui.isIndex())
	{
		GuiTomeIndex indexGui = (GuiTomeIndex) gui; if(indexGui.category == null)
		return true; return RunologyAPI.getCategories().contains(indexGui.category);
	}
		
		GuiTomeEntry entryGui = (GuiTomeEntry) gui;
		if(!RunologyAPI.getEntries().contains(entryGui.entry))
			return false;
		
		return entryGui.page < entryGui.entry.pages.size();
	}
}
