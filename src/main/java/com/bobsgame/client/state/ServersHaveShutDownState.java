package com.bobsgame.client.state;

import org.lwjgl.opengl.Display;
import com.bobsgame.ClientMain;
import com.bobsgame.client.GLUtils;
import com.bobsgame.shared.BobColor;

public class ServersHaveShutDownState extends State {
	public ServersHaveShutDownState() {

	}

	@Override
	public void update() {

	}

	@Override
	public void render() {
		ClientMain.glowTileBackground.render();

		GLUtils.drawFilledRect(0, 0, 0, 0, Display.getWidth(), 0, Display.getHeight(), 0.5f);
		GLUtils.drawOutlinedString("The servers have shut down for updating.", Display.getWidth() / 2 - 60, Display.getHeight() / 2 - 12, BobColor.white);
		GLUtils.drawOutlinedString("Your progress was saved. Please reload the client.", Display.getWidth() / 2 - 70, Display.getHeight() / 2 + 12, BobColor.gray);
	}

	@Override
	public void cleanup() {

	}
}