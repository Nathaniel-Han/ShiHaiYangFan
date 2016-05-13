package com.shihai;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MySQLiteHelper extends SQLiteOpenHelper{

	public MySQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		}
	@Override
	public void onCreate(SQLiteDatabase db){
//		db.execSQL("create table if not exists poetry("	
//				 + "id integer primary key,"
//				 + "title nvarchar,"
//				 + "author nvarchar,"
//				 + "content ntext,"
//				 + "comment ntext,"
//				 + "analysis ntext)");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	
	
}