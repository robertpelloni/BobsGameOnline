package com.bobsgame.client.engine.game.gui.statusbar.buttons;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import slick.Texture;

import com.bobsgame.client.GLUtils;
import com.bobsgame.client.engine.EnginePart;
import com.bobsgame.client.engine.game.ClientGameEngine;
import com.bobsgame.client.engine.game.gui.statusbar.StatusBar;


//=========================================================================================================================
public class StatusBarButton extends EnginePart
{//=========================================================================================================================


	public Texture texture = null;


	public boolean pulse = false;
	public boolean pulseInOut = false;
	public int pulseTicks = 0;
	public int lastPulseTicks = 0;


	public float glowAlpha = 1.0f;
	public boolean glow = false;


	public int offsetX0 = 0;
	public int offsetX1 = 0;//offset0+60;

	public int pressedOffsetY = 0;

	public int offsetY0 = 0;
	public int offsetY1 = 0;

	public int dividerX = 0;//offsetX1+20;

	public int glowX0 = 0;//offsetX0-60;
	public int glowX1 = 0;//offsetX1+60;

	public int glowY0 = 0;
	public int glowY1 = 0;

	public int clickX0 = 0;
	public int clickX1 = 0;


	public boolean enabled = true;



	//=========================================================================================================================
	public StatusBarButton(ClientGameEngine g)
	{//=========================================================================================================================
		super(g);
	}

	//=========================================================================================================================
	public void init()
	{//=========================================================================================================================

	}

	//=========================================================================================================================
	public void setOffsets()
	{//=========================================================================================================================


	}

	//=========================================================================================================================
	public void clicked()
	{//=========================================================================================================================

	}

	//=========================================================================================================================
	public boolean isAssociatedMenuActive()
	{//=========================================================================================================================
		return false;
	}

	//=========================================================================================================================
	public void update()
	{//=========================================================================================================================

		if(enabled==false)return;

		setOffsets();


		if(Mouse.getX()>clickX0&&Mouse.getX()<clickX1&&Mouse.getY()>(Display.getHeight()-StatusBar.sizeY))
		{
			glow=true;
			glowAlpha=1.0f;

			if(ControlsManager().MOUSEBUTTON_0_HELD==true)
			{
				pressedOffsetY=2;
			}
			else pressedOffsetY=0;

			if(ControlsManager().MOUSEBUTTON_0_PRESSED==true)
			{
				clicked();
			}

		}
		else if(isAssociatedMenuActive()==true)
		{
			glow=true;
		}
		else glow=false;




		if(pulse==true)
		{
			glow=true;

			pulseTicks+=Engine().engineTicksPassed();

			if(pulseTicks>lastPulseTicks+1000)
			{
				lastPulseTicks=pulseTicks;

				pulseInOut=!pulseInOut;
			}

			if(pulseInOut==true)
			{
				glowAlpha+=((float)Engine().engineTicksPassed()/1000.0f);
				if(glowAlpha>1.0f) glowAlpha=1.0f;
			}
			else
			{
				glowAlpha-=((float)Engine().engineTicksPassed()/1000.0f);
				if(glowAlpha<0.0f) glowAlpha=0.0f;
			}

		}
		else
		{
			glowAlpha=1.0f;
		}


	}

	//=========================================================================================================================
	public void setEnabled(boolean b)
	{//=========================================================================================================================
		enabled = b;
	}

	//=========================================================================================================================
	public void render(int layer)
	{//=========================================================================================================================


		if(enabled==false)return;

		if(texture==null) return;


		if(layer==0)
		{

			if(glow)GLUtils.drawTexture(StatusBar.glowTexture,glowX0,glowX1,glowY0,glowY1,glowAlpha,GLUtils.FILTER_LINEAR);


			GLUtils.drawTexture(texture,offsetX0,offsetX1,offsetY0+pressedOffsetY,(StatusBar.sizeY-offsetY1)+pressedOffsetY,1.0f,GLUtils.FILTER_LINEAR);


			if(glow)GLUtils.drawTexture(StatusBar.glowTexture,glowX0,glowX1,glowY0,glowY1,0.2f,GLUtils.FILTER_LINEAR);

		}

		if(layer==1)
		{

			GLUtils.drawTexture(StatusBar.dividerTexture,dividerX,dividerX+3,0,StatusBar.sizeY-1,1.0f,GLUtils.FILTER_LINEAR);

		}

	}







}
