package com.bobsgame.client.engine.game.nd.ndmenu;

import org.lwjgl.opengl.Display;

import slick.Texture;

import com.bobsgame.GLUtils;
import com.bobsgame.client.engine.Engine;
import com.bobsgame.client.engine.EnginePart;
import com.bobsgame.client.engine.game.nd.ND;
import com.bobsgame.client.engine.game.nd.NDGameEngine;
import com.bobsgame.client.state.GlowTileBackground;
import com.bobsgame.client.state.GlowTileBackground.GlowTile;
import com.bobsgame.shared.Utils;



//=========================================================================================================================
public class NDMenuBackground extends GlowTileBackground
{//=========================================================================================================================

	//=========================================================================================================================
	public NDMenuBackground(Engine g)
	{//=========================================================================================================================
		super(g);
	}


	//=========================================================================================================================
	public void init()
	{//=========================================================================================================================

		numActiveTiles=6;
		scale = 0.75f;
		ticksPerFrame = 20;
		scrollSpeedTicksMultiplier = (1.0f/64.0f);
		filter = GLUtils.FILTER_LINEAR;


		tileFrames = 65;//get from generator tool output


		cleanup();

		bgScrollTexture = GLUtils.loadTexture("res/guiBackground/nDmenuBG.png");

		glowTileFramesTexture = new Texture[tileFrames];
		for(int i=0;i<tileFrames;i++)
		glowTileFramesTexture[i] = GLUtils.loadTexture("res/guiBackground/nDmenu/"+i+".png");

		glowTiles.clear();

		for(int i=0;i<numActiveTiles;i++)
		{
			glowTiles.add(new GlowTile());
		}

		glowTiles.get(0).started=true;
	}


}
