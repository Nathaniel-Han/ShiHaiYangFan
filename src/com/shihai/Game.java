package com.shihai;

import org.loon.framework.android.game.action.avg.AVGScreen;
import org.loon.framework.android.game.action.avg.MessageDialog;
import org.loon.framework.android.game.action.avg.command.Command;
import org.loon.framework.android.game.core.LSystem;
import org.loon.framework.android.game.core.graphics.LColor;
import org.loon.framework.android.game.core.graphics.LFont;
import org.loon.framework.android.game.core.graphics.device.LGraphics;
import org.loon.framework.android.game.core.graphics.window.LButton;
import org.loon.framework.android.game.core.graphics.window.LMessage;
import org.loon.framework.android.game.core.graphics.window.LPaper;
import org.loon.framework.android.game.core.graphics.window.LSelect;

import android.R.string;
import android.content.Context;
import android.content.SharedPreferences;

public class Game extends AVGScreen {
	Context context;
	SharedPreferences sp;
	boolean isLoadcg,isLoadcg1,isLoadcg2,isLoadgb,isLoadmid,isLoaddialog,isLoading;
	int cgIndex;
	int cg1Index;
	int cg2Index;
	int midIndex;
	int gbIndex;
	int diaglogIndex;
	boolean isSave;
	LFont font;
	LColor color;
	String contentPart;
	LButton choiceButton,button1,button2,button3,button4,button5,button6,button7,button8,button9;
	LPaper choice,saved;
	
	
//	LMessage message=new LMessage(cg1Index, cg1Index);
	
	public Game(Context context) {
		super("res/script/game.txt", MessageDialog.getRMXPDialog("res/system/dialog.png",
				460, 150));
		this.context=context;
		isLoadcg=true;
		isLoadcg1=true;
		isLoadcg2=true;
		isLoadgb=true;
		isLoadmid=true;
		isLoaddialog=true;
		isLoading=false;
		cgIndex=midIndex=gbIndex=diaglogIndex=cg1Index=cg2Index=0;
		font=LFont.getFont(7);
		color=new LColor(0, 0, 0);
	
	}
	public Game(Context context,int gb,int mid,int cg,int cg1,int cg2,int diaglog){
		super("res/script/game.txt", MessageDialog.getRMXPDialog("res/system/dialog.png",
				460, 150));
		this.context=context;
		if(cg==0)
			isLoadcg=true;
		else 
			isLoadcg=false;
		if(cg1==0)
			isLoadcg1=true;
		else 
			isLoadcg1=false;
		isLoadcg2=false;
		isLoadgb=false;
		isLoadmid=false;
		isLoaddialog=false;
		isLoading=true;
		gbIndex=gb;
		midIndex=mid;
		cgIndex=cg;
		cg1Index=cg1;
		cg2Index=cg2;
		diaglogIndex=diaglog;
		font=LFont.getFont(7);
		color=new LColor(0, 0, 0);
	}

	public void onLoad() {
		saved=new LPaper("res/system/saved.png", 253, 0);
		button5=new LButton("res/system/button5.png",90,20){
			public void doClick(){
				if(isSave){				
					saveGame(gbIndex,midIndex,cgIndex, cg1Index, cg2Index, diaglogIndex,contentPart, 1);
					setText(getMes(1));
				}
				else {
					loadGame(1);
				}
			}
		};
		button6=new LButton("res/system/button6.png",90,20){
			public void doClick(){
				if(isSave){				
					saveGame(gbIndex,midIndex,cgIndex, cg1Index, cg2Index, diaglogIndex,contentPart, 2);
					setText(getMes(2));
				}
				else {
					loadGame(2);
				}
			}
		};
		button7=new LButton("res/system/button7.png",90,20){
			public void doClick(){
				if(isSave){				
					saveGame(gbIndex,midIndex,cgIndex, cg1Index, cg2Index, diaglogIndex,contentPart, 3);
					setText(getMes(3));
				}
				else {
					loadGame(3);
				}
			}
			
		};
		button8=new LButton("res/system/button8.png",90,20){
			public void doClick(){
				if(isSave){				
					saveGame(gbIndex,midIndex,cgIndex, cg1Index, cg2Index, diaglogIndex,contentPart, 4);
					setText(getMes(4));
				}
				else {
					loadGame(4);
				}
			
			}
		};
		button9=new LButton("res/system/button9.png",90,20){
			public void doClick(){
				if(isSave){				
					saveGame(gbIndex,midIndex,cgIndex, cg1Index, cg2Index, diaglogIndex,contentPart, 5);
					setText(getMes(5));
				}
				else {
					loadGame(5);
				}
			}
		};
		button5.setLocation(0, 2);
		button6.setLocation(0, 24);
		button7.setLocation(0, 46);
		button8.setLocation(0, 68);
		button9.setLocation(0, 90);
		button5.setFont(font);
		button5.setFontColor(color);
		button5.setText(getMes(1));
		button6.setFont(font);
		button6.setFontColor(color);
		button6.setText(getMes(2));
		button7.setFont(font);
		button7.setFontColor(color);
	    button7.setText(getMes(3));
		button8.setFont(font);
		button8.setFontColor(color);
		button8.setText(getMes(4));
		button9.setFont(font);
		button9.setFontColor(color);
		button9.setText(getMes(5));
		saved.add(button5);
		saved.add(button6);
		saved.add(button7);
		saved.add(button8);
		saved.add(button9);
		saved.setVisible(false);
		choice=new LPaper("res/system/choice.png", 345, 0);
		button1=new LButton("res/system/button1.png",90,30){
			public void doClick(){
				isSave=true;
				saved.setVisible(!saved.isVisible());
			}
		};
		button2=new LButton("res/system/button2.png",90,30){
			public void doClick(){
				isSave=false;
				saved.setVisible(!saved.isVisible());
			}
		};
		button3=new LButton("res/system/button3.png",90,30){
			public void doClick(){
				stopAssetsMusic();
				setScreen(new TitleScreen(context));
			}
		};
		button4=new LButton("res/system/button4.png",90,30){
			public void doClick(){
				LSystem.exit();
			}
		};
		button1.setLocation(0, 2);
		button2.setLocation(0, 34);
		button3.setLocation(0, 66);
		button4.setLocation(0, 98);
		choice.add(button1);
		choice.add(button2);
		choice.add(button3);
		choice.add(button4);
		choice.setVisible(false);		
		choiceButton=new LButton("res/system/choicebutton.png",40,40){
			public void doClick(){
				setLocked(!isLocked());
				choice.setVisible(!choice.isVisible());
				if(saved.isVisible())
					saved.setVisible(false);
			}
		};
		choiceButton.setLocation(439, 0);
		add(choiceButton);
		add(choice);
		add(saved);
	}

	public void drawScreen(LGraphics g) {
	}


	public void initCommandConfig(Command command) {
		command.setVariable("sel0", "0");
		command.setVariable("sel1", "0");
		command.setVariable("sel2", "0");
		command.setVariable("sel3", "0");
		command.setVariable("sel4", "0");
		command.setVariable("a1", "0");
	}

	public void initMessageConfig(LMessage message) {

	}

	public void initSelectConfig(LSelect select) {
	}

	public boolean nextScript(String mes) {
		Command command=getCommand();
		if(isLoading){
			isLoading=false;
			return true;
		}
		if(!isLoadgb){
			command.gotoIndex(gbIndex-1);
			isLoadgb=true;
			isLoading=true;
			nextScript();
			return false;
		}
		if(!isLoadmid){
			command.gotoIndex(midIndex-1);
			isLoadmid=true;
			isLoading=true;
			nextScript();
			return false;
		}
		if(!isLoadcg){
			command.gotoIndex(cgIndex-1);
			isLoadcg=true;
			isLoading=true;
			nextScript();
			return false;
		}
		if(!isLoadcg1){
			command.gotoIndex(cg1Index-1);
			isLoadcg1=true;
			isLoading=true;
			nextScript();
			return false;
		}
		if(!isLoadcg2){
			command.gotoIndex(cg2Index-1);
			isLoadcg2=true;
			isLoading=true;
			nextScript();
			return false;
		}
		if(!isLoaddialog){
			command.gotoIndex(diaglogIndex-1);
			isLoaddialog=true;
			nextScript();
			return false;
		}
		if(mes.startsWith("gb"))
			gbIndex=getOffset(command.nowCacheOffsetName());
		if(mes.startsWith("play"))
			midIndex=getOffset(command.nowCacheOffsetName());
		if(mes.startsWith("cg"))
		{
			cgIndex=cg1Index;
			cg1Index=cg2Index;
			cg2Index=getOffset(command.nowCacheOffsetName());
		}
		if(mes.startsWith("mes"))
		{
			diaglogIndex=getOffset(command.nowCacheOffsetName());
			contentPart=mes.substring(10, 17)+"…";
		}
		return true;
	}

	public void onExit() {
		setScreen(new TitleScreen(context));
	}

	public void onSelect(String message, int type) {
		if("1、《月下独酌》的作者是_______。".equalsIgnoreCase(message)){
			command.setVariable("sel0", String.valueOf(type));
		}
		if("2、花开堪折直须折，______________。".equalsIgnoreCase(message)){
			command.setVariable("sel1", String.valueOf(type));
		}
		if("3、春风又___江南岸，明月何时照我还。".equalsIgnoreCase(message)){
			command.setVariable("sel2", String.valueOf(type));
		}
		if("4、_____________，千金散尽还复来。".equalsIgnoreCase(message)){
			command.setVariable("sel3", String.valueOf(type));
		}
		if("5、谁言寸草心，______________。".equalsIgnoreCase(message)){
			command.setVariable("sel4", String.valueOf(type));
		}
		if("he".equalsIgnoreCase(message))
		{
			command.setVariable("a1", "haha~");
		}
	}
	
	public int getOffset(String offsetName){
		return Integer.parseInt(offsetName.split("@")[1]);
	}
	public String getMes(int index){
		sp=context.getSharedPreferences(String.valueOf(index), Context.MODE_PRIVATE);		
		return sp.getString("mes", null);
	}
	public void saveGame(int gb,int mid ,int cg,int cg1,int cg2,int dialog,String mes, int savedindex){		
		sp=context.getSharedPreferences(String.valueOf(savedindex), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor=sp.edit();
		editor.putInt("gb", gb);
		editor.putInt("mid", mid);
		editor.putInt("cg", cg);
		editor.putInt("cg1", cg1);
		editor.putInt("cg2", cg2);
		editor.putInt("dialog", dialog);
		editor.putString("mes", mes);
		editor.commit();
	}
	
	public void loadGame(int savedindex){
		sp=context.getSharedPreferences(String.valueOf(savedindex), Context.MODE_PRIVATE);
		int gb=sp.getInt("gb", 0);
		if(gb==0)
			return;
		int mid=sp.getInt("mid", 0);
		int cg=sp.getInt("cg", 0);
		int cg1=sp.getInt("cg1", 0);
		int cg2=sp.getInt("cg2", 0);
		int dialog=sp.getInt("dialog", 0);
		setScreen(new Game(context,gb,mid,cg,cg1,cg2,dialog));
	}
}
