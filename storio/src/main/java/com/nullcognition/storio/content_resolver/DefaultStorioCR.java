package com.nullcognition.storio.content_resolver;
// ersin 10/11/15 Copyright (c) 2015+ All rights reserved.


import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.nullcognition.storio.content_resolver.entity.CRModel;
import com.nullcognition.storio.content_resolver.provider.StorioContentProvider;
import com.nullcognition.storio.content_resolver.table.CRModelTable;
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
	public static final Uri CONTENT_URI = Uri.parse("content://" + StorioContentProvider.AUTHORITY + "/" + CRModelTable.TABLE);

	public static StorIOContentResolver provideStorIOContentResolver(@NonNull ContentResolver contentResolver){
		return DefaultStorIOContentResolver.builder()
		                                   .contentResolver(contentResolver)
		                                   .addTypeMapping(CRModel.class, ContentResolverTypeMapping.<CRModel>builder()
		                                                                                            .putResolver(putResolver)
		                                                                                            .getResolver(getResolver)
		                                                                                            .deleteResolver(deleteResolver)
		                                                                                            .build())
		                                   .build();
	}

	public static DefaultPutResolver<CRModel> putResolver = new DefaultPutResolver<CRModel>(){
		@NonNull @Override protected InsertQuery mapToInsertQuery(@NonNull final CRModel crModel){
			return InsertQuery.builder()
					.uri(CONTENT_URI) // "content://some_uri"
					.build();
		}

		@NonNull @Override protected UpdateQuery mapToUpdateQuery(@NonNull final CRModel crModel){
			return UpdateQuery.builder()
					.uri(CONTENT_URI) // "content://some_uri"
					.where(CRModelTable.COLUMN_ID + " = ?") // "some_column = ?"
					.whereArgs(crModel.id())
					.build();
		}

		@NonNull @Override public ContentValues mapToContentValues(@NonNull final CRModel crModel){
			final ContentValues contentValues = new ContentValues();

			contentValues.put(CRModelTable.COLUMN_ID, crModel.id());
			contentValues.put(CRModelTable.COLUMN_CONTENT, crModel.content());

			return contentValues;
		}
	};


	public static DefaultGetResolver<CRModel> getResolver = new DefaultGetResolver<CRModel>(){
		@NonNull @Override public CRModel mapFromCursor(@NonNull final Cursor cursor){

			return CRModel.newModel(
					cursor.getLong(cursor.getColumnIndexOrThrow(CRModelTable.COLUMN_ID)),
					cursor.getString(cursor.getColumnIndexOrThrow(CRModelTable.COLUMN_CONTENT))
			);
		}
	};

	public static DefaultDeleteResolver<CRModel> deleteResolver = new DefaultDeleteResolver<CRModel>(){
		@NonNull @Override protected DeleteQuery mapToDeleteQuery(@NonNull final CRModel crModel){
			return DeleteQuery.builder()
			                  .uri(CONTENT_URI)
			                  .where(CRModelTable.COLUMN_ID + " = ?")
			                  .whereArgs(crModel.id())
			                  .build();
		}
	};
}

