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
public class YouWillBeNotifiedState extends State
{//=========================================================================================================================





	//=========================================================================================================================
	public YouWillBeNotifiedState()
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

		String text = "To be continued...";

		GLUtils.drawFilledRect(0, 0, 0, 0, Display.getWidth(), 0, Display.getHeight(), 0.5f);
		GLUtils.drawOutlinedString(text, Display.getWidth()/2 - GLUtils.font.getWidth(text)/2, Display.getHeight()/2, BobColor.white);


	}


	//=========================================================================================================================
	public void cleanup()
	{//=========================================================================================================================


	}




}
