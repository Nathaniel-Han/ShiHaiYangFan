package com.shihai;

import org.loon.framework.android.game.action.sprite.effect.Fade;
import org.loon.framework.android.game.core.LSystem;
import org.loon.framework.android.game.core.graphics.LColor;
import org.loon.framework.android.game.core.graphics.Screen;
import org.loon.framework.android.game.core.graphics.device.LGraphics;
import org.loon.framework.android.game.core.graphics.window.LButton;
import org.loon.framework.android.game.core.timer.LTimerContext;
import org.loon.framework.android.game.media.sound.AssetsSound;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class TitleScreen extends Screen{
	LButton play_game,learn,exit;
	Fade fade;
	AssetsSound sound;
	Context context;
	boolean init=false;
	public TitleScreen(Context context){
		super();
		this.context=context;
	}
//	LButton test;
	
	public void onLoad()
	{
		fade=Fade.getInstance(Fade.TYPE_FADE_IN, LColor.black);
		add(fade);
		setBackground("res/system/title.png",false);
	}
	
	public void alter(LTimerContext c){
		if(fade.isStop()&&!init){
			sound=new AssetsSound("title.mid");
			sound.loop();
			play_game=new LButton("res/system/menu_start.png",154,46){
				public void doClick(){
					sound.stop();
					setScreen(new Game(context));
				}
			};
		play_game.setLocation(158, 80);
		play_game.setEnabled(false);
		add(play_game);
		
			learn=new LButton("res/system/menu_learn.png",154,46){
			public void doClick(){
		        Intent intent = new Intent();
		        intent.setClass(LSystem.getActivity(),poemActivity.class);
		        LSystem.getActivity().startActivity(intent);
		        }
		};
		learn.setLocation(158,140);
		learn.setEnabled(false);
		add(learn);
		
		exit=new LButton("res/system/menu_exit.png",154,46){
			public void doClick(){
				LSystem.exit();
			}
		};
		exit.setLocation(158,200);
		exit.setEnabled(false);
		add(exit);
		
		init=true;
		}
		if(init)
		{
			play_game.setEnabled(true);
			learn.setEnabled(true);
			exit.setEnabled(true);
//			test.setEnabled(true);
		}
		
		
	}

	@Override
	public void draw(LGraphics arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouch(float arg0, float arg1, MotionEvent arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onKeyDown(int arg0, KeyEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKeyUp(int arg0, KeyEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchMove(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
