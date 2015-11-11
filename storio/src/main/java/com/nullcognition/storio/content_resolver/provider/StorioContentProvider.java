package com.nullcognition.storio.content_resolver.provider;
// ersin 10/11/15 Copyright (c) 2015+ All rights reserved.


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.nullcognition.storio.content_resolver.table.CRModelTable;
import com.nullcognition.storio.sqlite.DbOpenHelper;

public class StorioContentProvider extends ContentProvider{

	public static final String AUTHORITY        = "com.nullcognition.storio";
	public static final Uri    BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	public static final Uri    CONTENT_URI      = BASE_CONTENT_URI.buildUpon().appendPath(CRModelTable.TABLE).build();

	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + CRModelTable.TABLE;

	public static final int IfMoreThanOneTableAssignNumberTableHere = 1;

	final static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	DbOpenHelper dbOpenHelper;

	static{
		matcher.addURI(AUTHORITY, CONTENT_URI.getPath(), IfMoreThanOneTableAssignNumberTableHere);
		// which should be .addURI("contacts", "/people", PEOPLE); where people is = 1
	}

	// matcher allows you to take a url and int match = matcher.match(url)
	// switch(match) case PEOPLE: return "vnd.android.cursor.dir/person";


	@Override public boolean onCreate(){

		dbOpenHelper = new DbOpenHelper(getContext());
		return true;
	}

	@Nullable @Override public Cursor query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs, final String sortOrder){
		switch(matcher.match(uri)){
			case IfMoreThanOneTableAssignNumberTableHere:
				return dbOpenHelper
						.getReadableDatabase()
						.query(
								CRModelTable.TABLE,
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

	@Nullable @Override public String getType(final Uri uri){
		return null;
	}

	@Nullable @Override public Uri insert(final Uri uri, final ContentValues values){

		final long insertedId;

		switch(matcher.match(uri)){
			case IfMoreThanOneTableAssignNumberTableHere:
				insertedId = dbOpenHelper
						.getWritableDatabase()
						.insert(
								CRModelTable.TABLE,
								null,
								values
						);
				break;

			default:
				return null;
		}

		if(insertedId != -1){
			getContext().getContentResolver().notifyChange(uri, null); // new data is in the database, notify the content resolver to reload everything
		}

		return ContentUris.withAppendedId(uri, insertedId);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
		final int numberOfRowsAffected;

		switch(matcher.match(uri)){
			case IfMoreThanOneTableAssignNumberTableHere:
				numberOfRowsAffected = dbOpenHelper
						.getWritableDatabase()
						.update(
								CRModelTable.TABLE,
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

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs){
		final int numberOfRowsDeleted;

		switch(matcher.match(uri)){
			case IfMoreThanOneTableAssignNumberTableHere:
				numberOfRowsDeleted = dbOpenHelper
						.getWritableDatabase()
						.delete(
								CRModelTable.TABLE,
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

}
