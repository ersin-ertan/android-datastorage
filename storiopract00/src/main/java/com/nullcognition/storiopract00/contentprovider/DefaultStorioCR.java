package com.nullcognition.storiopract00.contentprovider;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.nullcognition.storiopract00.model.Tweet;
import com.nullcognition.storiopract00.table.TweetTable;
import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio.contentresolver.operations.get.DefaultGetResolver;
import com.pushtorefresh.storio.contentresolver.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.contentresolver.queries.DeleteQuery;
import com.pushtorefresh.storio.contentresolver.queries.InsertQuery;
import com.pushtorefresh.storio.contentresolver.queries.UpdateQuery;

public class DefaultStorioCR{

	@NonNull
	public static final Uri CONTENT_URI = Uri.parse("content://" + TweetContentProvider.AUTHORITY + "/" + TweetTable.TABLE_NAME);

	public static StorIOContentResolver provideStorIOContentResolver(@NonNull ContentResolver contentResolver){
		return DefaultStorIOContentResolver.builder()
		                                   .contentResolver(contentResolver)
		                                   .addTypeMapping(Tweet.class, ContentResolverTypeMapping.<Tweet>builder()
		                                                                                          .putResolver(putResolver)
		                                                                                          .getResolver(getResolver)
		                                                                                          .deleteResolver(deleteResolver)
		                                                                                          .build())
		                                   .build();
	}

	public static DefaultPutResolver<Tweet> putResolver = new DefaultPutResolver<Tweet>(){
		@NonNull @Override protected InsertQuery mapToInsertQuery(@NonNull final Tweet tweet){
			return InsertQuery.builder()
					.uri(CONTENT_URI) // "content://some_uri"
					.build();
		}

		// this may be the problem, that the map to update query map not be mapped to update all values

		@NonNull @Override protected UpdateQuery mapToUpdateQuery(@NonNull final Tweet tweet){
			return UpdateQuery.builder()
					.uri(CONTENT_URI) // "content://some_uri"
					.where(TweetTable.COLUMN_ID + " = ?") // "some_column = ?"
					.whereArgs(tweet.getId())
					.build();
		}

		@NonNull @Override public ContentValues mapToContentValues(@NonNull final Tweet tweet){
			final ContentValues contentValues = new ContentValues();

			contentValues.put(TweetTable.COLUMN_ID, tweet.getId());
			contentValues.put(TweetTable.COLUMN_AUTHOR, tweet.getAuthor());
			contentValues.put(TweetTable.COLUMN_CONTENT, tweet.getContent());

			return contentValues;
		}
	};


	public static DefaultGetResolver<Tweet> getResolver = new DefaultGetResolver<Tweet>(){
		@NonNull @Override public Tweet mapFromCursor(@NonNull final Cursor cursor){

			return Tweet.newTweet(
					cursor.getLong(cursor.getColumnIndexOrThrow(TweetTable.COLUMN_ID)),
					cursor.getString(cursor.getColumnIndexOrThrow(TweetTable.COLUMN_AUTHOR)),
					cursor.getString(cursor.getColumnIndexOrThrow(TweetTable.COLUMN_CONTENT))
			);
		}
	};

	public static DefaultDeleteResolver<Tweet> deleteResolver = new DefaultDeleteResolver<Tweet>(){
		@NonNull @Override protected DeleteQuery mapToDeleteQuery(@NonNull final Tweet tweet){
			return DeleteQuery.builder()
			                  .uri(CONTENT_URI)
			                  .where(TweetTable.COLUMN_ID + " = ?")
			                  .whereArgs(tweet.getId())
			                  .build();
		}
	};


}
