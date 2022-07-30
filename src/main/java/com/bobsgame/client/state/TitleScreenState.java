package com.bobsgame.client.state;


import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import slick.Texture;

import com.bobsgame.ClientMain;
import com.bobsgame.GLUtils;
import com.bobsgame.client.console.Console;
import com.bobsgame.client.console.ConsoleText;
import com.bobsgame.client.engine.Engine;
import com.bobsgame.client.engine.entity.Entity;
import com.bobsgame.client.state.GlowTileBackground.GlowTile;
import com.bobsgame.shared.BobColor;
import com.bobsgame.shared.Utils;

import de.matthiasmann.twl.GUI;

//=========================================================================================================================
public class TitleScreenState extends State
{//=========================================================================================================================





	//=========================================================================================================================
	public TitleScreenState()
	{//=========================================================================================================================



	}


	int frame = 0;
	int ticks=0;

	int count = 0;

	//=========================================================================================================================
	public void update()
	{//=========================================================================================================================





		ticks+=engineTicksPassed();
		if(ticks>30)
		{
			ticks=0;

			frame++;
			if(frame>=ClientMain.glowTileBackground.glowTileFramesTexture.length){frame=0;count++;}

		}


		if(count>2)
		{
//			ClientMain.clientMain.createNewAccountState.cancelButton.setEnabled(false);
//			ClientMain.clientMain.createNewAccountState.cancelButton.setVisible(false);
//			ClientMain.clientMain.createNewAccountState.cancelButton.setActivated(true);
			ClientMain.clientMain.stateManager.setState(ClientMain.clientMain.createNewAccountState);
		}
	}


	//=========================================================================================================================
	public void render()
	{//=========================================================================================================================




			Texture t = ClientMain.glowTileBackground.bgScrollTexture;

			Texture over = ClientMain.glowTileBackground.glowTileFramesTexture[frame];

			float screenWidth = Display.getWidth();
			float screenHeight = Display.getHeight();


			float scale = 2.0f;

			float x0 = screenWidth/2 - (t.getImageWidth()*scale)/2;
			float x1 = x0 + (t.getImageWidth()*scale);

			float y0 = screenHeight/2 - (t.getImageHeight()*scale)/2;
			float y1 = y0 + (t.getImageHeight()*scale);


			GLUtils.drawTexture(t,x0,x1,y0,y1,1.0f,GLUtils.FILTER_NEAREST);
			GLUtils.drawTexture(over,x0,x1,y0,y1,1.0f,GLUtils.FILTER_NEAREST);


	}




}
