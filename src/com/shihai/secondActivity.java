package com.shihai;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Button;

public class secondActivity extends Activity{
	MySQLiteHelper myHelper;
	TextView tv1;
	TextView tv2;
	Button bt2;
	String[] result = new String[2];
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.secondlayout);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        bt2 = (Button)findViewById(R.id.bt2);
        myHelper =new MySQLiteHelper(this,"poem.db",null,1);
        Bundle bunde = this.getIntent().getExtras();
        String title = bunde.getString("title");
        String[] result = queryData(myHelper,title);
        tv1.setText(result[0]);
        tv2.setText(result[1]);
        bt2.setOnClickListener(new Button.OnClickListener(){
            @Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(secondActivity.this, poemActivity.class);
				startActivity(intent);
				secondActivity.this.finish();
			}
		});
	}
        public String[] queryData(MySQLiteHelper myHelper2,String title){
	    	String[] result = new String[2];
	    	SQLiteDatabase db = myHelper.getReadableDatabase();
	    	Cursor cursor = db.query("poetry", null, null, null, null, null, "id asc");
	        int titleIndex = cursor.getColumnIndex("title");
	        int authorIndex = cursor.getColumnIndex("author");
	        int contentIndex = cursor.getColumnIndex("content");
	        int commentIndex = cursor.getColumnIndex("comment");
	        int analysisIndex = cursor.getColumnIndex("analysis");
	        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
	        	if(cursor.getString(titleIndex).equals(title)){
	        		result[0] = cursor.getString(titleIndex).trim()+"\n" + cursor.getString(authorIndex).trim()+"\n"
      	                + cursor.getString(contentIndex).trim();
	        		result[1] = "注解:\n" + cursor.getString(commentIndex).trim()+"\n"
      	                             + "赏析:\n" + cursor.getString(analysisIndex).trim()+"\n";
      	            break;
	        	}
	        }
	        cursor.close();
	        db.close();
	        return result;
	    }
}