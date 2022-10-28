package com.bobsgame;




//import java.awt.Frame;
//import java.awt.GraphicsConfiguration;
//import java.awt.GraphicsDevice;
//import java.awt.GraphicsEnvironment;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;



import javax.imageio.ImageIO;
import javax.swing.JApplet;


import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
//import java.awt.event.*;
//import java.util.ArrayList;
//import java.util.Random;
import javax.swing.*;

//import java.util.Timer;

//import java.applet.Applet;
//import java.awt.BorderLayout;
//import java.awt.Canvas;




import net.java.games.input.AWTEnvironmentPlugin;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.DirectAndRawInputEnvironmentPlugin;
import net.java.games.input.DirectInputEnvironmentPlugin;


import netscape.javascript.*;

import org.lwjgl.*;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.*;


//import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
//import static org.lwjgl.opengl.GL12.GL_TEXTURE_WRAP_R;

//import static org.lwjgl.opengl.GL42.*;
//import static org.lwjgl.opengl.GL41.*;
//import static org.lwjgl.opengl.GL40.*;
//import static org.lwjgl.opengl.GL33.*;
//import static org.lwjgl.opengl.GL32.*;
//import static org.lwjgl.opengl.GL31.*;
//import static org.lwjgl.opengl.GL30.*;
//import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL11.*;

//import com.bobsgame.editor.BobColor;
//import org.newdawn.slick.opengl.Texture;
//import org.newdawn.slick.opengl.TextureLoader;
//import org.newdawn.slick.util.ResourceLoader;



import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.EditField;
import de.matthiasmann.twl.Event;
import de.matthiasmann.twl.FPSCounter;
import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.Timer;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;


import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.html.HTMLLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;

import com.bobsgame.client.Cache;
import com.bobsgame.client.ControlsManager;
import com.bobsgame.client.console.*;
import com.bobsgame.client.engine.Engine;
import com.bobsgame.client.engine.cinematics.CinematicsManager;
import com.bobsgame.client.engine.game.ClientGameEngine;
import com.bobsgame.client.engine.game.Player;
import com.bobsgame.client.engine.game.gui.PlayerEditMenu;
import com.bobsgame.client.engine.game.nd.ND;
import com.bobsgame.client.engine.map.Map;
import com.bobsgame.client.engine.map.MapManager;
import com.bobsgame.client.network.GameClientTCP;
import com.bobsgame.client.network.GameClientTCP.BobsGameClientHandler;
import com.bobsgame.client.state.KeyboardScreen;
import com.bobsgame.client.state.CreateNewAccountState;
import com.bobsgame.client.state.GlowTileBackground;
import com.bobsgame.client.state.LegalScreen;
import com.bobsgame.client.state.LoggedOutState;
import com.bobsgame.client.state.LoginScreen;
import com.bobsgame.client.state.LoginState;
import com.bobsgame.client.state.ServersHaveShutDownState;
import com.bobsgame.client.state.State;
import com.bobsgame.client.state.StateManager;
import com.bobsgame.client.state.TitleScreenState;
import com.bobsgame.client.state.YouWillBeNotifiedState;
import com.bobsgame.net.*;
import com.bobsgame.shared.BobColor;
import com.bobsgame.shared.Utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpUtils;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.commons.net.time.TimeTCPClient;
import org.apache.commons.net.time.TimeUDPClient;

import com.bobsgame.audio.AudioUtils;



//=========================================================================================================================
public class ClientMain extends Applet
{//=========================================================================================================================

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;




	public static ClientMain clientMain;



	//=========================================================================================================================
	public ClientMain()
	{//=========================================================================================================================

	}



	//=========================================================================================================================
	public static void main(String[] args)
	{//=========================================================================================================================



		//log.debug("main()");



		//TODO: could download natives into home dir/.bobsGame/native and then set to it for applet and desktop both



		//System.setProperty("org.lwjgl.librarypath","\\res");



/*
		Set these by either passing -Dkey=value as a Java VM option or by calling System.setProperty("key","value"); in your code (in most likely a static block).


		org.lwjgl.librarypath=<full path to native directory>

			Set the path to look for the native libraries in. Example:
			view sourceprint?
			1.System.setProperty("org.lwjgl.librarypath",System.getProperty("user.dir") + File.separator + "native" + File.separator + "os");


		org.lwjgl.opengl.Display.noinput=<true|false>

			Do not initialize any controls when creating the display


		org.lwjgl.opengl.Display.nomouse=<true|false>

			Do not create the mouse when creating the display


		org.lwjgl.opengl.Display.nokeyboard=<true|false>

			Do not create the keyboard when creating the display


		org.lwjgl.util.Debug=<true|false>

			Whether to output debug info


		org.lwjgl.util.NoChecks=<true|false>

			Whether to disable runtime function/buffer checks and state tracking.


		org.lwjgl.opengl.Display.allowSoftwareOpenGL=<true|false>

			Whether to allow creation of a software only opengl context


		org.lwjgl.opengl.Window.undecorated=<true|false>

			Whether to create an undecorated window (no title bar)


		org.lwjgl.input.Mouse.allowNegativeMouseCoords=<true|false>

			Usually mouse is clamped to 0,0 - setting this to true will cause you to get negative values if dragging outside and below or left of window
*/






		//Log.set(Log.LEVEL_DEBUG);

			//AWT EventQueue
//	       EventQueue.invokeLater(new Runnable()
//	       {
//
//	            public void run()
//	            {
//	            	//main
//	            }
//	        });

		//SwingUtilities EventQueue
//		try
//		{
//			SwingUtilities.invokeLater(new Runnable()//Causes doRun.run() to be executed asynchronously on the AWT event dispatching thread. This will happen after all pending AWT events have been processed.
//			//SwingUtilities.invokeAndWait(new Runnable()//Causes doRun.run() to be executed synchronously on the AWT event dispatching thread. This call blocks until all pending AWT events have been processed and (then) doRun.run() returns
//			{
//				@Override
//				public void run()
//				{
//				}
//			});
//		}
//		catch(InterruptedException | InvocationTargetException ex)
//		{
//		}


		try{Thread.currentThread().setName("ClientMain_main");}catch(SecurityException e){e.printStackTrace();}



		clientMain = new ClientMain();


		clientMain.mainInit();

		try
		{
			clientMain.mainLoop();
		}
		catch(Exception e){e.printStackTrace();}

		try
		{
			clientMain.cleanup();
		}
		catch(Exception e){e.printStackTrace();}


		ClientMain.exit();


		//for(int i=0;i<100000;i++)
		//bg.clientUDP.sendDatagramToServer(i);
		//bg.quit();



/*
		new Thread(
				new Runnable()
				{
					public void run()
					{

						try
						{

							ClientMain bg = new ClientMain();
							bg.mainLoop();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}

					}
				}
				).start();

		while(ClientMain.exit==false)
		{
			try
			{
				Thread.sleep(16);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}*/
	}


	//=========================================================================================================================
	//=========================================================================================================================
	//=========================================================================================================================
	// APPLET FUN
	//=========================================================================================================================
	//=========================================================================================================================
	//=========================================================================================================================

	static Canvas appletCanvas = null; //TODO: try using JComponent?


	static int lastWidth = 0;
	static int lastHeight = 0;
	static int lastDisplayWidth = 0;
	static int lastDisplayHeight = 0;
	static int lastAppletCanvasWidth = 0;
	static int lastAppletCanvasHeight = 0;
	static int canvasWidth = 0;
	static int canvasHeight = 0;



	static Thread gameThread = null;
	static Runnable gameRunnable = null;
	public static JSObject browser = null;

	static boolean started = false;



	//=========================================================================================================================
	@Override
	public void init()
	{//=========================================================================================================================

		String host = getCodeBase().toString();
		log.debug(host);

		if(
				host.startsWith("https://bobsgame.com/")==false&&
				host.startsWith("https://www.bobsgame.com/")==false&&
				host.startsWith("http://bobsgame.com/")==false&&
				host.startsWith("http://www.bobsgame.com/")==false&&
				host.startsWith("bobsgame.com/")==false&&
				host.startsWith("www.bobsgame.com/")==false&&
				host.startsWith("http://s3.amazonaws.com/bobsgame/client/")==false&&
				host.startsWith("https://s3.amazonaws.com/bobsgame/client")==false&&
				host.startsWith("s3.amazonaws.com/bobsgame/client/")==false&&
				host.startsWith("bobsgame.s3.amazonaws.com/client/")==false&&
				host.startsWith("http://bobsgame.s3.amazonaws.com/client/")==false&&
				host.startsWith("https://bobsgame.s3.amazonaws.com/client/")==false
		)
			ClientMain.exit();

		//log.error("init()");

	}


	//=========================================================================================================================
	@Override
	public void start()
	{//=========================================================================================================================



//        EventQueue.invokeLater(new Runnable()
//        {
//            public void run()
//            {
//            }
//        });

//      try
//      {
//         SwingUtilities.invokeLater(new Runnable()
//          {
//              @Override
//              public void run()
//              {
//              }
//          });
//      }
//      catch(InterruptedException | InvocationTargetException ex)
//      {
//      }

		//log.error("start()");



		if(started==true)return;

		clientMain = this;


		try{Thread.currentThread().setName("ClientMain_start");}catch(SecurityException e){e.printStackTrace();}
		//DONE: set names on all threads, network, helpers, etc.


		isApplet=true;


		if(BobNet.debugMode==true)setBackground(java.awt.Color.red);
		else setBackground(java.awt.Color.black);

		setLayout(new BorderLayout());
		try
		{
			appletCanvas = new Canvas()
			{
				public final void addNotify()
				{
					super.addNotify();


//					gameThread = new Thread() // or new Thread(new Runnable()
//					{
//						public void run()
//						{
//
//							clientMain.mainInit();
//							clientMain.mainLoop();
//						}
//					};
//					gameThread.start();


					gameRunnable = new Runnable()
					{
						public void run()
						{
							try{Thread.currentThread().setName("ClientMain_mainLoop");}catch(SecurityException e){e.printStackTrace();}

							if(BobNet.debugMode==false)browser = JSObject.getWindow(clientMain);

							started=true;

							mainInit();

							try
							{
								mainLoop();
							}
							catch(Exception e){e.printStackTrace();}

							try
							{
								cleanup();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}

							//System.exit(0);
						}
					};
					//SwingUtilities.invokeLater(gameRunnable);
					//or
					//gameThread = new Thread(gameRunnable,"ClientMain_mainLoop");//this actually make the window not resize!!
					//SwingUtilities.invokeLater(gameThread);
					//or
					gameThread = new Thread(gameRunnable);
					gameThread.start();



/*
public class ThreadA implements Runnable {
	public void run() {
		//Code
	}
}
//with a "new Thread(threadA).start()" call


public class ThreadB extends Thread {
	public ThreadB() {
		super("ThreadB");
	}
	public void run() {
		//Code
	}
}
//with a "threadB.start()" call
 */

				}

			};


			if(BobNet.debugMode==true)appletCanvas.setBackground(java.awt.Color.green);
			else appletCanvas.setBackground(java.awt.Color.black);


			//validate();

			//setSize(500,500);
			appletCanvas.setSize(getWidth(),getHeight());

			add(appletCanvas, BorderLayout.CENTER);

			this.setFocusable(true);
			//this.requestFocus();

			appletCanvas.setFocusable(true);
			appletCanvas.requestFocus();

			appletCanvas.setIgnoreRepaint(true);
			setVisible(true);

			validate();

			canvasWidth = appletCanvas.getWidth();
			canvasHeight = appletCanvas.getHeight();

		}
		catch (Exception e)
		{
			log.error(""+e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Unable to create display");
		}



	}







	//=========================================================================================================================
	@Override
	public void stop()
	{//=========================================================================================================================

		log.debug("stop()");







	}

	//=========================================================================================================================
	@Override
	public void destroy()
	{//=========================================================================================================================



		log.debug("destroy()");

		log.debug("exit=true");
		clientMain.exit=true; //this will end ghost thread, game thread break out of loop, call quit() and exit.
		this.exit = true;

		log.debug("Waiting for gameThread.join()");
		try
		{
			gameThread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		log.debug("gameThread.join() complete.");

		log.debug("Remove canvas");
		remove(appletCanvas);

		super.destroy();

	}
	//=========================================================================================================================
	public static void exit()
	{//=========================================================================================================================
		if(isApplet)clientMain.destroy();
		else System.exit(0);
	}

	//=========================================================================================================================
	//=========================================================================================================================
	//=========================================================================================================================
	//=========================================================================================================================
	//=========================================================================================================================
	//=========================================================================================================================
	//=========================================================================================================================



	//=========================================================================================================================
	public ClientStats clientInfo = new ClientStats();

	//=========================================================================================================================
	public void initClientInfo()
	{//=========================================================================================================================


		Properties systemProperties = System.getProperties();


//		log.debug("Java Runtime Environment version: "+prop.getProperty("java.version"));
//		log.debug("Java Runtime Environment vendor: "+prop.getProperty("java.vendor"));
//		log.debug("Java vendor URL: "+prop.getProperty("java.vendor.url"));
//		log.debug("Java installation directory: "+prop.getProperty("java.home"));
//		log.debug("Java Virtual Machine specification version: "+prop.getProperty("java.vm.specification.version"));
//		log.debug("Java Virtual Machine specification vendor: "+prop.getProperty("java.vm.specification.vendor"));
//		log.debug("Java Virtual Machine specification name: "+prop.getProperty("java.vm.specification.name"));
//		log.debug("Java Virtual Machine implementation version: "+prop.getProperty("java.vm.version"));
//		log.debug("Java Virtual Machine implementation vendor: "+prop.getProperty("java.vm.vendor"));
//		log.debug("Java Virtual Machine implementation name: "+prop.getProperty("java.vm.name"));
//		log.debug("Java Runtime Environment specification version: "+prop.getProperty("java.specification.version"));
//		log.debug("Java Runtime Environment specification vendor: "+prop.getProperty("java.specification.vendor"));
//		log.debug("Java Runtime Environment specification name: "+prop.getProperty("java.specification.name"));
//		log.debug("Java class format version number: "+prop.getProperty("java.class.version"));
//		log.debug("Java class path: "+prop.getProperty("java.class.path"));
//		log.debug("List of paths to search when loading libraries: "+prop.getProperty("java.library.path"));
//		log.debug("Default temp file path: "+prop.getProperty("java.io.tmpdir"));
//		log.debug("Name of JIT compiler to use: "+prop.getProperty("java.compiler"));
//		log.debug("Path of extension directory or directories: "+prop.getProperty("java.ext.dirs"));
//		log.debug("Operating system name: "+prop.getProperty("os.name"));
//		log.debug("Operating system architecture: "+prop.getProperty("os.arch"));
//		log.debug("Operating system version: "+prop.getProperty("os.version"));
//		log.debug("User's account name: "+prop.getProperty("user.name"));
//		log.debug("User's home directory: "+prop.getProperty("user.home"));
//		log.debug("User's current working directory: "+prop.getProperty("user.dir"));
//		log.debug("File separator ('/' on UNIX): "+prop.getProperty("file.separator"));
//		log.debug("Path separator (':' on UNIX): "+prop.getProperty("path.separator"));
//		log.debug("Line separator ('\\n' on UNIX): "+prop.getProperty("line.separator"));




		if(isApplet==true)
		{
			//connect through JSObject and get these from browser
			if(browser!=null)
			{
				clientInfo.browserUserAgentString = (String) browser.eval("getUserAgentString();");
				clientInfo.browserAppNameVersionString = (String) browser.eval("getAppNameString();");
				clientInfo.browserReferrerString = (String) browser.eval("getReferrerString();");
			}

			//Console.debug(clientInfo.browserUserAgentString);
			//Console.debug(clientInfo.browserAppNameVersionString);
			//Console.debug(clientInfo.browserReferrerString);
		}


		//add to DB, add to clientConnection object, add to debug console.
		//log.debug(System.getenv("PROCESSOR_IDENTIFIER"));//will only work for windows!
		//log.debug(System.getenv("PROCESSOR_ARCHITECTURE"));
		//log.debug(System.getenv("PROCESSOR_ARCHITEW6432"));
		//log.debug(System.getenv("NUMBER_OF_PROCESSORS"));

		clientInfo.getEnvProcessorIdentifier = System.getenv("PROCESSOR_IDENTIFIER");
		clientInfo.getEnvProcessorArchitecture = System.getenv("PROCESSOR_ARCHITECTURE");
		clientInfo.getEnvNumberOfProcessors = System.getenv("NUMBER_OF_PROCESSORS");


		clientInfo.jreVersion			= ""+systemProperties.getProperty("java.version");
		clientInfo.jreVendor			= ""+systemProperties.getProperty("java.vendor");
		//clientInfo.jreVendorURL			= ""+systemProperties.getProperty("java.vendor.url");
		clientInfo.jreHomeDir			= ""+systemProperties.getProperty("java.home");
		//clientInfo.jvmSpecVersion		= ""+systemProperties.getProperty("java.vm.specification.version");
		//clientInfo.jvmSpecVendor		= ""+systemProperties.getProperty("java.vm.specification.vendor");
		//clientInfo.jvmSpecName			= ""+systemProperties.getProperty("java.vm.specification.name");
		clientInfo.jvmVersion			= ""+systemProperties.getProperty("java.vm.version");
		//clientInfo.jvmVendor			= ""+systemProperties.getProperty("java.vm.vendor");
		clientInfo.jvmName				= ""+systemProperties.getProperty("java.vm.name");
		//clientInfo.jreSpecVersion		= ""+systemProperties.getProperty("java.specification.version");
		//clientInfo.jreSpecVendor		= ""+systemProperties.getProperty("java.specification.vendor");
		//clientInfo.jreSpecName			= ""+systemProperties.getProperty("java.specification.name");
		clientInfo.javaClassVersion		= ""+systemProperties.getProperty("java.class.version");
		clientInfo.javaClassPath		= ""+systemProperties.getProperty("java.class.path");
		clientInfo.javaLibraryPath		= ""+systemProperties.getProperty("java.library.path");
		clientInfo.javaTempDir			= ""+systemProperties.getProperty("java.io.tmpdir");
		//clientInfo.javaJITCompiler		= ""+systemProperties.getProperty("java.compiler");
		//clientInfo.javaExtensionPath	= ""+systemProperties.getProperty("java.ext.dirs");
		clientInfo.osName				= ""+systemProperties.getProperty("os.name");
		clientInfo.osArch				= ""+systemProperties.getProperty("os.arch");
		clientInfo.osVersion			= ""+systemProperties.getProperty("os.version");
		clientInfo.osUserAccountName	= ""+systemProperties.getProperty("user.name");
		clientInfo.osHomeDir			= ""+systemProperties.getProperty("user.home");
		clientInfo.workingDir			= ""+systemProperties.getProperty("user.dir");


		clientInfo.displayWidth = Display.getDesktopDisplayMode().getWidth();
		clientInfo.displayHeight = Display.getDesktopDisplayMode().getHeight();
		clientInfo.displayBPP = Display.getDesktopDisplayMode().getBitsPerPixel();
		clientInfo.displayFreq = Display.getDesktopDisplayMode().getFrequency();



		clientInfo.shaderCompiled = LWJGLUtils.useShader;
		clientInfo.canUseFBO = LWJGLUtils.useFBO;
		clientInfo.usingVSync = LWJGLUtils.vsync;


		clientInfo.displayAdapter = Display.getAdapter();
		clientInfo.displayDriver = Display.getVersion();
		clientInfo.lwjglVersion = Sys.getVersion();
		clientInfo.lwjglIs64Bit = Sys.is64Bit();
		clientInfo.lwjglPlatformName = LWJGLUtil.getPlatformName();

		clientInfo.numCPUs = StatsUtils.rt.availableProcessors();
		clientInfo.totalMemory = StatsUtils.totalMemory/1024/1024;
		clientInfo.maxMemory = StatsUtils.maxMemory/1024/1024;

		clientInfo.numControllersFound = LWJGLUtils.numControllers;
		clientInfo.controllersNames = LWJGLUtils.controllerNames;



		clientInfo.timeZoneGMTOffset = timeZoneGMTOffset;

		clientInfo.glVendor = glGetString(GL_VENDOR);
		clientInfo.glVersion = glGetString(GL_VERSION);
		clientInfo.glRenderer = glGetString(GL_RENDERER);
		clientInfo.shaderVersion = glGetString(GL_SHADING_LANGUAGE_VERSION);
		clientInfo.glExtensions = glGetString(GL_EXTENSIONS);


		clientInfo.log();
		log.info("CurrentDisplayWidth:"+Display.getWidth());
		log.info("CurrentDisplayHeight:"+Display.getHeight());


	}






	//=========================================================================================================================
	public float timeZoneGMTOffset = 0.0f;
	public float DSTOffset = 0.0f;
	public void initTime()
	{//=========================================================================================================================

		log.info("Init Time...");

		//put timezone in connection info
		Calendar calendar = Calendar.getInstance();

		Date calendarTime = calendar.getTime();
		log.info("Local Adjusted Time: " + calendarTime);
		String gmtOffset = new SimpleDateFormat("Z").format(calendarTime);
		log.info("Local TimeZone GMT offset: "+gmtOffset);



		//get the time zone
		TimeZone timezone = calendar.getTimeZone();


		//get timezone raw millisecond offset
		int offset = timezone.getRawOffset();
		int offsetHrs = offset / 1000 / 60 / 60;
		int offsetMins = offset / 1000 / 60 % 60;
		//log.debug("TimeZone Offset Hours: " + offsetHrs);
		//log.debug("TimeZone Offset Mins: " + offsetMins);

		timeZoneGMTOffset = offsetHrs+offsetMins/100.0f;
		//log.debug("TimeZone Offset Float: " + offsetFloat);

		//add daylight savings offset
		int dstOffset = 0;
		if (timezone.inDaylightTime(new Date()))
		{
			dstOffset = timezone.getDSTSavings();
		}
		int dstOffsetHrs = dstOffset / 1000 / 60 / 60;
		int dstOffsetMins = dstOffset / 1000 / 60 % 60;
		//log.debug("DST Offset Hours: " + dstOffsetHrs);
		//log.debug("DST Offset Mins: " + dstOffsetMins);
		DSTOffset = dstOffsetHrs+dstOffsetMins/100.0f;

		//combined offset timezone + dst
		int combinedOffset = offset + dstOffset;
		int combinedOffsetHrs = combinedOffset / 1000 / 60 / 60;
		int combinedOffsetMins = combinedOffset / 1000 / 60 % 60;
		//log.debug("Total Offset Hours: " + combinedOffsetHrs);
		//log.debug("Total Offset Mins: " + combinedOffsetMins);


		//make an adjusted calendar to getTime from
		Calendar adjustedCalendar = Calendar.getInstance();
		adjustedCalendar.add(Calendar.HOUR_OF_DAY, (-combinedOffsetHrs));
		adjustedCalendar.add(Calendar.MINUTE, (-combinedOffsetMins));
		//log.debug("GMT Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(adjustedCalendar.getTime()) + " + " + combinedOffsetHrs + ":" + combinedOffsetMins);





		new Thread
		(
			new Runnable()
			{
				public void run()
				{

					try{Thread.currentThread().setName("ClientMain_initTime");}catch(SecurityException e){e.printStackTrace();}


					String NTPhost = "time.nist.gov";//this goes through lots of servers in round-robin, so keep trying.

					NTPUDPClient ntpUDPClient = new NTPUDPClient();
					ntpUDPClient.setDefaultTimeout(5000);

					int timeTryCount=0;
					boolean haveTime = false;
					while(haveTime==false&&timeTryCount<5)
					{


						if(timeTryCount==0)NTPhost = "time.nist.gov";
						if(timeTryCount==1)NTPhost = "nist1-sj.ustiming.org";
						if(timeTryCount==2)NTPhost = "nisttime.carsoncity.k12.mi.us";
						if(timeTryCount==3)NTPhost = "wwv.nist.gov";
						if(timeTryCount==4)NTPhost = "nist1.symmetricom.com";


						timeTryCount++;

						if(ntpUDPClient.isOpen()==true)ntpUDPClient.close();

						try
						{
							ntpUDPClient.open();
						}
						catch(SocketException e){log.debug("Could not open NTP UDP Client: "+e.getMessage());continue;}

						InetAddress hostAddr = null;
						try
						{
							hostAddr = InetAddress.getByName(NTPhost);
						}
						catch(UnknownHostException e){log.debug("Could not resolve NTP host: "+NTPhost+" | "+e.getMessage());continue;}

						//log.debug("> " + hostAddr.getHostName() + "/" + hostAddr.getHostAddress());

						TimeInfo timeInfo = null;

						try
						{
							timeInfo = ntpUDPClient.getTime(hostAddr);
						}catch(IOException e){log.debug("Could not get time from NTP host: "+hostAddr.getHostAddress()+" | "+e.getMessage());continue;}


						NtpV3Packet message = timeInfo.getMessage();


						// Transmit time is time reply sent by server (t3)
						TimeStamp xmitNtpTime = message.getTransmitTimeStamp();
						//log.debug(" Transmit Timestamp:\t" + xmitNtpTime + "  "+ xmitNtpTime.toDateString());


						Date serverDate = xmitNtpTime.getDate();
						log.info("Server Time (Adjusted by local TimeZone + DST): " + serverDate);

						// init game clock


						Calendar serverCalendar = Calendar.getInstance();
						serverCalendar.setTime(serverDate);


						int hour = serverCalendar.get(Calendar.HOUR_OF_DAY);
						int minute = serverCalendar.get(Calendar.MINUTE);
						int second = serverCalendar.get(Calendar.SECOND);
						int day = serverCalendar.get(Calendar.DAY_OF_WEEK)-1;

						clientGameEngine.clock.setTime(day, hour, minute, second);



						ntpUDPClient.close();
						haveTime=true;

					}

					if(haveTime==false)
					{
						log.error("Could not get time from NTP server!");

						//TODO: just set to local clock time.
					}

				}
			}

		).start();

	}






	//=========================================================================================================================
	public void doLegalScreen()
	{//=========================================================================================================================

		if(new File(Cache.cacheDir+"session").exists()==false)
		{

			//if(BobNet.debugMode==false)
			{

				log.info("Legal Screen...");

				LegalScreen legalScreen = new LegalScreen();
				GUI legalScreenGUI = new GUI(legalScreen, LWJGLUtils.TWLrenderer);
				legalScreenGUI.applyTheme(LWJGLUtils.TWLthemeManager);

				while(legalScreen.clickedOK_S()==false)
				{

					glClear(GL_COLOR_BUFFER_BIT);


					legalScreen.update();
					legalScreenGUI.update();

					if(
							(Display.isCloseRequested() || ( BobNet.debugMode==true && Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) ))
							||legalScreen.clickedCancel_S()==true
					)
					{
						legalScreen.destroy();
						LWJGLUtils.TWLthemeManager.destroy();
						Display.destroy();
						AL.destroy();
						ClientMain.exit();
					}

					Display.sync(60);
					Display.update();
					doResizeCheck();

				}

				legalScreen.destroy();
				glClear(GL_COLOR_BUFFER_BIT);

				log.info("Accepted Legal Screen.");

			}

		}

	}




	//=========================================================================================================================
	public void showControlsImage()
	{//=========================================================================================================================

		if(new File(Cache.cacheDir+"session").exists()==false)
		{

			//if(BobNet.debugMode==false)
			{
				KeyboardScreen keyboardScreen = new KeyboardScreen();
				GUI keyboardScreenGUI = new GUI(keyboardScreen, LWJGLUtils.TWLrenderer);
				keyboardScreenGUI.applyTheme(LWJGLUtils.TWLthemeManager);

				keyboardScreen.okButton.setVisible(true);
				keyboardScreen.setActivated(true);

				while(keyboardScreen.clickedOK_S()==false)
				{
					glClear(GL_COLOR_BUFFER_BIT);


					keyboardScreen.update();
					keyboardScreenGUI.update();


					Display.sync(60);
					Display.update();
				}
				keyboardScreen.destroy();
				glClear(GL_COLOR_BUFFER_BIT);
			}

		}

	}





	//=========================================================================================================================
	public void makeGhostThread()
	{//=========================================================================================================================

		//ghost thread to prevent stuttering
		//this is due to windows aero, for some reason creating a ghost thread prevents it for some fucking reason
		new Thread
		(
			new Runnable()
			{
				public void run()
				{
					try{Thread.currentThread().setName("ClientMain_ghostThreadToPreventAeroStutter");}catch(SecurityException e){e.printStackTrace();}

					while(exit==false)
					{
						try
						{
							Thread.sleep(16);//this only seems to work at 16

							//Thread.yield(); //high cpu usage
							//if(Display.isActive()==false)Display.processMessages();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		).start();

	}


	//=========================================================================================================================
	public void initDebugLogger()
	{//=========================================================================================================================



		Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		LoggerContext loggerContext = rootLogger.getLoggerContext();
		// we are not interested in auto-configuration
		loggerContext.reset();



		PatternLayoutEncoder consoleEncoder = new PatternLayoutEncoder();
		consoleEncoder.setContext(loggerContext);
		consoleEncoder.setPattern("%-50(%highlight(%-5level| %msg   )) \\(%F:%L\\) %boldMagenta(%c{2}.%M\\(\\)) %boldGreen([%thread]) \n");
		consoleEncoder.start();

		ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<ILoggingEvent>();
		consoleAppender.setWithJansi(true);
		consoleAppender.setContext(loggerContext);
		consoleAppender.setEncoder(consoleEncoder);
		consoleAppender.start();

		rootLogger.addAppender(consoleAppender);




		HTMLLayout htmlLayout = new HTMLLayout();
		htmlLayout.setPattern("%date{yyyy-MM-dd HH:mm:ss}%relative%thread%F%L%c{2}%M%level%msg");
		htmlLayout.setContext(loggerContext);
		htmlLayout.start();

		LayoutWrappingEncoder<ILoggingEvent> layoutEncoder = new LayoutWrappingEncoder<ILoggingEvent>();
		layoutEncoder.setLayout(htmlLayout);
		layoutEncoder.setContext(loggerContext);
		layoutEncoder.setImmediateFlush(false);
		layoutEncoder.start();

		FileAppender<ILoggingEvent> htmlFileAppender = new FileAppender<ILoggingEvent>();
		htmlFileAppender.setContext(loggerContext);
		htmlFileAppender.setEncoder(layoutEncoder);
		htmlFileAppender.setAppend(true);
		htmlFileAppender.setFile(Cache.cacheDir+"log.html");
		htmlFileAppender.start();

		rootLogger.addAppender(htmlFileAppender);






		PatternLayoutEncoder textEncoder = new PatternLayoutEncoder();
		textEncoder.setContext(loggerContext);
		textEncoder.setPattern("%date{yyyy-MM-dd HH:mm:ss} %-50(%-5level| %msg   ) [%thread] \n");
		textEncoder.setImmediateFlush(true);
		textEncoder.start();


		FileAppender<ILoggingEvent> textFileAppender = new FileAppender<ILoggingEvent>();
		textFileAppender.setContext(loggerContext);
		textFileAppender.setEncoder(textEncoder);
		textFileAppender.setAppend(true);
		textFileAppender.setFile(Cache.cacheDir+"log.txt");
		textFileAppender.start();

		rootLogger.addAppender(textFileAppender);



		rootLogger.setLevel(Level.ALL);



	}

	//=========================================================================================================================
	public void initReleaseLogger()
	{//=========================================================================================================================


		Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		LoggerContext loggerContext = rootLogger.getLoggerContext();
		// we are not interested in auto-configuration
		loggerContext.reset();


		PatternLayoutEncoder consoleEncoder = new PatternLayoutEncoder();
		consoleEncoder.setContext(loggerContext);
		consoleEncoder.setPattern("%date{yyyy-MM-dd HH:mm:ss} %-50(%-5level| %msg   ) [%thread] \n");
		consoleEncoder.start();

		ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<ILoggingEvent>();
		consoleAppender.setWithJansi(false);
		consoleAppender.setContext(loggerContext);
		consoleAppender.setEncoder(consoleEncoder);
		consoleAppender.start();

		rootLogger.addAppender(consoleAppender);




		PatternLayoutEncoder textEncoder = new PatternLayoutEncoder();
		textEncoder.setContext(loggerContext);
		textEncoder.setPattern("%date{yyyy-MM-dd HH:mm:ss} %-50(%-5level| %msg   ) [%thread] \n");
		textEncoder.setImmediateFlush(true);
		textEncoder.start();


		FileAppender<ILoggingEvent> textFileAppender = new FileAppender<ILoggingEvent>();
		textFileAppender.setContext(loggerContext);
		textFileAppender.setEncoder(textEncoder);
		textFileAppender.setAppend(true);
		textFileAppender.setFile(Cache.cacheDir+"log.txt");
		textFileAppender.start();

		rootLogger.addAppender(textFileAppender);

		//rootLogger.setLevel(Level.WARN);





	}






	//=========================================================================================================================




	public static Logger log = (Logger) LoggerFactory.getLogger(ClientMain.class);


	public volatile boolean exit = false;


	public static Cache cacheManager = new Cache();
	public StateManager stateManager;

	public ControlsManager controlsManager;

	public ClientGameEngine clientGameEngine;
	public ArrayDeque<ClientGameEngine> gameStack = new ArrayDeque<ClientGameEngine>();

	public Console console;

	public LoginState loginState;
	public LoggedOutState loggedOutState;
	public ServersHaveShutDownState serversHaveShutDownState;
	public CreateNewAccountState createNewAccountState;
	public TitleScreenState titleScreenState;
	public YouWillBeNotifiedState youWillBeNotifiedState;

	public static GlowTileBackground glowTileBackground;


	public boolean serversAreShuttingDown = false;

	//public SpriteAssetIndex spriteAssetManager;
	//public MapAssetIndex mapAssetManager;




	public String slash = System.getProperties().getProperty("file.separator");

	public static boolean isApplet = false;


	public Utils utils;
	public LWJGLUtils lwjglUtils;
	public StatsUtils statsUtils;
	public GLUtils glUtils;
	public static AudioUtils audioUtils;

	public GameClientTCP gameClientTCP;
	//public ClientUDP clientUDP;





	public static String serverAddress = BobNet.releaseServerAddress;
	public static String STUNServerAddress = BobNet.releaseSTUNServerAddress;

	public void mainInit() {
		boolean debugOnLiveServer = false;

		if (BobNet.debugMode == true || debugOnLiveServer) {
			System.setProperty("org.lwjgl.util.Debug", "true");
			System.setProperty("org.lwjgl.util.NoChecks", "false");

			serverAddress = BobNet.debugServerAddress;
			STUNServerAddress = BobNet.debugSTUNServerAddress;

			initDebugLogger();
		} else {
			System.setProperty("org.lwjgl.util.Debug","false");
			System.setProperty("org.lwjgl.util.NoChecks","true");

			initReleaseLogger();
		}

		log.info("Main Init...");

		lwjglUtils = new LWJGLUtils();

		if (isApplet == false) {
			LWJGLUtils.setDisplayMode();
		} else if (isApplet == true) {
			try {
				Display.setParent(appletCanvas);
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}

		// this is done before init game so we can put debug stuff
		console = new Console();
		utils = new Utils();

		LWJGLUtils.initGL("Project 2");
		LWJGLUtils.initTWL();
		LWJGLUtils.initControllers();

		audioUtils = new AudioUtils();

		glUtils = new GLUtils();
		statsUtils = new StatsUtils();

		StatsUtils.initDebugInfo();

		if (previewClientInEditor == false && BobNet.debugMode == false) {
			doLegalScreen();
		}

		cacheManager.initCache();

		stateManager = new StateManager();

		//-------------------
		//init game
		//-------------------
		log.info("Init Client...");
		makeNewClientEngine();

		//-------------------
		//init login GUI
		//-------------------
		log.info("Init GUIs...");
		if (glowTileBackground == null) {
			glowTileBackground = new GlowTileBackground();
		}

		loginState = new LoginState();
		loggedOutState = new LoggedOutState();
		serversHaveShutDownState = new ServersHaveShutDownState();
		createNewAccountState = new CreateNewAccountState();
		titleScreenState = new TitleScreenState();
		youWillBeNotifiedState = new YouWillBeNotifiedState();

		// TODO: check cookie exists
		// TODO: check cache/intro
		// TODO: check registry property

		if (previewClientInEditor == false) {
			boolean didIntro = false; //Cache.doesDidIntroFileExist();

			if (didIntro == false) {
				introMode = true;

				log.info("Setup Intro...");

				clientGameEngine.statusBar.gameStoreButton.setEnabled(false);
				clientGameEngine.statusBar.ndButton.setEnabled(false);
				clientGameEngine.statusBar.stuffButton.setEnabled(false);
				clientGameEngine.statusBar.moneyCaption.setEnabled(false);
				clientGameEngine.statusBar.dayCaption.setEnabled(false);

				stateManager.setState(clientGameEngine);
				clientGameEngine.cinematicsManager.fadeFromBlack(10000);

				clientGameEngine.mapManager.changeMap("ALPHABobsApartment","atDesk");
				//clientGameEngine.mapManager.changeMap("GENERIC1UpstairsBedroom1",12*8*2,17*8*2);
				//clientGameEngine.textManager.text("Yep \"Yuu\" yay. <.><1>bob! yay, \"bob\" yay! <.><0>\"Yuu\" yay, nD. yay yay \"bob's game\" yay- bob's? yay \"bob's\" yay bob's game<1>yep");
			} else {
				if (BobNet.debugMode == false) {
					showControlsImage();
				}
				stateManager.setState(loginState);
			}
		}

		initTime();

		//-------------------
		//fill in the client session info to send to the server for debug/stats
		//this must be done after everything is initialized.
		//-------------------
		initClientInfo();
	}



	//=========================================================================================================================
	public void makeNewClientEngine()
	{//=========================================================================================================================


		if(clientGameEngine!=null)clientGameEngine.cleanup();
		if(gameClientTCP!=null)gameClientTCP.cleanup();


		controlsManager = new ControlsManager();
		clientGameEngine = new ClientGameEngine();

		Engine.setClientGameEngine(clientGameEngine);
		Engine.setControlsManager(controlsManager);


		//stateManager.setState(game);

		clientGameEngine.init();

		//-------------------
		//init network
		//-------------------
		gameClientTCP = new GameClientTCP(clientGameEngine);

	}


	public static boolean introMode = false;
	public static boolean previewClientInEditor = false;


	boolean debugKeyPressed = false;
	boolean screenShotKeyPressed = false;
	boolean resize = false;

	//=========================================================================================================================
	//this is called from the browser javascript on window.focus
	public void focus()
	{//=========================================================================================================================

		resize=true;


		this.requestFocus();

		if(isApplet==true)
		{
			appletCanvas.requestFocus();
			appletCanvas.requestFocusInWindow();
		}

	}

	//=========================================================================================================================
	//this is called from the browser javascript on window.blur
	public void blur()
	{//=========================================================================================================================


	}



	public static String facebookID = "";
	public static String facebookAccessToken = "";
	public static boolean _gotFacebookResponse = false;


	synchronized static public void setGotFacebookResponse_S(boolean b){_gotFacebookResponse = b;}
	synchronized static public boolean getGotFacebookResponse_S(){return _gotFacebookResponse;}

	//=========================================================================================================================
	//this is called from the browser javascript after we call the facebook JS SDK
	public void setFacebookCredentials(String facebookID, String accessToken)
	{//=========================================================================================================================
		ClientMain.facebookID = facebookID;
		ClientMain.facebookAccessToken = accessToken;

		setGotFacebookResponse_S(true);
	}








	//=========================================================================================================================
	public void mainLoop() throws Exception
	{//=========================================================================================================================

		//------------------------------
		// main loop
		//------------------------------

		makeGhostThread();

		focus();


		StatsUtils.initTimers();

		log.info("Begin Main Loop...");

		while(exit==false)
		{

			if(Display.isCloseRequested() || (BobNet.debugMode==true&&Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)))exit=true;


			StatsUtils.updateTimers();
			StatsUtils.updateDebugInfo();


			//if(ticksPassed>0)
			{
				//clientTCP.update();
				controlsManager.update();

				stateManager.update();
				console.update();

				glClear(GL_COLOR_BUFFER_BIT);
				stateManager.render();

				LWJGLUtils.setBlendMode(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				console.render();
				//LWJGLUtils.setBlendMode(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
			}


			if(serversAreShuttingDown)
			{
				GLUtils.drawFilledRect(0, 0, 0, 0, Display.getWidth(), 0, Display.getHeight(), 0.2f);
				GLUtils.drawOutlinedString("The servers are shutting down soon for updating.", Display.getWidth()/2-60, Display.getHeight()/2-12, BobColor.white);
			}


			if(Display.isActive()==false && BobNet.debugMode==false)
			{


				GLUtils.drawFilledRect(0, 0, 0, 0, Display.getWidth(), 0, Display.getHeight(), 0.5f);
				GLUtils.drawOutlinedString("Low power mode. Click to resume.", Display.getWidth()/2-70, Display.getHeight()/2-12, BobColor.white);



				Display.sync(10);

				if(Display.isVisible())Display.update();
				else
				Display.processMessages();
				//Display.update();




				/*try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}*/

				//Thread.yield();

				//continue;

			}
			else
			{


				if(LWJGLUtils.vsync)
				{
					try
					{
						//this just lowers cpu usage
						Thread.sleep(2);//TODO: vary this based on system speed
						//System.gc();

						Thread.yield();

					}catch(Exception e){e.printStackTrace();}

				}
				else
				if(LWJGLUtils.vsync==false)
				{

					//Display.sync(240);
					//Display.sync(90);
					Display.sync(60);


					//sync() only seems to work properly with ghost thread

					//with ghost thread
					//60 = stutter
					//90 = no stutter, a bit flickery
					//120 = quick, slight stutter, probably due to interpolation
					//150 = quick, 15% cpu, seems a bit flickery, probably due to interpolation
					//240 == 250 fps for some reason, really smooth, 20% gpu, almost no cpu.


					//without ghost thread
						//sync(240) caps at 120 and stutters. fucking weird!
						//90 = 60
						//60 = 60
						//30 = 30, jitter but no stutter
						//45 = jitter and constant fast stutter, maybe from interpolation.


					/*try
					{
						//this actually regulates framerate
						Thread.sleep(6);//16);//TODO: vary this based on system speed

						//no ghost thread
						//30 = 30 fps solid, jitter but no stutter, very smooth though **(same for with ghost thread)


						//ghost thread
						//8 = ~ 120-125 fps, starting to get a little bit of stutter
						//7 = ~ 130-140 fps, no stutter, tiny bit choppy for some reason
						//6 = ~ 150 fps, no stutter
						//5 = ~ 200 fps, no stutter
						//4 = ~ 250 fps, no stutter

						//1 = 950 fps, very smooth


						Thread.yield();

					}catch(Exception e){e.printStackTrace();}*/

				}


				Display.update();



			}



			doScreenShotCheck();

			doResizeCheck();

			LWJGLUtils.e();
		}


	}





	//=========================================================================================================================
	public void doResizeCheck()
	{//=========================================================================================================================



//			log.info("Display.getWidth() x Display.getHeight():"+Display.getWidth()+" x "+Display.getHeight());
//			log.info("getWidth() x getHeight():"+getWidth()+" x "+getHeight());
//			if(isApplet)
//			{
//				log.info("appletCanvas.getWidth() x appletCanvas.getHeight():"+appletCanvas.getWidth()+" x "+appletCanvas.getHeight());
//				log.info("this.getContentPane().getWidth() x this.getContentPane().getHeight():"+this.getContentPane().getWidth()+" x "+this.getContentPane().getHeight());
//				log.info("this.getGlassPane().getWidth() x this.getGlassPane().getHeight():"+this.getGlassPane().getWidth()+" x "+this.getGlassPane().getHeight());
//				log.info("this.getRootPane().getWidth() x this.getRootPane().getHeight():"+this.getRootPane().getWidth()+" x "+this.getRootPane().getHeight());
//				log.info("this.getParent().getWidth() x this.getParent().getHeight():"+this.getParent().getWidth()+" x "+this.getParent().getHeight());
//
//			}




		if(isApplet==true)
		{
			//appletCanvas.notify();
			//appletCanvas.setSize(500,500);
			//invalidate();
			//appletCanvas.invalidate();
			//resize=true;

			//validate();

			if(
					//Display.getWidth()!=canvasWidth||Display.getHeight()!=canvasHeight
					Display.getWidth()!=lastDisplayWidth
					||Display.getHeight()!=lastDisplayHeight
					||getWidth()!=lastWidth
					||getHeight()!=lastHeight
					||appletCanvas.getWidth()!=lastAppletCanvasWidth
					||appletCanvas.getHeight()!=lastAppletCanvasHeight

			)
			{

				log.info("Resized applet.");




				resize = true;

				canvasWidth = Display.getWidth();
				canvasHeight = Display.getHeight();

				//TODO: resize to browser window
				//appletCanvas.setSize(canvasWidth,canvasHeight);
				//appletCanvas.validate();
				//remove(appletCanvas);
				//add(appletCanvas);
				//validate();

				try
				{

					//Display.setParent(null);

					//remove(appletCanvas);
					//add(appletCanvas);
					//validate();

					//Display.update();
					//Display.setParent(appletCanvas);
					//Display.update();


					//Display.setDisplayMode(new DisplayMode(canvasWidth,canvasHeight));
					//Display.update();

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}


		}



		if(Display.wasResized()==true || resize==true)
		{
			resize=false;


			//reset GL model matrix, etc.

			log.info("Resized window.");



//				log.info("Display.getWidth() x Display.getHeight():"+Display.getWidth()+" x "+Display.getHeight());
//				log.info("getWidth() x getHeight():"+getWidth()+" x "+getHeight());
//				if(isApplet)log.info("appletCanvas.getWidth() x appletCanvas.getHeight():"+appletCanvas.getWidth()+" x "+appletCanvas.getHeight());
	//
	//
			lastDisplayWidth = Display.getWidth();
			lastDisplayHeight = Display.getHeight();
			lastWidth = getWidth();
			lastHeight = getHeight();

			if(isApplet)
			{
				lastAppletCanvasWidth = appletCanvas.getWidth();
				lastAppletCanvasHeight = appletCanvas.getHeight();
			}


			LWJGLUtils.doResize();


		}
	}




	//=========================================================================================================================
	public void doScreenShotCheck()
	{//=========================================================================================================================


		boolean takeScreenShot = false;

		if(Keyboard.isKeyDown(Keyboard.KEY_F12))
		{
			if(screenShotKeyPressed==false)
			{
				screenShotKeyPressed=true;
				takeScreenShot=true;
			}
		}
		else
		{
			screenShotKeyPressed=false;
		}


		if(takeScreenShot)
		{

			clientGameEngine.audioManager.playSound("screenShot",1.0f,1.0f,1);

			String imageName = "bobsgame-"+new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(Calendar.getInstance().getTime())+".png";
			String fileName = "";

			if(System.getProperty("os.name").contains("Win"))
			{
				Console.add("Saved screenshot on Desktop.",BobColor.green,3000);
				fileName = System.getProperty("user.home")+Cache.slash+"Desktop"+Cache.slash+imageName;
			}
			else
			{
				Console.add("Saved screenshot in home folder.",BobColor.green,3000);
				fileName = System.getProperty("user.home")+Cache.slash+imageName;
			}

			GL11.glReadBuffer(GL11.GL_FRONT);
			int width = Display.getWidth();
			int height= Display.getHeight();
			int bytesPerPixel = Display.getDisplayMode().getBitsPerPixel()/8; // Assuming a 32-bit display with a byte each for red, green, blue, and alpha.
			ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bytesPerPixel);
			GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer );

			File file = new File(fileName);
			String format = "PNG";
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			for(int x = 0; x < width; x++)
			{
				for(int y = 0; y < height; y++)
				{
					int i = (x + (width * y)) * bytesPerPixel;
					int r = buffer.get(i) & 0xFF;
					int g = buffer.get(i + 1) & 0xFF;
					int b = buffer.get(i + 2) & 0xFF;
					image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
				}
			}


			try
			{
				ImageIO.write(image, format, file);
			} catch (IOException e) { e.printStackTrace(); }


		}


		boolean printLog = false;

		if(Keyboard.isKeyDown(Keyboard.KEY_F11))
		{
			if(debugKeyPressed==false)
			{
				debugKeyPressed=true;
				printLog=true;
			}
		}
		else
		{
			debugKeyPressed=false;
		}

		if(printLog)
		{

			String imageName = "bobsgame-"+new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(Calendar.getInstance().getTime())+".txt";
			String fileName = "";

			if(System.getProperty("os.name").contains("Win"))
			{
				Console.add("Saved debug log on Desktop.",BobColor.green,3000);
				fileName = System.getProperty("user.home")+Cache.slash+"Desktop"+Cache.slash+imageName;
			}
			else
			{
				Console.add("Saved debug log in home folder.",BobColor.green,3000);
				fileName = System.getProperty("user.home")+Cache.slash+imageName;
			}

			Writer output = null;
			try
			{
				output = new BufferedWriter(new FileWriter(new File(fileName)));

				String s = FileUtils.readFileToString(new File(Cache.cacheDir+"log.txt"));
				s = s + "\n";
				//s = s + clientInfo.printString();

				output.write(s);
				output.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}

	}


	//=========================================================================================================================
	public void cleanup() throws Exception
	{//=========================================================================================================================
		//------------------------------
		// end main loop, cleanup
		//------------------------------


		log.info("Cleaning up...");

		try
		{
			Display.setParent(null);
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
		}

		try{

			AL.destroy();
		}catch(Exception e){e.printStackTrace();}

		try{
			gameClientTCP.cleanup();
			clientGameEngine.cleanup();
		}catch(Exception e){e.printStackTrace();}


		try{
			loginState.cleanup();
			createNewAccountState.cleanup();
			LWJGLUtils.TWLthemeManager.destroy();
		}catch(Exception e){e.printStackTrace();}


		//loggedOutState.cleanup();
		//serversHaveShutDownState.cleanup();
		//titleScreenState.cleanup();
		//youWillBeNotifiedState.cleanup();
		//glowTileBackground.cleanup();


		try{
			Display.destroy();
		}catch(Exception e){e.printStackTrace();}

		log.info("Exiting...");



	}




}

