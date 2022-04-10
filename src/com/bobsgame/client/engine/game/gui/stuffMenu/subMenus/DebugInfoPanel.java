package com.bobsgame.client.engine.game.gui.stuffMenu.subMenus;
import org.lwjgl.opengl.Display;

import com.bobsgame.ClientMain;
import com.bobsgame.client.console.Console;
import com.bobsgame.client.engine.Engine;
import com.bobsgame.client.engine.game.ClientGameEngine;
import com.bobsgame.client.engine.game.gui.GUIManager;
import com.bobsgame.client.engine.game.gui.gameStore.GameStore;
import com.bobsgame.client.engine.game.gui.statusbar.StatusBar;
import com.bobsgame.client.engine.game.gui.stuffMenu.StuffMenu;
import com.bobsgame.client.engine.game.gui.stuffMenu.SubPanel;

import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.ScrollPane;
import de.matthiasmann.twl.DialogLayout.Group;



//=========================================================================================================================
public class DebugInfoPanel extends SubPanel
{//=========================================================================================================================


	static Label[] label = null;

	static Group horizontalGroup = null;
	static Group verticalGroup = null;

	//=========================================================================================================================
	public DebugInfoPanel()
	{//=========================================================================================================================


		super();

		horizontalGroup = insideLayout.createParallelGroup();
		verticalGroup = insideLayout.createSequentialGroup();

		//updateStatusPanel();

		insideLayout.setHorizontalGroup
		(
			horizontalGroup
		);

		insideLayout.setVerticalGroup
		(
			verticalGroup
		);


	}

	public long updateTicks = 0;

	//=========================================================================================================================
	public void update()
	{//=========================================================================================================================

		updateTicks+=Engine.mainTicksPassed;
		if(updateTicks>200)//every 200 ticks
		{
			updateTicks=0;


			int size = Console.consoleTextList.size();

			if(label==null||size!=label.length)
			{
				label = new Label[size];

				for(int i=0; i<size; i++)
				{
					label[i] = new Label(Console.consoleTextList.get(i).text);
				}

				insideLayout.removeAllChildren();

				horizontalGroup = insideLayout.createParallelGroup(label);
				verticalGroup = insideLayout.createSequentialGroup(label);

				insideLayout.setHorizontalGroup
				(
					horizontalGroup
				);

				insideLayout.setVerticalGroup
				(
					verticalGroup
				);
			}
			else
			{
				for(int i=0; i<size; i++)
				{
					label[i].setText(Console.consoleTextList.get(i).text);
				}
			}

		}
	}




}
