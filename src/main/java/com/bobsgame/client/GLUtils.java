package com.bobsgame.client;


import static org.lwjgl.opengl.GL11.*;
//import static org.lwjgl.opengl.GL30.*;
//import static org.lwjgl.opengl.GL31.*;
//import static org.lwjgl.opengl.GL32.*;
//import static org.lwjgl.opengl.GL33.*;
//import static org.lwjgl.opengl.GL40.*;
//import static org.lwjgl.opengl.GL41.*;
//import static org.lwjgl.opengl.GL42.*;
//import static org.lwjgl.opengl.GL43.*;


import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL14;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;


import org.slf4j.LoggerFactory;

import com.bobsgame.shared.BobColor;
import com.bobsgame.shared.Utils;

import slick.InternalTextureLoader;
import slick.SlickCallable;
import slick.Texture;
import slick.TextureLoader;
import slick.TrueTypeFont;



import ch.qos.logback.classic.Logger;





//=========================================================================================================================
public class GLUtils
{//=========================================================================================================================

	public static Logger log = (Logger) LoggerFactory.getLogger(GLUtils.class);

	static public TrueTypeFont font;

	static private boolean antiAlias = true;

	public static int texturesLoaded = 0;
	public static long textureBytesLoaded = 0;



	public static Texture blankTexture = null;
	public static Texture boxTexture = null;



	public final static int FILTER_FBO_LINEAR_NO_MIPMAPPING = -2;
	public final static int FILTER_FBO_NEAREST_NO_MIPMAPPING = -1;
	public final static int FILTER_NEAREST = 0;
	public final static int FILTER_LINEAR = 1;



	public static float globalDrawScale = 1.0f;

	//=========================================================================================================================
	public GLUtils()
	{//=========================================================================================================================

		blankTexture = GLUtils.loadTexture("res/misc/blank.png");
		boxTexture = GLUtils.loadTexture("res/misc/box.png");



		//load font from file
		try
		{
			InputStream inputStream = Utils.getResourceAsStream("res/fonts/bobsgame.ttf");

			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(8f); // set font size
			font = new TrueTypeFont(awtFont, antiAlias);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}





		/*
		 * 	public void initDisplay(int width, int height) {
		this.width = width;
		this.height = height;

		String extensions = GL11.glGetString(GL11.GL_EXTENSIONS);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClearDepth(1);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}


	public void enterOrtho(int xsize, int ysize) {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glTranslatef((width-xsize)/2,
						(height-ysize)/2,0);
	}



		if (antialias) {
			GL.glEnable(SGL.GL_LINE_SMOOTH);
		} else {
			GL.glDisable(SGL.GL_LINE_SMOOTH);



	*/
	}



	//=========================================================================================================================
	static public Texture loadTexture(String name, int imageWidth, int imageHeight, ByteBuffer textureByteBuffer)
	{//=========================================================================================================================

		int texWidth=Utils.getClosestPowerOfTwo(imageWidth);
		int texHeight=Utils.getClosestPowerOfTwo(imageHeight);

		int textureID = InternalTextureLoader.createTextureID();
		Texture textureImpl = new Texture(name, GL_TEXTURE_2D, textureID);


		//textureImpl.bind();//use this so slick keeps track of last texture used
		//TODO: write my own Utils.bind that tracks textureID and doesn't rebind (also if textureID is released, set bind to null)
		//look at what slick does for all that, or use slick more.

		glBindTexture(GL_TEXTURE_2D, textureID);

		textureImpl.setTextureWidth(texWidth);
		textureImpl.setTextureHeight(texHeight);



		IntBuffer temp = BufferUtils.createIntBuffer(16);
		glGetInteger(GL_MAX_TEXTURE_SIZE, temp);
		int max = temp.get(0);
		if ((texWidth > max) || (texHeight > max)) {
			try
			{
				throw new IOException("Attempt to allocate a texture too big for the current hardware");
			}
			catch (IOException e)
			{

				e.printStackTrace();
			}
		}


		textureImpl.setWidth(imageWidth);
		textureImpl.setHeight(imageHeight);
		textureImpl.setAlpha(true);




		//glBindTexture(GL_TEXTURE_2D, textureID);

		//glEnable(GL_TEXTURE_2D);

		setDefaultTextureParams();

		glTexImage2D(

						GL_TEXTURE_2D,   // type of texture we're creating
						0,                      // level-of-detail: use 0
						GL_RGBA8,           // texture pixel format
						texWidth, texHeight, //width and height of texture image (powers of 2)
						0,                      // width of the border (either 0 or 1, use 0)
						GL_RGBA,           // image pixel format
						GL_UNSIGNED_BYTE,  // image pixel data type
						textureByteBuffer                  // image pixel data

		);

		//glDisable(GL_TEXTURE_2D);


		texturesLoaded++;
		textureBytesLoaded+=(texWidth*texHeight*4);


		return textureImpl;

	}

	//=========================================================================================================================
	static public void setDefaultTextureParams()
	{//=========================================================================================================================

		//THIS WAS THE PROBLEM WITH TEXTURES SHOWING UP WHITE, NEEDED TO GENERATE MIPMAPS FOR SOME REASON

		//why don't i need to do this for captions????

		glTexParameteri(GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL, 0);
		glTexParameteri(GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, 2);//0=100% 1=50% 2=25%
		glTexParameteri(GL_TEXTURE_2D, GL14.GL_GENERATE_MIPMAP, GL_TRUE);
		//glTexEnvf(GL_TEXTURE_FILTER_CONTROL, GL_TEXTURE_LOD_BIAS, -1.5f);


		//glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		//glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		//glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST); //GL11.GL_NEAREST);
		//glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST); //GL11.GL_NEAREST);

	}


	//=========================================================================================================================
	static public Texture loadTexture(String s)
	{//=========================================================================================================================
		try
		{

			InputStream i = Utils.getResourceAsStream(s);

			if(i==null)
			{
				log.error("Could not open file for texture: "+s);
				return null;
			}

			Texture t = TextureLoader.getTexture("PNG", i);

			texturesLoaded++;
			textureBytesLoaded+=(t.getTextureWidth()*t.getTextureHeight()*4);


//			glBindTexture(GL_TEXTURE_2D, t.getTextureID());
//			//glEnable(GL_TEXTURE_2D);
//			glTexParameteri(GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL, 0);
//			glTexParameteri(GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, 2);//0=100% 1=50% 2=25%
//			glTexParameteri(GL_TEXTURE_2D, GL14.GL_GENERATE_MIPMAP, GL_TRUE);

			//glTexEnvf(GL_TEXTURE_FILTER_CONTROL, GL_TEXTURE_LOD_BIAS, -1.5f);
			//glDisable(GL_TEXTURE_2D);

			return t;
		}
		catch (IOException e)
		{
			log.error("Could not load texture: "+s);
			e.printStackTrace();
			return null;
		}
	}


	//=========================================================================================================================
	static public Texture releaseTexture(Texture t)
	{//=========================================================================================================================

		texturesLoaded--;
		textureBytesLoaded-=(t.getTextureWidth()*t.getTextureHeight()*4);

		t.release();

		return null;


	}


	static public FloatBuffer boxBuffer = BufferUtils.createFloatBuffer(12);
	static public FloatBuffer colBuffer = BufferUtils.createFloatBuffer(16);
	static public FloatBuffer texBuffer = BufferUtils.createFloatBuffer(8);

	static float box[] = new float[12];
	static float col[] = new float[16];
	static float tex[] = new float[8];



	//=========================================================================================================================
	public static void drawTexture(Texture texture, float sx0, float sy0, int filter)
	{//=========================================================================================================================
		drawTexture(texture,sx0,sy0,1.0f,filter);
	}

	//=========================================================================================================================
	public static void drawTexture(Texture texture, float sx0, float sy0, float alpha, int filter)
	{//=========================================================================================================================

		float sx1 = sx0+texture.getImageWidth();
		float sy1 = sy0+texture.getImageHeight();

		drawTexture(texture,sx0,sx1,sy0,sy1,alpha,filter);
	}

	//===========================================================================================================================
	static public void drawTexture(Texture texture, float sx0, float sx1, float sy0, float sy1, float alpha, int filter)
	{//===========================================================================================================================

		float tXRatio = (float)texture.getImageWidth()/(float)texture.getTextureWidth();
		float tYRatio = (float)texture.getImageHeight()/(float)texture.getTextureHeight();

		float ix0 = 0.0f;
		float ix1 = 1.0f;

		float iy0 = 0.0f;
		float iy1 = 1.0f;

		float tx0 = ix0 * tXRatio;
		float tx1 = ix1 * tXRatio;

		float ty0 = iy0 * tYRatio;
		float ty1 = iy1 * tYRatio;

		drawTexture(texture,tx0,tx1,ty0,ty1,sx0,sx1,sy0,sy1,alpha,filter);
	}

	//===========================================================================================================================
	static public void drawTexture(Texture texture, float tx0, float tx1, float ty0, float ty1, float sx0, float sx1, float sy0, float sy1, float alpha, int filter)
	{//===========================================================================================================================
		if(texture==null)return;

		drawTexture(texture.getTextureID(), tx0, tx1, ty0, ty1, sx0, sx1, sy0, sy1, alpha, filter);
	}

	//===========================================================================================================================
	static public void drawTexture(int textureID, float tx0, float tx1, float ty0, float ty1, float sx0, float sx1, float sy0, float sy1, float alpha, int filter)
	{//===========================================================================================================================

		glBindTexture(GL_TEXTURE_2D, textureID);
		drawTexture(tx0, tx1, ty0, ty1, sx0, sx1, sy0, sy1, 1.0f, 1.0f, 1.0f,alpha, filter);

	}
	//===========================================================================================================================
	static public void drawTexture(float tx0, float tx1, float ty0, float ty1, float sx0, float sx1, float sy0, float sy1, float alpha, int filter)
	{//===========================================================================================================================

		drawTexture(tx0, tx1, ty0, ty1, sx0, sx1, sy0, sy1, 1.0f, 1.0f, 1.0f, alpha, filter);

	}
	//===========================================================================================================================
	public static void drawTexture(Texture texture,float tx0,float tx1,float ty0,float ty1,float sx0,float sx1,float sy0,float sy1,float r,float g,float b,float a,int filter)
	{//===========================================================================================================================

		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
		drawTexture(tx0, tx1, ty0, ty1, sx0, sx1, sy0, sy1, r, g, b, a, filter);


	}


	//===========================================================================================================================
	static public void drawTexture(float textureX0, float textureX1, float textureY0, float textureY1, float screenX0, float screenX1, float screenY0, float screenY1, float r, float g, float b, float a, int filter)
	{//===========================================================================================================================

		screenX0*=globalDrawScale;
		screenX1*=globalDrawScale;
		screenY0*=globalDrawScale;
		screenY1*=globalDrawScale;




		glEnable(GL_TEXTURE_2D);

		if(filter==FILTER_FBO_LINEAR_NO_MIPMAPPING)//for FBO rendering only
		{
			glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
		}
		else
		if(filter==FILTER_FBO_NEAREST_NO_MIPMAPPING)//for FBO rendering only
		{
			glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);
		}
		else
		if(filter==FILTER_NEAREST)
		{


			glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR_MIPMAP_LINEAR);
			glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);

			//if(Keyboard.isKeyDown(Keyboard.KEY_1))glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
		}
		else
		if(filter==FILTER_LINEAR)
		{
			glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR_MIPMAP_LINEAR);
			glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);

			//if(Keyboard.isKeyDown(Keyboard.KEY_1))glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
		}

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		//glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_R, GL12.GL_CLAMP_TO_EDGE); //TODO:what does this do and why did i comment it out? i forget.



		box[0]=screenX0;
		box[1]=screenY0;
		box[2]=0.0f;
		box[3]=screenX0;
		box[4]=screenY1;
		box[5]=0.0f;
		box[6]=screenX1;
		box[7]=screenY1;
		box[8]=0.0f;
		box[9]=screenX1;
		box[10]=screenY0;
		box[11]=0.0f;

		boxBuffer.put(box);
		boxBuffer.position(0);


		col[0] = r;
		col[1] = g;
		col[2] = b;
		col[3] = a;

		col[4] = r;
		col[5] = g;
		col[6] = b;
		col[7] = a;

		col[8] = r;
		col[9] = g;
		col[10] = b;
		col[11] = a;

		col[12] = r;
		col[13] = g;
		col[14] = b;
		col[15] = a;


		colBuffer.put(col);
		colBuffer.position(0);


		tex[0] = textureX0;
		tex[1] = textureY0;
		tex[2] = textureX0;
		tex[3] = textureY1;
		tex[4] = textureX1;
		tex[5] = textureY1;
		tex[6] = textureX1;
		tex[7] = textureY0;

		texBuffer.put(tex);
		texBuffer.position(0);

		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);

		glVertexPointer(3, 3*4,  boxBuffer);
		glColorPointer(4, 4*4,  colBuffer);
		glTexCoordPointer(2, 2*4,  texBuffer);

		glDrawArrays(GL_TRIANGLE_FAN,0,4);

		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_COLOR_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);



//
//		glColor4f(1,1,1,1);
//
//		glEnable(GL_COLOR_MATERIAL);
//
//		glColor4f(1,1,1,a);
//
//
//		if(Keyboard.isKeyDown(Keyboard.KEY_1))glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL13.GL_COMBINE);
//		if(Keyboard.isKeyDown(Keyboard.KEY_2))glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_BLEND);
//		if(Keyboard.isKeyDown(Keyboard.KEY_3))glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
//
//
//		if(Keyboard.isKeyDown(Keyboard.KEY_4))a=0.5f;
//


//		glEnableClientState(GL_COLOR_ARRAY);
//		glEnable(GL_COLOR_MATERIAL);
//		glColor4f(r,g,b,a);
//
//		//glEnable(GL11.GL_BLEND);
//
//		//glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL_COMBINE);
//		//glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL_MODULATE);//GL_BLEND//GL_COMBINE
//		//glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL_REPLACE);
////		glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_REPLACE);
////		glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
//
//		glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_BLEND);
//
//
//
//
//		if(Keyboard.isKeyDown(Keyboard.KEY_4))glTexEnvi(GL11.GL_TEXTURE_ENV, GL13.GL_COMBINE_RGB, GL11.GL_MODULATE);
//		if(Keyboard.isKeyDown(Keyboard.KEY_5))glTexEnvi(GL11.GL_TEXTURE_ENV, GL13.GL_COMBINE_ALPHA, GL11.GL_MODULATE);
//
//		if(Keyboard.isKeyDown(Keyboard.KEY_6))glTexEnvi(GL11.GL_TEXTURE_ENV, GL13.GL_COMBINE_RGB, GL11.GL_BLEND);
//		if(Keyboard.isKeyDown(Keyboard.KEY_7))glTexEnvi(GL11.GL_TEXTURE_ENV, GL13.GL_COMBINE_ALPHA, GL11.GL_BLEND);
//
//		if(Keyboard.isKeyDown(Keyboard.KEY_8))glTexEnvi(GL11.GL_TEXTURE_ENV, GL13.GL_COMBINE_RGB, GL13.GL_COMBINE);
//		if(Keyboard.isKeyDown(Keyboard.KEY_9))glTexEnvi(GL11.GL_TEXTURE_ENV, GL13.GL_COMBINE_ALPHA, GL13.GL_COMBINE);
//
//
////		glTexEnvi(GL11.GL_TEXTURE_ENV, GL13.GL_SOURCE0_RGB, GL13.GL_CONSTANT);
////		glTexEnvi(GL11.GL_TEXTURE_ENV, GL13.GL_OPERAND0_RGB, GL11.GL_SRC_COLOR);
//
//
//		if(Keyboard.isKeyDown(Keyboard.KEY_0))glTexEnvi(GL11.GL_TEXTURE_ENV, GL13.GL_SOURCE0_ALPHA, GL11.GL_TEXTURE);
//		if(Keyboard.isKeyDown(Keyboard.KEY_0))glTexEnvi(GL11.GL_TEXTURE_ENV, GL13.GL_SOURCE1_ALPHA, GL13.GL_CONSTANT);
////		glTexEnvi(GL11.GL_TEXTURE_ENV, GL13.GL_OPERAND0_RGB, GL11.GL_SRC_ALPHA);
////		glTexEnvi(GL11.GL_TEXTURE_ENV, GL13.GL_OPERAND0_RGB, GL11.GL_SRC_ALPHA);
//
//
//		glBegin(GL_QUADS);
//			glTexCoord2f(textureX0, textureY0); glVertex3f(screenX0, screenY0,  0.0f);
//			glTexCoord2f(textureX0, textureY1); glVertex3f(screenX0, screenY1,  0.0f);
//			glTexCoord2f(textureX1, textureY1); glVertex3f(screenX1, screenY1,  0.0f);
//			glTexCoord2f(textureX1, textureY0); glVertex3f(screenX1, screenY0,  0.0f);
//		glEnd();
//
//
//		glDisableClientState(GL_COLOR_ARRAY);
//		glDisable(GL_COLOR_MATERIAL);

		//glDisable(GL_TEXTURE_2D);


	}

	//=========================================================================================================================
	public static void drawOutlinedString(String text, int screenX, int screenY, BobColor color)
	{//=========================================================================================================================
		drawOutlinedString(screenX,screenY,text,color);
	}


	//=========================================================================================================================
	public static void drawOutlinedString(int screenX0, int screenY0, String text, BobColor color)
	{//=========================================================================================================================

//
//		if(font==null)
//		{
//			try
//			{
//				InputStream inputStream = Utils.getResourceAsStream("res/fonts/bobsgame.ttf");
//
//				Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
//				awtFont = awtFont.deriveFont(8f); // set font size
//				font = new TrueTypeFont(awtFont, antiAlias);
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}

		if(font==null){log.error("Font is null");return;}
		if(text==null){log.error("Text is null");return;}

		screenX0*=globalDrawScale;
		//screenX1*=globalDrawScale;
		screenY0*=globalDrawScale;
		//screenY1*=globalDrawScale;
		//
		//Renderer.setRenderer(1);

		//glEnable(GL_TEXTURE_2D);

		//Color.white.bind();
		//GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		//glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
		//glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);


		SlickCallable.enterSafeBlock();
		{
			font.drawString(screenX0-1,screenY0-1, text, BobColor.black);
			font.drawString(screenX0+1,screenY0-1, text, BobColor.black);
			font.drawString(screenX0-1,screenY0+1, text, BobColor.black);
			font.drawString(screenX0+1,screenY0+1, text, BobColor.black);
			font.drawString(screenX0+1,screenY0, text, BobColor.black);
			font.drawString(screenX0-1,screenY0, text, BobColor.black);
			font.drawString(screenX0,screenY0+1, text, BobColor.black);
			font.drawString(screenX0,screenY0-1, text, BobColor.black);
			font.drawString(screenX0,screenY0, text, new BobColor(color.r(),color.g(),color.b()));


			//glEnable(GL_TEXTURE_2D);
		}
		SlickCallable.leaveSafeBlock();
	}

	//=========================================================================================================================
	public static void drawLine(float screenX0, float screenY0, float screenX1, float screenY1, int r, int g, int b)
	{//=========================================================================================================================
		drawLine(screenX0, screenY0, screenX1, screenY1, r/255.0f, g/255.0f, b/255.0f, 1.0f);
	}

	//=========================================================================================================================
	public static void drawLine(float screenX0, float screenY0, float screenX1, float screenY1, float r, float g, float b)
	{//=========================================================================================================================
		drawLine(screenX0, screenY0, screenX1, screenY1, r, g, b, 1.0f);
	}
	//=========================================================================================================================
	public static void drawLine(float screenX0, float screenY0, float screenX1, float screenY1, float r, float g, float b, float a)
	{//=========================================================================================================================

		screenX0*=globalDrawScale;
		screenX1*=globalDrawScale;
		screenY0*=globalDrawScale;
		screenY1*=globalDrawScale;

		glDisable(GL_TEXTURE_2D);

		glColor4f(r,g,b,a);


		glBegin(GL_LINES);
		glVertex2f(screenX0, screenY0);
		glVertex2f(screenX1, screenY1);
		glEnd();

	}

	//===============================================================================================
	public static void drawArrowLine(float screenX0, float screenY0, float screenX1, float screenY1, int r, int g, int b)
	{//===============================================================================================

		drawLine(screenX0, screenY0, screenX1, screenY1, r, g, b);


		screenX0*=globalDrawScale;
		screenX1*=globalDrawScale;
		screenY0*=globalDrawScale;
		screenY1*=globalDrawScale;


		//get distance
		float dx = (screenX1-screenX0);
		float dy = (screenY1-screenY0);

		//get midpoint from distance
		float midX = (screenX0+dx/2);
		float midY = (screenY0+dy/2);

		//make distance half since we are using midpoint
		dx/=2;
		dy/=2;

		//get actual distance to midpoint
		float dist = (float) Math.sqrt(dx*dx + dy*dy);

		//get distance ratio
		float distXRatio=dx/dist;
		float distYRatio=dy/dist;

		int arrowWidth = 12;

		//get perpendicular points to midpoint
		float midXPerpX1 =  (midX + (arrowWidth/2)*distYRatio);
		float midYPerpY1 =  (midY - (arrowWidth/2)*distXRatio);
		float midXPerpX2 =  (midX - ((arrowWidth)/2)*distYRatio);
		float midYPerpY2 =  (midY + ((arrowWidth)/2)*distXRatio);


		//get point 10 pixels past midpoint
		float pastMidX = (screenX0 + (dist+16)*distXRatio);
		float pastMidY = (screenY0 + (dist+16)*distYRatio);

		drawLine(midXPerpX1, midYPerpY1, midXPerpX2, midYPerpY2, r, g, b);
		drawLine(midXPerpX1, midYPerpY1, pastMidX, pastMidY, r, g, b);
		drawLine(midXPerpX2, midYPerpY2, pastMidX, pastMidY, r, g, b);

	}



	//=========================================================================================================================
	public static void drawBox(float screenX0, float screenX1, float screenY0, float screenY1, int r, int g, int b)
	{//=========================================================================================================================
		screenX0*=globalDrawScale;
		screenX1*=globalDrawScale;
		screenY0*=globalDrawScale;
		screenY1*=globalDrawScale;


		glColor3f(r/255.0f,g/255.0f,b/255.0f);

		//top
		glBegin(GL_LINES);
		glVertex2f(screenX0, screenY0);
		glVertex2f(screenX1, screenY0);
		//glEnd();

		//bottom
		//glBegin(GL_LINES);
		glVertex2f(screenX0, screenY1);
		glVertex2f(screenX1, screenY1);
		//glEnd();


		//left
		//glBegin(GL_LINES);
		glVertex2f(screenX0, screenY0);
		glVertex2f(screenX0, screenY1);
		//glEnd();

		//right
		//glBegin(GL_LINES);
		glVertex2f(screenX1, screenY0);
		glVertex2f(screenX1, screenY1);
		glEnd();
		/*

		glBindTexture(GL_TEXTURE_2D, Utils.boxTexture.getTextureID());

		float tx0;
		float tx1;
		float ty0;
		float ty1;

		float x0;
		float x1;
		float y0;
		float y1;


		//top left
		tx0 = 0.0f;
		tx1 = 3.0f/Utils.boxTexture.getTextureWidth();
		ty0 = 0.0f;
		ty1 = 3.0f/Utils.boxTexture.getTextureHeight();

		x0 = screenLeft-3;
		x1 = screenLeft;
		y0 = screenTop-3;
		y1 = screenTop;

		GL.drawTexture(r,g,b,tx0,tx1,ty0,ty1,x0,x1,y0,y1,1.0f,0);

		//top

		tx0 = 3.0f/Utils.boxTexture.getTextureWidth();
		tx1 = 6.0f/Utils.boxTexture.getTextureWidth();
		ty0 = 0.0f/Utils.boxTexture.getTextureHeight();
		ty1 = 3.0f/Utils.boxTexture.getTextureHeight();

		x0 = screenLeft;
		x1 = screenRight;
		y0 = screenTop-3;
		y1 = screenTop;

		GL.drawTexture(r,g,b,tx0,tx1,ty0,ty1,x0,x1,y0,y1,1.0f,0);

		//top right

		tx0 = 6.0f/Utils.boxTexture.getTextureWidth();
		tx1 = 9.0f/Utils.boxTexture.getTextureWidth();
		ty0 = 0.0f/Utils.boxTexture.getTextureHeight();
		ty1 = 3.0f/Utils.boxTexture.getTextureHeight();

		x0 = screenRight;
		x1 = screenRight+3;
		y0 = screenTop-3;
		y1 = screenTop;

		GL.drawTexture(r,g,b,tx0,tx1,ty0,ty1,x0,x1,y0,y1,1.0f,0);


		//left
		tx0 = 0.0f;
		tx1 = 3.0f/Utils.boxTexture.getTextureWidth();
		ty0 = 3.0f/Utils.boxTexture.getTextureHeight();
		ty1 = 6.0f/Utils.boxTexture.getTextureHeight();

		x0 = screenLeft-3;
		x1 = screenLeft;
		y0 = screenTop;
		y1 = screenBottom;

		GL.drawTexture(r,g,b,tx0,tx1,ty0,ty1,x0,x1,y0,y1,1.0f,0);


		//right
		tx0 = 6.0f/Utils.boxTexture.getTextureWidth();
		tx1 = 9.0f/Utils.boxTexture.getTextureWidth();
		ty0 = 3.0f/Utils.boxTexture.getTextureHeight();
		ty1 = 6.0f/Utils.boxTexture.getTextureHeight();

		x0 = screenRight;
		x1 = screenRight+3;
		y0 = screenTop;
		y1 = screenBottom;

		GL.drawTexture(r,g,b,tx0,tx1,ty0,ty1,x0,x1,y0,y1,1.0f,0);


		//bottom left
		tx0 = 0.0f;
		tx1 = 3.0f/Utils.boxTexture.getTextureWidth();
		ty0 = 6.0f/Utils.boxTexture.getTextureHeight();
		ty1 = 9.0f/Utils.boxTexture.getTextureHeight();

		x0 = screenLeft-3;
		x1 = screenLeft;
		y0 = screenBottom;
		y1 = screenBottom+3;

		GL.drawTexture(r,g,b,tx0,tx1,ty0,ty1,x0,x1,y0,y1,1.0f,0);


		//bottom
		tx0 = 3.0f/Utils.boxTexture.getTextureWidth();
		tx1 = 6.0f/Utils.boxTexture.getTextureWidth();
		ty0 = 6.0f/Utils.boxTexture.getTextureHeight();
		ty1 = 9.0f/Utils.boxTexture.getTextureHeight();

		x0 = screenLeft;
		x1 = screenRight;
		y0 = screenBottom;
		y1 = screenBottom+3;

		GL.drawTexture(r,g,b,tx0,tx1,ty0,ty1,x0,x1,y0,y1,1.0f,0);


		//bottom right
		tx0 = 6.0f/Utils.boxTexture.getTextureWidth();
		tx1 = 9.0f/Utils.boxTexture.getTextureWidth();
		ty0 = 6.0f/Utils.boxTexture.getTextureHeight();
		ty1 = 9.0f/Utils.boxTexture.getTextureHeight();

		x0 = screenRight;
		x1 = screenRight+3;
		y0 = screenBottom;
		y1 = screenBottom+3;

		GL.drawTexture(r,g,b,tx0,tx1,ty0,ty1,x0,x1,y0,y1,1.0f,0);

*/


	}

	//===========================================================================================================================
	static public void drawFilledRect(int ri, int gi, int bi, float screenX0, float screenX1, float screenY0, float screenY1, float alpha)
	{//===========================================================================================================================

		screenX0*=globalDrawScale;
		screenX1*=globalDrawScale;
		screenY0*=globalDrawScale;
		screenY1*=globalDrawScale;


		glDisable(GL_TEXTURE_2D);

		glColor4f(ri/255.0f,gi/255.0f,bi/255.0f, alpha);

//		//top
//		glBegin(GL_TRIANGLE_FAN);
//		glVertex2f(screen_x0, screen_y0);
//		glVertex2f(screen_x1, screen_y0);
//		//glEnd();
//
//		//bottom
//		glVertex2f(screen_x0, screen_y1);
//		glVertex2f(screen_x1, screen_y1);
//		//glEnd();
//
//
//		//left
//		glVertex2f(screen_x0, screen_y0);
//		glVertex2f(screen_x0, screen_y1);
//		//glEnd();
//
//		//right
//		glVertex2f(screen_x1, screen_y0);
//		glVertex2f(screen_x1, screen_y1);
//		glEnd();



		box[0]=screenX0;
		box[1]=screenY0;
		box[2]=0.0f;
		box[3]=screenX0;
		box[4]=screenY1;
		box[5]=0.0f;
		box[6]=screenX1;
		box[7]=screenY1;
		box[8]=0.0f;
		box[9]=screenX1;
		box[10]=screenY0;
		box[11]=0.0f;

		boxBuffer.put(box);
		boxBuffer.position(0);

		glEnableClientState(GL_VERTEX_ARRAY);

		glVertexPointer(3, 3*4,  boxBuffer);

		glDrawArrays(GL_TRIANGLE_FAN,0,4);

		glDisableClientState(GL_VERTEX_ARRAY);
	}

	//=========================================================================================================================
	static public void drawFilledRectXYWH(float x, float y, float w, float h, float r, float g, float b, float a)
	{//=========================================================================================================================


		drawFilledRect((int)(r*255.0f),(int)(g*255.0f),(int)(b*255.0f),x,x+w,y,y+h,a);


//		glDisable(GL_TEXTURE_2D);
//
//
//		float screen_x0 = x;
//		float screen_x1 = x+w;
//		float screen_y0 = y;
//		float screen_y1 = y+h;
//
//
//		box[0]=screen_x0;
//		box[1]=screen_y0;
//		box[2]=0.0f;
//		box[3]=screen_x0;
//		box[4]=screen_y1;
//		box[5]=0.0f;
//		box[6]=screen_x1;
//		box[7]=screen_y1;
//		box[8]=0.0f;
//		box[9]=screen_x1;
//		box[10]=screen_y0;
//		box[11]=0.0f;
//
//		boxBuffer.put(box);
//		boxBuffer.position(0);
//
//		col[0] = r;
//		col[1] = g;
//		col[2] = b;
//		col[3] = a;
//
//		col[4] = r;
//		col[5] = g;
//		col[6] = b;
//		col[7] = a;
//
//		col[8] = r;
//		col[9] = g;
//		col[10] = b;
//		col[11] = a;
//
//		col[12] = r;
//		col[13] = g;
//		col[14] = b;
//		col[15] = a;
//
//
//		colBuffer.put(col);
//		colBuffer.position(0);
//
//		glEnableClientState(GL_VERTEX_ARRAY);
//		glEnableClientState(GL_COLOR_ARRAY);
//
//		glVertexPointer(3, 3*4,  boxBuffer);
//		glColorPointer(4, 4*4,  colBuffer);
//		glDrawArrays(GL_TRIANGLE_FAN,0,4);
//
//		glDisableClientState(GL_COLOR_ARRAY);
//		glDisableClientState(GL_VERTEX_ARRAY);
//
//		glEnable(GL_TEXTURE_2D);

	}














}
