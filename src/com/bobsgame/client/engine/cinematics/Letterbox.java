package com.bobsgame.client.engine.cinematics;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.lwjgl.opengl.Display;

import com.bobsgame.GLUtils;
import com.bobsgame.client.engine.Engine;
import com.bobsgame.client.engine.EnginePart;
import com.bobsgame.client.engine.game.ClientGameEngine;
import com.bobsgame.shared.BobColor;
import com.bobsgame.shared.Utils;

import easing.Easing;


//TODO: improve speed function for this
//TODO: maybe automatically shut off letterbox if leave room

//TODO: need getters and setters


//=========================================================================================================================
public class Letterbox extends EnginePart
{//=========================================================================================================================


	//letterbox

	private float alpha=1.0f;


	private float sizeY=0;

	private float currentYOffset=0;

	private boolean on=false;




	private BobColor color = BobColor.black;

	private int ticksSlideDuration = 0;

	private long startTime = System.currentTimeMillis();

	//=========================================================================================================================
	/**
	 * letterbox takes in size, alpha, and speed.
	 * it will scroll from 0 to size on top and bottom.
	 * speed must be low. 0.1f * ticks(16) * 60fps = 96 pixels per second.
	 */
	public Letterbox(Engine g)
	{//=========================================================================================================================
		super(g);

	}

	//=========================================================================================================================
	public void init()
	{//=========================================================================================================================


	}


	public void setAlpha()
	{
		//TODO
	}

	public void setColor()
	{
		//TODO
	}


	public void setLayer()
	{
		//TODO over/under text? over/under statusbar?
	}


	//=========================================================================================================================
	public void setOn(int ticksSlideDuration, float percentY)
	{//=========================================================================================================================
		this.ticksSlideDuration = ticksSlideDuration;
		sizeY = (int)(Display.getHeight()*percentY);
		startTime = System.currentTimeMillis();
		on=true;

	}

	//=========================================================================================================================
	public void setOn(int ticksSlideDuration, int sizeY)
	{//=========================================================================================================================
		this.ticksSlideDuration = ticksSlideDuration;
		this.sizeY = sizeY;
		startTime = System.currentTimeMillis();
		on=true;
	}

	//=========================================================================================================================
	public void setOff(int ticksSlideDuration)
	{//=========================================================================================================================

		this.ticksSlideDuration = ticksSlideDuration;
		startTime = System.currentTimeMillis();
		on=false;
	}

	//=========================================================================================================================
	public void update()
	{//=========================================================================================================================


		float ticksPassed = (System.currentTimeMillis() - startTime)*Engine().engineSpeed;


		//if on, if scroll position isn't size_y scroll down
		if(on==true)
		{
			if(ticksPassed<ticksSlideDuration)
			{
				currentYOffset = (float)Easing.easeInOutQuadratic(ticksPassed,0,sizeY,ticksSlideDuration);
			}
			else
			{
				currentYOffset = sizeY;
			}
		}


		//if off, if scroll position isnt 0, scroll up
		if(on==false)
		{
			if(ticksPassed<ticksSlideDuration)
			{
				currentYOffset = (float)Easing.easeInOutQuadratic(ticksPassed,sizeY,0,ticksSlideDuration);
			}
			else
			{
				currentYOffset=0;
			}
		}






	}

	//=========================================================================================================================
	public void render()
	{//=========================================================================================================================
		GLUtils.drawFilledRect(color.getRed(),color.getGreen(),color.getBlue(), 0, Display.getWidth(), 0, sizeY, alpha);

		GLUtils.drawFilledRect(color.getRed(),color.getGreen(),color.getBlue(), 0, Display.getWidth(), Display.getHeight()-sizeY, Display.getHeight(), alpha);
	}


}
