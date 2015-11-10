package com.nullcognition.storio.sqlite;
// ersin 09/11/15 Copyright (c) 2015+ All rights reserved.


import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.nullcognition.storio.sqlite.entity.Model;
import com.nullcognition.storio.sqlite.entity.ModelStorIOSQLiteDeleteResolver;
import com.nullcognition.storio.sqlite.entity.ModelStorIOSQLiteGetResolver;
import com.nullcognition.storio.sqlite.entity.ModelStorIOSQLitePutResolver;
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;

public class DefaultStorio{


	public static StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper){
		return DefaultStorIOSQLite.builder()
		                          .sqliteOpenHelper(sqLiteOpenHelper)
		                          .addTypeMapping(Model.class, SQLiteTypeMapping.<Model>builder()
		                                                                        .putResolver(new ModelStorIOSQLitePutResolver())
		                                                                        .getResolver(new ModelStorIOSQLiteGetResolver())
		                                                                        .deleteResolver(new ModelStorIOSQLiteDeleteResolver())
		                                                                        .build())
		                          .build();
	}
}
