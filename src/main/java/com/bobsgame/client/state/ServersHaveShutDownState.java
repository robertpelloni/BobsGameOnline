package com.bobsgame.client.state;


import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.bobsgame.ClientMain;
import com.bobsgame.GLUtils;
import com.bobsgame.client.console.Console;
import com.bobsgame.client.console.ConsoleText;
import com.bobsgame.client.engine.Engine;
import com.bobsgame.client.engine.entity.Entity;
import com.bobsgame.shared.BobColor;
import com.bobsgame.shared.Utils;

import de.matthiasmann.twl.GUI;

//=========================================================================================================================
public class ServersHaveShutDownState extends State
{//=========================================================================================================================





	//=========================================================================================================================
	public ServersHaveShutDownState()
	{//=========================================================================================================================



	}


	//=========================================================================================================================
	public void update()
	{//=========================================================================================================================






	}


	//=========================================================================================================================
	public void render()
	{//=========================================================================================================================


		ClientMain.glowTileBackground.render();


		GLUtils.drawFilledRect(0, 0, 0, 0, Display.getWidth(), 0, Display.getHeight(), 0.5f);
		GLUtils.drawOutlinedString("The servers have shut down for updating.", Display.getWidth()/2-60, Display.getHeight()/2-12, BobColor.white);
		GLUtils.drawOutlinedString("Your progress was saved. Please reload the client.", Display.getWidth()/2-70, Display.getHeight()/2+12, BobColor.gray);

	}


	//=========================================================================================================================
	public void cleanup()
	{//=========================================================================================================================


	}




}
