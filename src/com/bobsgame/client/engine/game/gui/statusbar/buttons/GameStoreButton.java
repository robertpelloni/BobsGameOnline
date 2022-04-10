package com.bobsgame.client.engine.game.gui.statusbar.buttons;



import java.io.File;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.bobsgame.ClientMain;
import com.bobsgame.GLUtils;
import com.bobsgame.client.ControlsManager;
import com.bobsgame.client.engine.Engine;
import com.bobsgame.client.engine.game.ClientGameEngine;
import com.bobsgame.client.engine.game.gui.GUIManager;
import com.bobsgame.client.engine.game.gui.gameStore.GameStore;
import com.bobsgame.client.engine.game.gui.statusbar.StatusBar;
import com.bobsgame.client.engine.game.gui.stuffMenu.StuffMenu;
import com.bobsgame.client.engine.game.nd.ND;
import com.bobsgame.shared.Utils;




//=========================================================================================================================
public class GameStoreButton extends StatusBarButton
{//=========================================================================================================================


	//=========================================================================================================================
	public GameStoreButton(ClientGameEngine g)
	{//=========================================================================================================================

		super(g);

		texture=GLUtils.loadTexture("res/statusbar/gameStore.png");


		offsetX0=0;// NDButton.dividerX+20;//120
		offsetX1=0;// offsetX0+124;//244

		pressedOffsetY=0;
		offsetY0=3;
		offsetY1=5;

		dividerX=0;// offsetX1+16;//260

		glowX0=0;// offsetX0-60;//60
		glowX1=0;// offsetX1+60;//184

		glowY0=-40;
		glowY1=60;

		clickX0 = 100;
		clickX1 = dividerX;

	}


	//=========================================================================================================================
	public void init()
	{//=========================================================================================================================

		setOffsets();

	}

	//=========================================================================================================================
	public void setOffsets()
	{//=========================================================================================================================

		offsetX0=StatusBar().ndButton.dividerX+20;// 120
		offsetX1=offsetX0+124;// 244
		dividerX=offsetX1+16;// 260
		glowX0=offsetX0-60;// 60
		glowX1=offsetX1+60;// 184

		clickX0 = 100;
		clickX1 = dividerX;

	}


	//=========================================================================================================================
	public void clicked()
	{//=========================================================================================================================

		if(PlayerEditMenu().isActivated()==true) return;


		if(StuffMenu().isActivated()&&StuffMenu().isScrollingDown()==false) StuffMenu().toggleActivated();

		GameStore().toggleActivated();
	}


	//=========================================================================================================================
	public boolean isAssociatedMenuActive()
	{//=========================================================================================================================

		return GameStore().isActivated();
	}


}
