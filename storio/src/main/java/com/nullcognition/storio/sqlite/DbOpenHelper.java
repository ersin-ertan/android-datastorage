package com.nullcognition.storio.sqlite;
// ersin 09/11/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nullcognition.storio.sqlite.table.ModelTable;

public class DbOpenHelper extends SQLiteOpenHelper{

	public static final String DATABASE = "database";
	public static final int VERSION = 1;

	public DbOpenHelper(final Context context){
		super(context, DATABASE, null, VERSION);
	}

	@Override public void onCreate(final SQLiteDatabase db){
		db.execSQL(ModelTable.getCreateTableQuery());
	}

	@Override public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion){}
}
