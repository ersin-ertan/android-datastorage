package com.nullcognition.storiopract00.contentprovider;
// ersin 11/11/15 Copyright (c) 2015+ All rights reserved.


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.nullcognition.storiopract00.database.SqliteOpenHelper;
import com.nullcognition.storiopract00.table.TweetTable;

// content://com.nullcognition.storiopract00/tweet_table
// for table tweet_table, the provider returns the MIME type
// vnd.android.cursor.dir/vnd.nullgonition.storiopract00.tweet_table
// note the .dir
//
// content://com.nullcognition.storiopract00/tweet_table/someRowNumber
// for row someRowNumber in table tweet_table, the provider returns the MIME type
// vnd.android.cursor.item/vnd.nullcognition.storiopract00.tweet_table
// note the .item


public class TweetContentProvider extends ContentProvider{

	public static final String AUTHORITY        = "com.nullcognition.storiopract00";
	public static final Uri    BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	// content://com.nullcognition.storiopract00

	// for tweets path look in tweet table
	public static final int TABLE_TWEET_MATCHER_ID = 1;

	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

	static{ uriMatcher.addURI(AUTHORITY, TweetTable.TWEET_URI.getPath(), TABLE_TWEET_MATCHER_ID);}

	private SqliteOpenHelper sqliteOpenHelper;

	@Override public boolean onCreate(){
		sqliteOpenHelper = new SqliteOpenHelper(getContext());
		return true;
	}

	@Nullable @Override public Cursor query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs, final String sortOrder){
		switch(uriMatcher.match(uri)){
			case TABLE_TWEET_MATCHER_ID:
				return sqliteOpenHelper
						.getReadableDatabase().query(
								TweetTable.TABLE_NAME,
								projection,
								selection,
								selectionArgs,
								null,
								null,
								sortOrder
						);
			default:
				return null;
		}
	}

	@Nullable @Override public Uri insert(final Uri uri, final ContentValues values){

		final long insertedId;

		switch(uriMatcher.match(uri)){
			case TABLE_TWEET_MATCHER_ID:
				insertedId = sqliteOpenHelper
						.getWritableDatabase().insert(
								TweetTable.TABLE_NAME,
								null,
								values
						);
				break;
			default:
				return null;
		}

		if(insertedId != -1){
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return ContentUris.withAppendedId(uri, insertedId);
	}

	@Override public int delete(final Uri uri, final String selection, final String[] selectionArgs){

		final int numberOfRowsDeleted;

		switch(uriMatcher.match(uri)){
			case TABLE_TWEET_MATCHER_ID:
				numberOfRowsDeleted = sqliteOpenHelper
						.getWritableDatabase().delete(
								TweetTable.TABLE_NAME,
								selection,
								selectionArgs
						);
				break;
			default:
				return 0;
		}
		if(numberOfRowsDeleted > 0){
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return numberOfRowsDeleted;
	}

	@Override public int update(final Uri uri, final ContentValues values, final String selection, final String[] selectionArgs){

		final int numberOfRowsAffected;

		switch(uriMatcher.match(uri)){
			case TABLE_TWEET_MATCHER_ID:
				numberOfRowsAffected = sqliteOpenHelper
						.getReadableDatabase().update(
								TweetTable.TABLE_NAME,
								values,
								selection,
								selectionArgs
						);
				break;
			default:
				return 0;
		}
		if(numberOfRowsAffected > 0){
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return numberOfRowsAffected;
	}

	@Nullable @Override public String getType(final Uri uri){
		// if your ContentProvider has been exported, or your own) to retrieve
		// the MIME type of the given content URL
		return null;
	}
}
