package com.bobsgame;


import static org.lwjgl.opengl.ARBFramebufferObject.*;
import static org.lwjgl.opengl.EXTFramebufferObject.*;


import static org.lwjgl.opengl.ARBFragmentShader.*;
import static org.lwjgl.opengl.ARBVertexShader.*;
import static org.lwjgl.opengl.ARBShaderObjects.*;


import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL20.*;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.OpenGLException;
import org.lwjgl.opengl.Util;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.bobsgame.client.engine.game.nd.ND;
import com.bobsgame.shared.BobColor;
import com.bobsgame.shared.Utils;

import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;


//=========================================================================================================================
public class LWJGLUtils
{//=========================================================================================================================


	public static Logger log = (Logger) LoggerFactory.getLogger(LWJGLUtils.class);



	//=========================================================================================================================
	public LWJGLUtils()
	{//=========================================================================================================================



	}





	//=========================================================================================================================
	/**
	 * Set the display mode to be used
	 *
	 * @param width
	 *            The width of the display required
	 * @param height
	 *            The height of the display required
	 * @param fullscreen
	 *            True if we want fullscreen mode
	 */
	public static void setFullscreenCompatibleDisplayMode(int width, int height, boolean fullscreen)
	{//=========================================================================================================================

		//return if requested DisplayMode is already set
		if (
				(Display.getDisplayMode().getWidth() == width)
				&&(Display.getDisplayMode().getHeight() == height)
				&&(Display.isFullscreen() == fullscreen)
		)
		{
			return;
		}

		try
		{
			DisplayMode targetDisplayMode = null;

			//if(fullscreen)
			{
				DisplayMode[] modes = Display.getAvailableDisplayModes();

				int freq = 0;
				for(int i = 0; i < modes.length; i++)
				{
					DisplayMode current = modes[i];

					if(
							(current.getWidth() == width)
							&&(current.getHeight() == height)
					)
					{
						if(
								(targetDisplayMode == null)
								||(current.getFrequency() >= freq)
						)
						{
							if(
									(targetDisplayMode == null)
									||(current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())
							)
							{
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}

						//if we've found a match for bpp and frequence against the original display mode
						//then it's probably best to go for this one since it's most likely compatible with the monitor
						if (
								(current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel())
								&&(current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())
						)
						{
							targetDisplayMode = current;
							break;
						}
					}
				}
			}
			//else
			//{
				//targetDisplayMode = new DisplayMode(width, height);
			//}

			if (targetDisplayMode == null)
			{
				log.warn("Could not find video mode : " + width + "x" + height + " fullscreen: " + fullscreen);
				return;
			}

			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);

		}
		catch (LWJGLException e)
		{
			log.warn("Could not set video mode: " + width + "x" + height + " fullscreen: " + fullscreen + e);
		}
	}






	static public int SCREEN_SIZE_X = 1280;
	static public int SCREEN_SIZE_Y = 720;

	static public boolean vsync=true;


	static public boolean useShader=true;
	static public int lightShader=0;
	static public int colorShader=0;

	static public ArrayList<Integer> bgShaders = new ArrayList<Integer>();

	static public int bgShaderCount = 61;

	static public int gaussianShader=0;
	static public int maskShader=0;
	static public int bloomShader=0;



	static public boolean useFBO = true;

	static public int mainFBO_lightTexture;
	static public int mainFBO_Texture;

	static public int nDFBO_Texture;
	static public int nDFBO_MaskTexture;

	static public int nDBloomFBO_Texture_0;
	static public int nDBloomFBO_Texture_1;

	static public int nDBGFBO_Texture_0;
	static public int nDBGFBO_Texture_1;
	//static public int miniMapFBOTextureID;

	static public int mainFBO;
	static public int nDFBO;
	static public int nDBloomFBO;
	static public int nDBGFBO;
	//static public int miniMapFBOID;

	static public int nDBGFBOWidth = 640;
	static public int nDBGFBOHeight = 480;


	//public int lightFBOID;
	//public int depthRenderBufferID;


	//private AWTGLCanvas canvas;

	static public int desktopDisplayWidth = 0;
	static public int desktopDisplayHeight = 0;
	static public int desktopDisplayBPP = 0;
	static public int desktopDisplayFreq = 0;


	//=========================================================================================================================
	public static void setDisplayMode()
	{//=========================================================================================================================


		DisplayMode[] modes = null;

		try
		{
			modes=Display.getAvailableDisplayModes();
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
		}



		for(int i=0;i<modes.length;i++)
		{
			DisplayMode m = modes[i];

			//log.debug(m.getWidth() + "x" + m.getHeight() + " BPP: " + m.getBitsPerPixel() + " Frequency: " + m.getFrequency() + "Hz");
		}

		//TODO: get the browser window/current frame size using javascript
		//set the applet size to this using javascript
		//set the windowed display to this

		//get list of display modes
		//get desktop resolution
		desktopDisplayWidth = Display.getDesktopDisplayMode().getWidth();
		desktopDisplayHeight = Display.getDesktopDisplayMode().getHeight();
		desktopDisplayBPP = Display.getDesktopDisplayMode().getBitsPerPixel();
		desktopDisplayFreq = Display.getDesktopDisplayMode().getFrequency();

		int halfSizeWidth = desktopDisplayWidth/2;
		int halfSizeHeight = desktopDisplayHeight/2;


		//get monitor resolution ??

		//get cpu, gpu, ram
		//if fast enough, set to full resolution fullscreen

		//if not so fast, set to half resolution fullscreen

		//***********
		//using the "normal" LWJGL "new DisplayMode()" does not validate this mode as being fullscreen compatible as it has no bpp or freq set.
		//to make a fullscreen supported DisplayMode you MUST use one of the modes from getAvailableDisplayModes()
		//***********
		//Display.setDisplayMode(new DisplayMode(SCREEN_SIZE_X,SCREEN_SIZE_Y));
		setFullscreenCompatibleDisplayMode(SCREEN_SIZE_X, SCREEN_SIZE_Y, false);
		//Display.setFullscreen(true);

		//boolean useHalfSizeRes = true;

		//if(useHalfSizeRes)setFullscreenCompatibleDisplayMode(halfSizeWidth, halfSizeHeight, false);

		//SCREEN_SIZE_X = halfSizeWidth;
		//SCREEN_SIZE_Y = halfSizeHeight;

		//else
		//setFullscreenCompatibleDisplayMode(desktopDisplayWidth, desktopDisplayHeight, false);
		//Display.setFullscreen(true);


	}




	//=========================================================================================================================
	public static void setViewport()
	{//=========================================================================================================================
		glViewport(0, 0, SCREEN_SIZE_X, SCREEN_SIZE_Y);
		glLoadIdentity();
		glOrtho(0, SCREEN_SIZE_X, SCREEN_SIZE_Y, 0, -1, 1);
	}

	//=========================================================================================================================
	public static void initGL(String windowName)
	{//=========================================================================================================================


		//-----------------------------------
		//opens another frame window, could be used as a debug console or something!
		//-----------------------------------
		/*setTitle("Canvas Test");
		setSize(640, 480);
		try
		{
			canvas = new AWTGLCanvas();
		}
		catch (LWJGLException e1)
		{
			e1.printStackTrace();
		}

		canvas.setSize(640, 480);
		add(canvas);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				ClientMain.exit();
			}
		});

		setResizable(true);
		pack();
		setVisible(true);*/

		try
		{





			Display.setResizable(true);
			//TODO: we want vsync be default in fullscreen, or if we can detect aero is off (win 7 no accel, windows xp, linux, mac)


			//TODO: if we are a window, we want 1280x900 or whatever, resizeable.
			//if we are an applet, we want to resize based on the applet size, which is resized by javascript

			//if we go fullscreen, we want to go full or half monitor resolution and turn on vsync

			if(vsync==true)Display.setVSyncEnabled(true);
			Display.setTitle(windowName);//ULTRA \"bob's game\" ONLINE");//world of \"bob's game\"");



			//if we want to disable deprecated GL functions below GL 3.3
//			ContextAttribs ctxAttr = new ContextAttribs(3, 3);
//			ctxAttr = ctxAttr.withForwardCompatible(true);
//			ctxAttr = ctxAttr.withProfileCore(true);
//			ctxAttr = ctxAttr.withProfileCompatibility(false);
//			ctxAttr = ctxAttr.withDebug(true);

			Display.create();//new PixelFormat(),ctxAttr);


			log.info("Setting up GL...");

			log.info("GL vendor: "+glGetString(GL_VENDOR));
			log.info("GL version: "+glGetString(GL_VERSION));
			log.info("Renderer: "+glGetString(GL_RENDERER));
			log.info("Shader version: "+glGetString(GL_SHADING_LANGUAGE_VERSION));
			log.info("Extensions: "+glGetString(GL_EXTENSIONS));

			//Display.setSwapInterval(120);


			//if (isApplication) {
				//Mouse.setGrabbed(true);
			//}




			//glEnable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			//glEnable(GL_LINE_SMOOTH);
			//glShadeModel(GL_SMOOTH);


			//glColor4f(0.0f,0.0f,0.0f,0.0f);
			//glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
			//glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			//LWJGLUtils.setBlendMode(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			//glClearDepth(1);



			glDisable(GL_SCISSOR_TEST);
			glDisable(GL_DEPTH_TEST);
			glDisable(GL_LIGHTING);

			//glPushAttrib(GL_ENABLE_BIT|GL_TRANSFORM_BIT|GL_HINT_BIT|GL_COLOR_BUFFER_BIT|GL_SCISSOR_BIT|GL_LINE_BIT|GL_TEXTURE_BIT);

			//glMatrixMode(GL_PROJECTION);
			//glPushMatrix();
			//glLoadIdentity();

			setViewport();

			//glMatrixMode(GL_MODELVIEW);
			//glPushMatrix();
			//glLoadIdentity();
			//glViewport(0, 0, SCREEN_SIZE_X, SCREEN_SIZE_Y);


			log.info("Setting up FBO...");


			// check if GL_ARB_framebuffer_object can be use on this system

			if(GLContext.getCapabilities().GL_EXT_framebuffer_object)
			{
				useFBO = true;
				ARBFBO = false;

				log.info("EXT FBO supported.");
			}
			else
			if(GLContext.getCapabilities().GL_ARB_framebuffer_object)
			{
				useFBO = true;
				ARBFBO = true;

				log.info("EXT FBO not supported. Using ARB FBO.");
			}

			else
			{



				log.error("FBO not supported.");
				useFBO=false;

				new GLUtils();


				GLUtils.drawFilledRect(0, 0, 0, 0, Display.getWidth(), 0, Display.getHeight(), 0.5f);
				GLUtils.drawOutlinedString("Your graphics card is not supported yet. (Needs FBO support.)", Display.getWidth()/2-100, Display.getHeight()/2-12, BobColor.white);

				Display.update();

				try
				{
					Thread.sleep(5000);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}


				ClientMain.exit();

			}






			{

				//generate FBOs
				mainFBO = LWJGLUtils.genFBO();
				LWJGLUtils.bindFBO(mainFBO);


				mainFBO_Texture = LWJGLUtils.setupFBOTexture(glGenTextures(),(int)(SCREEN_SIZE_X), (int)(SCREEN_SIZE_Y));
				LWJGLUtils.attachTextureToFBO(0,mainFBO_Texture);


				mainFBO_lightTexture = LWJGLUtils.setupFBOTexture(glGenTextures(),(int)(SCREEN_SIZE_X), (int)(SCREEN_SIZE_Y));
				LWJGLUtils.attachTextureToFBO(1, mainFBO_lightTexture);



				//=================================



				nDFBO = LWJGLUtils.genFBO();
				LWJGLUtils.bindFBO(nDFBO);


				nDFBO_Texture = LWJGLUtils.setupFBOTexture(glGenTextures(),ND.SCREEN_SIZE_X*ND.FBO_SCALE_MULTIPLIER, ND.SCREEN_SIZE_Y*ND.FBO_SCALE_MULTIPLIER);
				LWJGLUtils.attachTextureToFBO(0, nDFBO_Texture);


				nDFBO_MaskTexture = LWJGLUtils.setupFBOTexture(glGenTextures(),ND.SCREEN_SIZE_X*ND.FBO_SCALE_MULTIPLIER, ND.SCREEN_SIZE_Y*ND.FBO_SCALE_MULTIPLIER);
				LWJGLUtils.attachTextureToFBO(1, nDFBO_MaskTexture);



				//=================================


				nDBloomFBO = LWJGLUtils.genFBO();
				LWJGLUtils.bindFBO(nDBloomFBO);

				nDBloomFBO_Texture_0 = LWJGLUtils.setupFBOTexture(glGenTextures(),(int)(ND.SCREEN_SIZE_X*ND.FBO_SCALE_MULTIPLIER*ND.BLOOM_FBO_SCALE), (int)(ND.SCREEN_SIZE_Y*ND.FBO_SCALE_MULTIPLIER*ND.BLOOM_FBO_SCALE));
				LWJGLUtils.attachTextureToFBO(0, nDBloomFBO_Texture_0);


				nDBloomFBO_Texture_1 = LWJGLUtils.setupFBOTexture(glGenTextures(),(int)(ND.SCREEN_SIZE_X*ND.FBO_SCALE_MULTIPLIER*ND.BLOOM_FBO_SCALE), (int)(ND.SCREEN_SIZE_Y*ND.FBO_SCALE_MULTIPLIER*ND.BLOOM_FBO_SCALE));
				LWJGLUtils.attachTextureToFBO(1, nDBloomFBO_Texture_1);



				//=================================


				nDBGFBO = LWJGLUtils.genFBO();
				LWJGLUtils.bindFBO(nDBGFBO);

				nDBGFBO_Texture_0 = LWJGLUtils.setupFBOTexture(glGenTextures(),nDBGFBOWidth,nDBGFBOHeight);
				LWJGLUtils.attachTextureToFBO(0, nDBGFBO_Texture_0);

				nDBGFBO_Texture_1 = LWJGLUtils.setupFBOTexture(glGenTextures(),nDBGFBOWidth,nDBGFBOHeight);
				LWJGLUtils.attachTextureToFBO(1, nDBGFBO_Texture_1);



				//bind the depth renderbuffer
				//glBindRenderbuffer(GL_RENDERBUFFER, depthRenderBufferID);
				//get the data space for it
				//glRenderbufferStorage(GL_RENDERBUFFER, GL14.GL_DEPTH_COMPONENT24, 512, 512);
				//bind it to the renderbuffer
				//glFramebufferRenderbuffer(GL_FRAMEBUFFER,GL_DEPTH_ATTACHMENT,GL_RENDERBUFFER, depthRenderBufferID);

				//switch back to normal framebuffer
				LWJGLUtils.bindFBO(0);
			}

			e();

			log.info("Setting up shaders...");


			String glVersion = glGetString(GL_VERSION);
			//String shaderVersion = glGetString(GL_SHADING_LANGUAGE_VERSION);
			//String glExtensions = glGetString(GL_EXTENSIONS);

			boolean hasARBShadingLanguage100 = GLContext.getCapabilities().GL_ARB_shading_language_100;
			boolean hasARBFragmentShader = GLContext.getCapabilities().GL_ARB_fragment_shader;
			boolean hasARBVertexShader = GLContext.getCapabilities().GL_ARB_vertex_shader;
			boolean hasARBShaderObjects = GLContext.getCapabilities().GL_ARB_shader_objects;
			boolean hasARBFragmentProgram = GLContext.getCapabilities().GL_ARB_fragment_program;
			boolean hasNVVertexProgram3 = GLContext.getCapabilities().GL_NV_vertex_program3;
			boolean hasNVGPUProgram4 = GLContext.getCapabilities().GL_NV_gpu_program4;

//			boolean extensionsStringHasARBShadingLanguage = glExtensions.contains("GL_ARB_shading_language_100");
//			boolean extensionsStringHasARBFragmentShader = glExtensions.contains("GL_ARB_fragment_shader");
//			boolean extensionsStringHasARBVertexShader = glExtensions.contains("GL_ARB_vertex_shader");
//			boolean extensionsStringHasARBShaderObjects = glExtensions.contains("GL_ARB_shader_objects");
//			boolean extensionsStringHasARBFragmentProgram = glExtensions.contains("GL_ARB_fragment_program");
//			boolean extensionsStringHasNVVertexProgram3 = glExtensions.contains("GL_NV_vertex_program3");
//			boolean extensionsStringHasNVGPUProgram4 = glExtensions.contains("GL_NV_gpu_program4");

			int glVersionMajor = 0;
			glVersionMajor = Integer.parseInt(glVersion.substring(0,glVersion.indexOf(".")));

//			log.info("glVersion:"+glVersion);
//			log.info("shaderVersion:"+shaderVersion);
//			log.info("glExtensions:"+glExtensions);
			log.info("glVersionMajor:"+glVersionMajor);
			log.info("Shader version: "+glGetString(GL_SHADING_LANGUAGE_VERSION));

			log.info("hasARBShadingLanguage100 (GLSL 1.00):"+hasARBShadingLanguage100);
			log.info("hasARBFragmentShader (GLSL 1.00):"+hasARBFragmentShader);
			log.info("hasARBVertexShader (GLSL 1.00):"+hasARBVertexShader);
			log.info("hasARBShaderObjects (GLSL 1.00):"+hasARBShaderObjects);
			log.info("shadingVersionExists (GLSL 1.051):"+(glGetString(GL_SHADING_LANGUAGE_VERSION).equals("0")==false));

			log.info("hasARBFragmentProgram (SM 2):"+hasARBFragmentProgram);
			log.info("hasNVVertexProgram3 (SM 3):"+hasNVVertexProgram3);
			log.info("hasNVGPUProgram4 (SM 4):"+hasNVGPUProgram4);
//			log.info("extensionsStringHasARBShadingLanguage:"+extensionsStringHasARBShadingLanguage);
//			log.info("extensionsStringHasARBFragmentShader:"+extensionsStringHasARBFragmentShader);
//			log.info("extensionsStringHasARBVertexShader:"+extensionsStringHasARBVertexShader);
//			log.info("extensionsStringHasARBShaderObjects:"+extensionsStringHasARBShaderObjects);
//			log.info("extensionsStringHasARBFragmentProgram:"+extensionsStringHasARBFragmentProgram);
//			log.info("extensionsStringHasNVVertexProgram3:"+extensionsStringHasNVVertexProgram3);
//			log.info("extensionsStringHasNVGPUProgram4:"+extensionsStringHasNVGPUProgram4);


			if(glVersionMajor<2)
			{
				useShader=false;
			}

			if(useShader)
			{
				lightShader = LWJGLUtils.createProgramObject();
				colorShader = LWJGLUtils.createProgramObject();
				gaussianShader = LWJGLUtils.createProgramObject();
				maskShader = LWJGLUtils.createProgramObject();
				bloomShader = LWJGLUtils.createProgramObject();

				if(LWJGLUtils.makeShader("lightShader",lightShader,"res/shaders/texCoord.vert","res/shaders/lightBlend.frag")==false)useShader=false;
				if(LWJGLUtils.makeShader("colorShader",colorShader,"res/shaders/texCoord.vert","res/shaders/colorAdjust.frag")==false)useShader=false;
				if(LWJGLUtils.makeShader("gaussianShader",gaussianShader,"res/shaders/bloom_blurspace.vert","res/shaders/bloom_alpha_gaussian.frag")==false)useShader=false;
				if(LWJGLUtils.makeShader("maskShader",maskShader,"res/shaders/bloom_screenspace.vert","res/shaders/bloom_alpha_threshold.frag")==false)useShader=false;
				if(LWJGLUtils.makeShader("bloomShader",bloomShader,"res/shaders/bloom_screenspace.vert","res/shaders/bloom_alpha_bloom.frag")==false)useShader=false;


				if(useShader)
				{

					for(int i=0;i<bgShaderCount;i++)
					{
						int p = LWJGLUtils.createProgramObject();
						bgShaders.add(new Integer(p));
					}

					int count = 0;

					for(int i=0;i<bgShaderCount;i++)
					{
						String name = count+".frag";
						if(count<10)name = "0"+name;

						if(LWJGLUtils.makeShader(name,bgShaders.get(i).intValue(),"res/shaders/texCoord.vert","res/shaders/bg/"+name)==false)
						{
							bgShaderCount--;
							bgShaders.remove(i);
							i--;
						}

						count++;
					}

				}

			}
			else
			{
				useShader=false;
			}


			if(useShader==false)log.warn("Shaders not supported.");


		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
			ClientMain.exit();
		}

		log.info("GL Complete.");

	}




	//=========================================================================================================================
	private static void attachTextureToFBO(int attachment, int textureID)
	{//=========================================================================================================================

		if(ARBFBO)
		{
			if(attachment==0)glFramebufferTexture2D(GL_FRAMEBUFFER,GL_COLOR_ATTACHMENT0,GL_TEXTURE_2D, textureID, 0);
			else glFramebufferTexture2D(GL_FRAMEBUFFER,GL_COLOR_ATTACHMENT1,GL_TEXTURE_2D, textureID, 0);
		}
		else
		{
			if(attachment==0)glFramebufferTexture2DEXT(GL_FRAMEBUFFER,GL_COLOR_ATTACHMENT0_EXT,GL_TEXTURE_2D, textureID, 0);
			glFramebufferTexture2DEXT(GL_FRAMEBUFFER,GL_COLOR_ATTACHMENT1_EXT,GL_TEXTURE_2D, textureID, 0);
		}
	}



	public static boolean ARBFBO = false;

	//=========================================================================================================================
	public static void bindFBO(int fboID)
	{//=========================================================================================================================

		if(ARBFBO)
		{
			glBindFramebuffer(GL_FRAMEBUFFER, fboID);
		}
		else
		{
			glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, fboID);
		}

	}


	//=========================================================================================================================
	public static void drawIntoFBOAttachment(int i)
	{//=========================================================================================================================
		if(ARBFBO)
		{
			if(i==0)glDrawBuffer(GL_COLOR_ATTACHMENT0);
			else glDrawBuffer(GL_COLOR_ATTACHMENT1);
		}
		else
		{
			if(i==0)glDrawBuffer(GL_COLOR_ATTACHMENT0_EXT);
			else glDrawBuffer(GL_COLOR_ATTACHMENT1_EXT);
		}

	}


	//=========================================================================================================================
	public static int genFBO()
	{//=========================================================================================================================
		if(ARBFBO)
		{
			return glGenFramebuffers();
		}
		else
		{
			return glGenFramebuffersEXT();
		}
	}

	//=========================================================================================================================
	public static int setupFBOTexture(int tex, int width, int height)
	{//=========================================================================================================================

		//init FBO texture
		glBindTexture(GL_TEXTURE_2D, tex);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, (java.nio.ByteBuffer) null);

		return tex;
	}



	public static boolean ARBShader = false;

	//=========================================================================================================================
	public static int createProgramObject()
	{//=========================================================================================================================
		int i = 0;

		if(useShader==true)
		{

			if(ARBShader==false)
			{
				i = glCreateProgram();e();
				if(i==0)
				{
					ARBShader=true;
					log.error("Core shaders failed. Trying ARB shaders.");
				}

			}

			if(ARBShader==true)
			{
				i = glCreateProgramObjectARB();e();
				if(i==0)
				{
					useShader=false;
					log.error("ARB shaders failed. Using no shaders.");
				}
			}



		}


		return i;

	}


	public static final int FRAG=0;
	public static final int VERT=1;

	//=========================================================================================================================
	private static int compileShaderObject(String filename, int type)
	{//=========================================================================================================================
		//will be non zero if successfully created
		int shader = 0;


		if(type==FRAG)
		{
			if(ARBShader)shader=glCreateShaderObjectARB(GL_FRAGMENT_SHADER_ARB);
			else shader = glCreateShader(GL_FRAGMENT_SHADER);
		}
		else
		{
			if(ARBShader)shader=glCreateShaderObjectARB(GL_VERTEX_SHADER_ARB);
			else shader = glCreateShader(GL_VERTEX_SHADER);

		}

		e();

		//if created, convert the shader code to a String
		if(shader==0){return 0;}

		String code="";
		String line;
		try
		{
			BufferedInputStream inputStream = new BufferedInputStream(Utils.getResourceAsStream(filename));
			//InputStream inputStream = Utils.getResourceAsStream(filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

			//BufferedReader reader=new BufferedReader(new FileReader(filename));
			while((line=reader.readLine())!=null)
			{
				code+=line + "\n";
			}
			reader.close();
		}
		catch(Exception e)
		{
			log.error("Could not read code: "+filename);
			return 0;
		}



		if(ARBShader)
		{
			glShaderSourceARB(shader, code);e();
			glCompileShaderARB(shader);e();

			//if there was a problem compiling, reset to zero
			if(glGetObjectParameteriARB(shader, GL_OBJECT_COMPILE_STATUS_ARB) == GL_FALSE)
			{
				e();

				int logLength = glGetObjectParameteriARB(shader,GL_OBJECT_INFO_LOG_LENGTH_ARB);e();
				if(logLength > 0)
				{
					String out = glGetInfoLogARB(shader, 1024);e();
					if(out.length()>0)out = out.substring(0,out.length()-1);//remove extra newline
					log.error("ShaderInfoLogARB: "+out);
				}

				shader=0;
			}
		}
		else
		{
			glShaderSource(shader, code);e();
			glCompileShader(shader);e();

			//if there was a problem compiling, reset to zero
			if(glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE)
			{
				e();

				int logLength = glGetShaderi(shader, GL_INFO_LOG_LENGTH);e();
				if(logLength > 0)
				{
					String out = glGetShaderInfoLog(shader, 1024);e();
					if(out.length()>0)out = out.substring(0,out.length()-1);//remove extra newline
					log.error("ShaderInfoLog: "+out);

				}

				shader=0;
			}
		}



		//if zero we won't be using the shader
		return shader;
	}




	//=========================================================================================================================
	public static boolean makeShader(String name, int shaderProgram, String vertPath, String fragPath)
	{//=========================================================================================================================

		int vertShader = 0;
		int fragShader = 0;

		vertShader=LWJGLUtils.compileShaderObject(vertPath,VERT);
		fragShader=LWJGLUtils.compileShaderObject(fragPath,FRAG);

		if(vertShader != 0 && fragShader != 0)
		{
			if(ARBShader)
			{
				glAttachObjectARB(shaderProgram, vertShader);e();
				glAttachObjectARB(shaderProgram, fragShader);e();
				glLinkProgramARB(shaderProgram);e();
				glValidateProgramARB(shaderProgram);e();
			}
			else
			{

				glAttachShader(shaderProgram, vertShader);e();
				glAttachShader(shaderProgram, fragShader);e();
				glLinkProgram(shaderProgram);e();
				glValidateProgram(shaderProgram);e();
			}



			String out = "";

			if(ARBShader)
			{
				int logLength = glGetObjectParameteriARB(shaderProgram, GL_OBJECT_INFO_LOG_LENGTH_ARB);e();
				if(logLength > 0)
				{
					out = glGetInfoLogARB(shaderProgram, 1024);e();
					if(out.length()>0)out = out.substring(0,out.length()-1);//remove extra newline
					out = ("ProgramInfoLog: "+out);
				}
			}
			else
			{
				int logLength = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);e();
				if(logLength > 0)
				{
					out = glGetProgramInfoLog(shaderProgram, 1024);e();
					if(out.length()>0)out = out.substring(0,out.length()-1);//remove extra newline
					out = ("ProgramInfoLog: "+out);
				}
			}

			if(out.toLowerCase().contains("error"))//startsWith("Validation successful")==false && status.startsWith("Validation warning! - Sampler")==false)
			{
				log.warn(name+" status: "+out);
				return false;
			}
			else
			return true;


		}
		else
		{
			log.error(name+" did not compile!");
			return false;
		}

	}

	public static void e()
	{
		try{Util.checkGLError();}catch(OpenGLException e){e.printStackTrace();}
	}

	//=========================================================================================================================
	public static void useShader(int shader)
	{//=========================================================================================================================

		//if(useShader==false)return;

		if(ARBShader)
		{
			glUseProgramObjectARB(shader);
		}
		else
		{
			glUseProgram(shader);
		}

	}

	//=========================================================================================================================
	static public LWJGLRenderer TWLrenderer = null;
	static public ThemeManager TWLthemeManager = null;
	//=========================================================================================================================
	public static void initTWL()
	{//=========================================================================================================================

		log.info("Init TWL...");

		try
		{

			Mouse.destroy();

			TWLrenderer = new LWJGLRenderer();

			Mouse.create();

		}
		catch (LWJGLException e1)
		{
			e1.printStackTrace();
		}


		log.info("Load Theme...");
		try
		{
			TWLthemeManager = ThemeManager.createThemeManager(Utils.getResource("res/theme/themetest.xml"), TWLrenderer);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		log.info("Theme Loaded.");

	}




	//=========================================================================================================================
	static public int numControllers = 0;
	static public String controllerNames = "";

	//=========================================================================================================================
	public static void initControllers()
	{//=========================================================================================================================

		log.info("Init Controllers...");
		try
		{
			Controllers.create();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		numControllers = Controllers.getControllerCount();
		log.info(numControllers+" Controllers Found");

		for(int n=0;n<numControllers;n++)
		{
			Controller controller = Controllers.getController(n);
			log.info(controller.getName());

			if(controllerNames.length()>0)controllerNames = controllerNames.concat(","+controller.getName());
			else controllerNames = controllerNames.concat(controller.getName());


			for(int i=0;i<controller.getButtonCount();i++)
			{

			}
			for(int i=0;i<controller.getAxisCount();i++)
			{
				//controller.setDeadZone(i, -1.0f);
			}
		}

		log.info("Controllers Loaded.");
	}


	//=========================================================================================================================
	public static void doResize()
	{//=========================================================================================================================

		SCREEN_SIZE_X = Display.getWidth();
		SCREEN_SIZE_Y = Display.getHeight();


		//SCREEN_SIZE_X = getWidth();
		//SCREEN_SIZE_Y = getHeight();

		//glMatrixMode(GL_MODELVIEW);//worldview transform (how far from 0,0,0 are we)
		//glLoadIdentity();//reset selected transform matrix
		//glMatrixMode(GL_PROJECTION);//perspective transform matrix (for 3f->2f perspective, etc)

		//order does matter
		glLoadIdentity();//reset selected transform matrix
		glOrtho(0, SCREEN_SIZE_X, SCREEN_SIZE_Y, 0, -1, 1);

		glViewport(0, 0, SCREEN_SIZE_X, SCREEN_SIZE_Y);//order doesn't matter



		//glMatrixMode(GL_MODELVIEW);
		//glPushMatrix();


		//fix FBO sizes

		//switch to the new framebuffer
		LWJGLUtils.bindFBO(mainFBO);

		mainFBO_Texture = LWJGLUtils.setupFBOTexture(mainFBO_Texture,(int)(SCREEN_SIZE_X), (int)(SCREEN_SIZE_Y));
		LWJGLUtils.attachTextureToFBO(0, mainFBO_Texture);


		mainFBO_lightTexture = LWJGLUtils.setupFBOTexture(mainFBO_lightTexture,(int)(SCREEN_SIZE_X), (int)(SCREEN_SIZE_Y));
		LWJGLUtils.attachTextureToFBO(1,mainFBO_lightTexture);

		//switch back to normal framebuffer
		LWJGLUtils.bindFBO(0);



		TWLrenderer.syncViewportSize();


	}




	//=========================================================================================================================
	public static void setBlendMode(int src,int dst)
	{//=========================================================================================================================

		//if(Keyboard.isKeyDown(Keyboard.KEY_COMMA))glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		//else
		//if(Keyboard.isKeyDown(Keyboard.KEY_PERIOD))glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
		//else
		glBlendFunc(src,dst);
	}




	//=========================================================================================================================
	public static void setShaderVar1i(int shader,String string,int i)
	{//=========================================================================================================================
		if(ARBShader)glUniform1iARB(glGetUniformLocationARB(shader, new StringBuffer(string)), i);
		else glUniform1i(glGetUniformLocation(shader, new StringBuffer(string)), i);

	}

	//=========================================================================================================================
	public static void setShaderVar1f(int shader,String string,float f)
	{//=========================================================================================================================
		if(ARBShader)glUniform1fARB(glGetUniformLocationARB(shader, new StringBuffer(string)), f);
		else glUniform1f(glGetUniformLocation(shader, new StringBuffer(string)), f);

	}




	//=========================================================================================================================
	public static void setShaderVar2f(int shader,String string,float f1,float f2)
	{//=========================================================================================================================
		if(ARBShader)glUniform2fARB(glGetUniformLocationARB(shader, new StringBuffer(string)), f1,f2);
		else glUniform2f(glGetUniformLocation(shader, new StringBuffer(string)), f1,f2);

	}








}
