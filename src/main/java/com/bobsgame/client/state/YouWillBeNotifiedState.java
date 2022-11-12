package com.bobsgame.client.state;

import org.lwjgl.opengl.Display;
import com.bobsgame.ClientMain;
import com.bobsgame.client.GLUtils;
import com.bobsgame.shared.BobColor;

public class YouWillBeNotifiedState extends State {
	public YouWillBeNotifiedState() {

	}
	
	@Override
	public void update() {

	}

	@Override
	public void render() {
		ClientMain.glowTileBackground.render();

		String text = "To be continued...";

		GLUtils.drawFilledRect(0, 0, 0, 0, Display.getWidth(), 0, Display.getHeight(), 0.5f);
		GLUtils.drawOutlinedString(text, Display.getWidth() / 2 - GLUtils.font.getWidth(text) / 2, Display.getHeight() / 2, BobColor.white);
	}

	@Override
	public void cleanup() {

	}
}
