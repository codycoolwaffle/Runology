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
package ccw.wafflekingdom.runology.client.core.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraftforge.fml.common.Loader;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import ccw.wafflekingdom.runology.api.internal.ShaderCallback;
import ccw.wafflekingdom.runology.client.core.handler.ClientTickHandler;
import ccw.wafflekingdom.runology.common.Runology;
import ccw.wafflekingdom.runology.common.core.handler.ConfigHandler;

public final class ShaderHelper
{
	private static final int VERT = ARBVertexShader.GL_VERTEX_SHADER_ARB;
	private static final int FRAG = ARBFragmentShader.GL_FRAGMENT_SHADER_ARB;
	
	public static int filmGrain = 0;
	public static int gold = 0;
	public static int categoryButton = 0;
	public static int alpha = 0;
	
	private static boolean hasIncompatibleMods = false;
	private static boolean checkedIncompatibility = false;
	
	private static void deleteShader(int id)
	{
		if(id != 0)
		{
			ARBShaderObjects.glDeleteObjectARB(id);
		}
	}
	
	public static void registerReloadListener()
	{
		if(Minecraft.getMinecraft().getResourceManager() instanceof SimpleReloadableResourceManager)
		{
			((SimpleReloadableResourceManager) Minecraft.getMinecraft().getResourceManager())
					.registerReloadListener(manager -> {
						deleteShader(filmGrain); filmGrain = 0; deleteShader(gold); gold = 0;
						deleteShader(categoryButton); categoryButton = 0; deleteShader(alpha);
						alpha = 0;
						
						initShaders();
					});
		}
	}
	
	public static void initShaders()
	{
		if(!useShaders())
			return;
		/*
		filmGrain = createProgram(null, LibResources.SHADER_FILM_GRAIN_FRAG);
		gold = createProgram(null, LibResources.SHADER_GOLD_FRAG);
		categoryButton = createProgram(null, LibResources.SHADER_CATEGORY_BUTTON_FRAG);
		alpha = createProgram(LibResources.SHADER_ALPHA_VERT, LibResources.SHADER_ALPHA_FRAG);*/
	}
	
	public static void useShader(int shader, ShaderCallback callback)
	{
		if(!useShaders())
			return;
		
		ARBShaderObjects.glUseProgramObjectARB(shader);
		
		if(shader != 0)
		{
			int time = ARBShaderObjects.glGetUniformLocationARB(shader, "time");
			ARBShaderObjects.glUniform1iARB(time, ClientTickHandler.ticksInGame);
			
			if(callback != null)
				callback.call(shader);
		}
	}
	
	public static void useShader(int shader)
	{
		useShader(shader, null);
	}
	
	public static void releaseShader()
	{
		useShader(0);
	}
	
	public static boolean useShaders()
	{
		return ConfigHandler.useShaders && OpenGlHelper.shadersSupported && checkIncompatibleMods();
	}
	
	private static boolean checkIncompatibleMods()
	{
		if(!checkedIncompatibility)
		{
			hasIncompatibleMods = Loader.isModLoaded("albedo") || Loader.isModLoaded("optifine");
			checkedIncompatibility = true;
		}
		
		return !hasIncompatibleMods;
	}
	
	// Most of the code taken from the LWJGL wiki
	// http://lwjgl.org/wiki/index.php?title=GLSL_Shaders_with_LWJGL
	
	private static int createProgram(String vert, String frag)
	{
		int vertId = 0, fragId = 0, program; if(vert != null)
		vertId = createShader(vert, VERT); if(frag != null)
		fragId = createShader(frag, FRAG);
		
		program = ARBShaderObjects.glCreateProgramObjectARB(); if(program == 0)
		return 0;
		
		if(vert != null)
			ARBShaderObjects.glAttachObjectARB(program, vertId); if(frag != null)
		ARBShaderObjects.glAttachObjectARB(program, fragId);
		
		ARBShaderObjects.glLinkProgramARB(program); if(ARBShaderObjects
				                                               .glGetObjectParameteriARB(program,
				                                                                         ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB)
		                                               == GL11.GL_FALSE)
	{
		Runology.LOGGER.error(getLogInfo(program)); return 0;
	}
		
		ARBShaderObjects.glValidateProgramARB(program); if(ARBShaderObjects
				                                                   .glGetObjectParameteriARB(
						                                                   program,
						                                                   ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB)
		                                                   == GL11.GL_FALSE)
	{
		Runology.LOGGER.error(getLogInfo(program)); return 0;
	}
		
		return program;
	}
	
	private static int createShader(String filename, int shaderType)
	{
		int shader = 0; try
	{
		shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
		
		if(shader == 0)
			return 0;
		
		ARBShaderObjects.glShaderSourceARB(shader, readFileAsString(filename));
		ARBShaderObjects.glCompileShaderARB(shader);
		
		if(ARBShaderObjects
				   .glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB)
		   == GL11.GL_FALSE)
			throw new RuntimeException("Error creating shader: " + getLogInfo(shader));
		
		return shader;
	}
	catch(Exception e)
	{
		ARBShaderObjects.glDeleteObjectARB(shader); e.printStackTrace(); return -1;
	}
	}
	
	private static String getLogInfo(int obj)
	{
		return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects
				.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
	}
	
	private static String readFileAsString(String filename) throws Exception
	{
		InputStream in = ShaderHelper.class.getResourceAsStream(filename);
		
		if(in == null)
			return "";
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8")))
		{
			return reader.lines().collect(Collectors.joining("\n"));
		}
	}
}
