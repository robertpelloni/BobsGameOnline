package com.bobsgame.client.engine.game.gui.gameStore;

import org.lwjgl.opengl.Display;

import com.bobsgame.ClientMain;

import com.bobsgame.client.engine.Engine;
import com.bobsgame.client.engine.game.ClientGameEngine;
import com.bobsgame.client.engine.game.gui.GUIManager;
import com.bobsgame.client.engine.game.gui.MenuPanel;
import com.bobsgame.client.engine.game.gui.statusbar.StatusBar;
import com.bobsgame.client.engine.game.gui.stuffMenu.StuffMenu;
import com.bobsgame.client.engine.game.gui.stuffMenu.subMenus.ControlsPanel;
import com.bobsgame.client.engine.game.gui.stuffMenu.subMenus.DebugInfoPanel;
import com.bobsgame.client.engine.game.gui.stuffMenu.subMenus.ItemsPanel;
import com.bobsgame.client.engine.game.gui.stuffMenu.subMenus.LogsPanel;
import com.bobsgame.client.engine.game.gui.stuffMenu.subMenus.MessagesPanel;
import com.bobsgame.client.engine.game.gui.stuffMenu.subMenus.SettingsPanel;
import com.bobsgame.client.engine.game.gui.stuffMenu.subMenus.StatusPanel;

import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.ScrollPane;
import de.matthiasmann.twl.ToggleButton;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.model.OptionBooleanModel;
import de.matthiasmann.twl.model.SimpleIntegerModel;






//=========================================================================================================================
public class GameStore extends MenuPanel
{//=========================================================================================================================





	public GameItem[] games;
	//public GameListPanel gameStorePanel;



	//=========================================================================================================================
	public GameStore()
	{//=========================================================================================================================

		super();

		games = new GameItem[3];

		for(int i=0;i<3;i++)
		{
			games[i] = new GameItem();
		}

		games[0].gameTitleLabel.setText("Tetrid LIVE");
		games[1].gameTitleLabel.setText("PING World Tournament");
		games[2].gameTitleLabel.setText("RunMan I/O");

		games[0].setText("Tetrid LIVE is the newest, hottest party puzzle fun game in town. Endlessly sort blocks! Become entranced with compulsion! Do the job of a robot! Train your skills to become an efficient retail shelf stocker, or take the challenge and work your way up to becoming the ultimate factory worker! A hundred-million selling worldwide smash hit that made the inventor absolutely nothing!");
		games[1].setText("The game that started it all! PING fueled a frenzy of shameless overseas ripoffs, some of which spawned the major game console companies of today- who aggressively defend their own titles and shut down fan-made creations!");
		games[2].setText("Only one way forward! Smash the walls, crush your enemies, and grab as much gold as you can on your way to save the princess! Power up with hallucinogenic Fly Agaric mushrooms, famously hypothesized for giving the vikings their superhuman strength during berzerker raids! For ages 4 and up!");






		insideScrollPaneLayout.setHorizontalGroup
		(
			insideScrollPaneLayout.createParallelGroup(games)
		);

		insideScrollPaneLayout.setVerticalGroup
		(
			insideScrollPaneLayout.createSequentialGroup(games)
		);


		//---------------------
		//scrollpane
		//----------------------

		scrollPane = new ScrollPane(insideScrollPaneLayout);

		scrollPane.setTheme(GUIManager.scrollPaneTheme);
		scrollPane.setCanAcceptKeyboardFocus(false);
		scrollPane.setExpandContentSize(true);


		//---------------------
		//add scrollpane to outside panel
		//----------------------

		//mainPanelLayout.add(scrollPane);


		mainPanelLayout.setCanAcceptKeyboardFocus(false);
		mainPanelLayout.setHorizontalGroup
		(
				mainPanelLayout.createParallelGroup(scrollPane)
		);

		mainPanelLayout.setVerticalGroup
		(
				mainPanelLayout.createSequentialGroup(scrollPane)
		);


		add(mainPanelLayout);

	}

	//=========================================================================================================================
	public void setActivated(boolean b)
	{//=========================================================================================================================

		if(b==true&&(StatusBar().gameStoreButton.enabled==false||enabled()==false))return;

		super.setActivated(b);

	}




	//=========================================================================================================================
	public void render()
	{//=========================================================================================================================

		//additional rendering calls go here (after gui is drawn)


	}

}
