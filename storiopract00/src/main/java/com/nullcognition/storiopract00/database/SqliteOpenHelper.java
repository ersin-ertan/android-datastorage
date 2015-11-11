package com.nullcognition.storiopract00.database;
// ersin 11/11/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nullcognition.storiopract00.table.TweetTable;

public class SqliteOpenHelper extends SQLiteOpenHelper{

	public static final String DATABASE_NAME    = "database_tweet";
	public static final int    DATABASE_VERSION = 1;

	public SqliteOpenHelper(final Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override public void onCreate(final SQLiteDatabase db){
		db.execSQL(TweetTable.getCreateTableQuery());
	}

	@Override public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion){ }
}
