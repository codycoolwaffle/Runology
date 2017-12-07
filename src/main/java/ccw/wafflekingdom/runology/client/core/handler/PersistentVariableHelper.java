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

import java.io.IOException;

public class PersistentVariableHelper
{
	public static int tomeGuiScale = 0;
	
	public static void save() throws IOException
	{
	
	}
	
	public static void saveSafe()
	{
		try
		{
			save();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
