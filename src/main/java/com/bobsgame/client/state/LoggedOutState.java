package com.bobsgame.client.state;


import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.bobsgame.ClientMain;
import com.bobsgame.client.GLUtils;
import com.bobsgame.shared.BobColor;

//=========================================================================================================================
public class LoggedOutState extends State
{//=========================================================================================================================





	//=========================================================================================================================
	public LoggedOutState()
	{//=========================================================================================================================



	}


	//=========================================================================================================================
	public void update()
	{//=========================================================================================================================

		if(Keyboard.isKeyDown(Keyboard.KEY_L))
		{

			ClientMain.clientMain.stateManager.setState(ClientMain.clientMain.clientGameEngine);

		}

	}


	//=========================================================================================================================
	public void render()
	{//=========================================================================================================================


		ClientMain.glowTileBackground.render();


		GLUtils.drawFilledRect(0, 0, 0, 0, Display.getWidth(), 0, Display.getHeight(), 0.5f);
		GLUtils.drawOutlinedString("You have been logged out by a different session.", Display.getWidth()/2-70, Display.getHeight()/2-12, BobColor.white);
		GLUtils.drawOutlinedString("Press L to take this session back and log out that one.", Display.getWidth()/2-90, Display.getHeight()/2+12, BobColor.gray);

	}


	//=========================================================================================================================
	public void cleanup()
	{//=========================================================================================================================


	}




}
