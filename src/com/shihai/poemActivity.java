package com.shihai;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponseCallback;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class poemActivity extends Activity {

	private EditText et;
	private Button but1; 
	private Button but2;
	private ProgressBar mProgressBar;
	
	
	private  MobileServiceClient mClient;
	private MobileServiceTable<Poem> mPoemTable;
	
	private String rootDirectory = "/data/data/com.shihai/databases";
	
   private final String DATABASE_FILENAME = "poem.db";  
	
	private ArrayList<HashMap<String,Object>> mData;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
	               WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        requestWindowFeature(Window.FEATURE_NO_TITLE); 
	        setContentView(R.layout.firstlayout);
	        et = (EditText)findViewById(R.id.et);
	        but1 = (Button)findViewById(R.id.but1);
	        but2 = (Button)findViewById(R.id.but2);
	        mProgressBar = (ProgressBar)findViewById(R.id.progressBar1);
	        mProgressBar.setVisibility(ProgressBar.GONE);
	        final LayoutInflater inflater = LayoutInflater.from(this);
	        final LinearLayout lin = (LinearLayout) findViewById(R.id.LinearLayout01);
	        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.listview,null).findViewById(R.id.layout);
	       
	        
			
	        try{
	        	mClient = new MobileServiceClient(
						"https://hyxandroid.azure-mobile.net/",
						"tkPaxSKkZazDKFndVIlDjCVfpZykcr78",this).withFilter(new ProgressFilter());
	        	
	        	mPoemTable = mClient.getTable(Poem.class);
	        	
	        }catch (MalformedURLException e) {
	        	createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
	        }
	        
	        
	        mData = getData();
	        
	        ListView list = (ListView)layout.getChildAt(0);
	        SimpleAdapter listItemAdapter = new SimpleAdapter(this,mData,R.layout.vlist,  
	                new String[] {"title", "author"},  new int[] {R.id.title,R.id.author} );
	        list.setAdapter(listItemAdapter);
	                                 
	        list.setOnItemClickListener(new OnItemClickListener(){
	    	@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int click,
						long arg3) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				String seeking = (String) mData.get(click).get("title");
	    		bundle.putString("title", seeking);
	    		intent.putExtras(bundle);
	    		intent.setClass(poemActivity.this,secondActivity.class);
	    		startActivity(intent);
	    		poemActivity.this.finish();
	    		}
	    	});
	        lin.removeAllViews();
	        lin.addView(layout);
	        final AlertDialog.Builder my_ADialog = new AlertDialog.Builder(this);
	        but1.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View v) {
					String seeking = et.getText().toString();
					boolean bool = false;
					int  size = mData.size();
					for(int i = 0; i < size; i++)
					{
						if(mData.get(i).get("title").equals(seeking))
						{
							bool = true;
							break;
						}
					}
					if(bool)
					{
					Intent intent = new Intent();
					Bundle bundle = new Bundle();
					bundle.putString("title", seeking);
					intent.putExtras(bundle);
					intent.setClass(poemActivity.this,secondActivity.class);
					startActivity(intent);
		    		poemActivity.this.finish();
					}else {
						my_ADialog.setTitle("对不起!");
						my_ADialog.setMessage("没有该诗，请重新输入");
						my_ADialog.show();
					}
				}
	        	
	        });
	        but2.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View arg0) {
					poemActivity.this.finish();
					}
	        	});
	        }
	 
	 private ArrayList<HashMap<String,Object>>  getData(){
		 final ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
//		 HashMap<String,Object>  map ;
//		 //myHelper = new MySQLiteHelper(this,"poem.db",null,1);
//		 SQLiteDatabase db = openDatabase();//myHelper.getReadableDatabase();
//		 Cursor cursor = db.query("poetry", null, null, null, null, null, "id asc");
//		 int titleIndex = cursor.getColumnIndex("title");
//	     int authorIndex = cursor.getColumnIndex("author");
//	     for(cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()){
//	    	map = new HashMap<String,Object>();
//	     	map.put("title", cursor.getString(titleIndex));
//	     	map.put("author",cursor.getString(authorIndex));
//	     	list.add(map);
//	        
//	     }
//	     cursor.close();
//	     db.close();
		 
		 mPoemTable.where().field("id").ne("").execute(new TableQueryCallback<Poem>() {
			
			@Override
			public void onCompleted(List<Poem> result, int count, Exception exception,
					ServiceFilterResponse response) {
				// TODO Auto-generated method stub
				for(Poem item:result){
//					 HashMap<String,Object>  map = new HashMap<String,Object>();
//					 map.put("title",item.getTitle());
//					 map.put("author", item.getAuther());
//					 list.add(map);
				}
				
			}
		});
		 
		 HashMap<String,Object>  map ;
		 //myHelper = new MySQLiteHelper(this,"poem.db",null,1);
		 SQLiteDatabase db = openDatabase();//myHelper.getReadableDatabase();
		 Cursor cursor = db.query("poetry", null, null, null, null, null, "id asc");
		 int titleIndex = cursor.getColumnIndex("title");
	     int authorIndex = cursor.getColumnIndex("author");
	     for(cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()){
	    	map = new HashMap<String,Object>();
	     	map.put("title", cursor.getString(titleIndex));
	     	map.put("author",cursor.getString(authorIndex));
	     	list.add(map);
	        
	     }
	     cursor.close();
	     db.close();
		return list;
	    
	 }
	 
	 private SQLiteDatabase openDatabase()  
	 {  
	     try  
	     {  
	         // 获得dictionary.db文件的绝对路径  
	         String databaseFilename = rootDirectory + "/" + DATABASE_FILENAME;  
	         File dir = new File(rootDirectory);  
	         // 如果/sdcard/dictionary目录中存在，创建这个目录  
	         if (!dir.exists())  
	         {
	        	 dir.mkdir();  
	         // 如果在/sdcard/dictionary目录中不存在  
	         // dictionary.db文件，则从res\raw目录中复制这个文件到  
	         // SD卡的目录（/sdcard/dictionary）  
	         if (!(new File(databaseFilename)).exists())  
	         {  
	             // 获得封装dictionary.db文件的InputStream对象  
	             InputStream is = getResources().openRawResource(R.raw.poem);  
	             FileOutputStream fos = new FileOutputStream(databaseFilename);  
	             byte[] buffer = new byte[8192];  
	             int count = 0;  
	             // 开始复制dictionary.db文件  
	             while ((count = is.read(buffer)) > 0)  
	             {  
	                 fos.write(buffer, 0, count);  
	             }  
	   
	             fos.close();  
	             is.close();  
	         }  
	         }
	         // 打开/sdcard/dictionary目录中的dictionary.db文件  
	         SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(  
	                 databaseFilename, null);  
	         return database;  
	     }  
	     catch (Exception e)  
	     {  
	     }  
	     return null;  
	 }  
	 
		private void createAndShowDialog(Exception exception, String title) {
			Throwable ex = exception;
			if(exception.getCause() != null){
				ex = exception.getCause();
			}
			createAndShowDialog(ex.getMessage(), title);
		}
	    
		private void createAndShowDialog(String message, String title) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setMessage(message);
			builder.setTitle(title);
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			builder.create().show();
		}
		
		
		
		
		private class ProgressFilter implements ServiceFilter {
			
			@Override
			public void handleRequest(ServiceFilterRequest request, NextServiceFilterCallback nextServiceFilterCallback,
					final ServiceFilterResponseCallback responseCallback) {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.VISIBLE);
					}
				});
				
				nextServiceFilterCallback.onNext(request, new ServiceFilterResponseCallback() {
					
					@Override
					public void onResponse(ServiceFilterResponse response, Exception exception) {
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.GONE);
							}
						});
						
						if (responseCallback != null)  responseCallback.onResponse(response, exception);
					}
				});
			}
		}
}
	    
