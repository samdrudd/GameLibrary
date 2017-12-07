package com.samrudd.gamelibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 2;
	private static final String TABLE_NAME_GAMES = "games";
	private static final String TABLE_CREATE_GAMES = 
			"CREATE TABLE IF NOT EXISTS games (id INTEGER PRIMARY KEY AUTOINCREMENT, title varchar(255), system_id int);";

	public DatabaseHelper(Context context) 
	{
		super(context, TABLE_NAME_GAMES, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	public void onCreate(SQLiteDatabase db)
	{		
		db.execSQL(TABLE_CREATE_GAMES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS games");
		onCreate(db);
		
	}
	
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS games");
		onCreate(db);
	}

}