package com.nullcognition.storio.sqlite.table;
// ersin 09/11/15 Copyright (c) 2015+ All rights reserved.


import com.pushtorefresh.storio.sqlite.queries.Query;

public class ModelTable{

	public static final String TABLE     = "model";
	public static final String COLUMN_ID = "_id";

	public static final String COLUMN_AUTHOR  = "author";
	public static final String COLUMN_CONTENT = "content";

	public static final Query QUERY_ALL = Query.builder().table(TABLE).build();

	public static String getCreateTableQuery(){
		return "CREATE TABLE " + TABLE + "("
				+ COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
				+ COLUMN_AUTHOR + " TEXT NOT NULL, "
				+ COLUMN_CONTENT + " TEXT NOT NULL"
				+ ");";
	}

	private ModelTable() throws IllegalAccessException{ throw new IllegalAccessException("meta-data class");}
}
