package com.nullcognition.requery;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import com.nullcognition.requery.model.Models;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.rx.RxSupport;
import io.requery.rx.SingleEntityStore;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;

/**
 * Created by mms on 11/6/16.
 *
 * https://github.com/requery/requery/wiki/Android
 */

public class PeopleApplication extends Application {

  final PeopleApplication peopleApplication;
  private SingleEntityStore<Persistable> dataStore;

  PeopleApplication() {
    peopleApplication = this;
  }

  public static PeopleApplication get(Context context) {
    return (PeopleApplication) context.getApplicationContext();
  }

  @Override public void onCreate() {
    super.onCreate();
    StrictMode.enableDefaults();
  }

  SingleEntityStore<Persistable> getData() {
    if (dataStore == null) {
      DatabaseSource source = new DatabaseSource(this, Models.DEFAULT, 1);
      if (BuildConfig.DEBUG) {
        source.setTableCreationMode(TableCreationMode.DROP_CREATE);
      }
      io.requery.sql.Configuration config = source.getConfiguration();
      dataStore = RxSupport.toReactiveStore(new EntityDataStore<Persistable>(config));
    }
    return dataStore;
  }
}
