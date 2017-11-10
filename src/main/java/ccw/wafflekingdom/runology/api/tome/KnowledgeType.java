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

import net.minecraft.util.text.TextFormatting;

public class KnowledgeType
{
	public final String id;
	public final TextFormatting color;
	public final boolean autoUnlock;
	
	public KnowledgeType(String id, TextFormatting color, boolean autoUnlock)
	{
		this.id = id;
		this.color = color;
		this.autoUnlock = autoUnlock;
	}
	
	public String getUnlocalizedName()
	{
		return "runology.knowledge." + id;
	}
}
