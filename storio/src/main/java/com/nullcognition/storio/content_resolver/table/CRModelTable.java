package com.nullcognition.storio.content_resolver.table;
// ersin 10/11/15 Copyright (c) 2015+ All rights reserved.


import com.pushtorefresh.storio.sqlite.queries.Query;

public class CRModelTable{

	public static final String TABLE     = "crmodel";
	public static final String COLUMN_ID = "_id";

	public static final String COLUMN_CONTENT = "content";

	public static final Query QUERY_ALL = Query.builder().table(TABLE).build();

	public static String getCreateTableQuery(){
		return "CREATE TABLE " + TABLE + "("
				+ COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
				+ COLUMN_CONTENT + " TEXT NOT NULL"
				+ ");";
	}

	private CRModelTable() throws IllegalAccessException{ throw new IllegalAccessException("meta-data class");}
}
