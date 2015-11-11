package com.nullcognition.storiopract00.table;
// ersin 11/11/15 Copyright (c) 2015+ All rights reserved.


import android.net.Uri;

import com.nullcognition.storiopract00.contentprovider.TweetContentProvider;
import com.pushtorefresh.storio.sqlite.queries.Query;

public class TweetTable{

	public static final String TABLE_NAME = "table_tweet";
	public static final String COLUMN_ID  = "_id";

	public static final String COLUMN_AUTHOR  = "author";
	public static final String COLUMN_CONTENT = "content";

	public static final Uri TWEET_URI =
			TweetContentProvider.BASE_CONTENT_URI.buildUpon()
			                                     .appendPath(TABLE_NAME)
			                                     .build();

	public static final Query QUERY_ALL = Query.builder().table(TABLE_NAME).build();

	public static String getCreateTableQuery(){
		return "CREATE TABLE " + TABLE_NAME + "("
				+ COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
				+ COLUMN_AUTHOR + " TEXT NOT NULL, "
				+ COLUMN_CONTENT + " TEXT NOT NULL"
				+ ");";
	}

	private TweetTable() throws IllegalAccessException{
		throw new IllegalAccessException();
	}
}
