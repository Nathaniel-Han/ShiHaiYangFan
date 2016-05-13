package com.shihai;

import org.loon.framework.android.game.LGameAndroid2DActivity;
import org.loon.framework.android.game.core.graphics.LImage;

public class Main extends LGameAndroid2DActivity{
	private LImage image=LImage.createImage("res/system/front.png");

	@Override
	public void onMain() {
		// TODO Auto-generated method stub
		this.initialization(true);
		this.setFPS(30);
		this.setLogo(image);
		this.setShowLogo(true);
		this.setShowFPS(true);
		this.setScreen(new TitleScreen(getApplicationContext()));
		this.showScreen();
		}

}
