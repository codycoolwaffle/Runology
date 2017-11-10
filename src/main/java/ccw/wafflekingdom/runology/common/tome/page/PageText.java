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
package ccw.wafflekingdom.runology.common.tome.page;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

import ccw.wafflekingdom.runology.api.internal.IGuiTomeEntry;
import ccw.wafflekingdom.runology.api.tome.TomePage;

public class PageText extends TomePage
{
	PageText(String unlocalizedName)
	{
		super(unlocalizedName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderScreen(IGuiTomeEntry gui, int mx, int my)
	{
		int width = gui.getWidth() - 30;
		int x = gui.getLeft() + 16;
		int y = gui.getTop() + 2;
		
		renderText(x, y, width, gui.getHeight(), getUnlocalizedName());
	}
	
	public static void renderText(int x, int y, int width, int height, String unlocalizedText)
	{
		renderText(x, y, width, height, 10, true, 0, unlocalizedText);
	}
	
	public static void renderText(int x, int y, int width, int height, int paragraphSize,
	                              boolean useUnicode, int color, String unlocalizedText)
	{
		x += 2;
		y += 10;
		width -= 4;
		FontRenderer font = Minecraft.getMinecraft().fontRenderer;
		boolean unicode = font.getUnicodeFlag();
		if(useUnicode)
		{
			font.setUnicodeFlag(true);
		}
		String text = I18n.format(unlocalizedText).replaceAll("&", "\u00a7");
		String[] textEntries = text.split("<br>");
		
		List<List<String>> lines = new ArrayList<>();
		
		String controlCodes;
		for(String s : textEntries)
		{
			List<String> words = new ArrayList<>();
			String lineStr = "";
			String[] tokens = s.split(" ");
			for(String token : tokens)
			{
				String prev = lineStr;
				String spaced = token + " ";
				lineStr += spaced;
				
				controlCodes = toControlCodes(getControlCodes(prev));
				if(font.getStringWidth(lineStr) > width)
				{
					lines.add(words);
					lineStr = controlCodes + spaced;
					words = new ArrayList<>();
				}
				
				words.add(controlCodes + token);
			}
			
			if(!lineStr.isEmpty())
			{
				lines.add(words);
			}
			lines.add(new ArrayList<>());
		}
		
		int i = 0;
		for(List<String> words : lines)
		{
			words.size();
			int xi = x;
			int spacing = 4;
			int wcount = words.size();
			int compensationSpaces = 0;
			
			
			//TODO: add justified text to the tome
			/*boolean justify = ConfigHandler.tomeJustifiedText && wcount > 0 && lines.size() > i && !lines.get(i + 1).isEmpty();
			
			if(justify) {
				String s = Joiner.on("").join(words);
				int swidth = font.getStringWidth(s);
				int space = width - swidth;
				
				spacing = wcount == 1 ? 0 : space / (wcount - 1);
				compensationSpaces = wcount == 1 ? 0 : space % (wcount - 1);
			}*/
			
			for(String s : words)
			{
				int extra = 0;
				if(compensationSpaces > 0)
				{
					compensationSpaces--;
					extra++;
				}
				font.drawString(s, xi, y, color);
				xi += font.getStringWidth(s) + spacing + extra;
			}
			
			y += words.isEmpty() ? paragraphSize : 10;
			i++;
		}
		
		font.setUnicodeFlag(unicode);
	}
	
	private static String getControlCodes(String s)
	{
		String controls = s.replaceAll("(?<!\u00a7)(.)", "");
		return controls.replaceAll(".*r", "r");
	}
	
	private static String toControlCodes(String s)
	{
		return s.replaceAll(".", "\u00a7$0");
	}
}
