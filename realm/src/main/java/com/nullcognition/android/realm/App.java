package com.nullcognition.android.realm;

import android.app.Application;
import io.realm.Realm;

/**
 * Created by mms on 10/21/16.
 */

public class App extends Application {

  @Override public void onCreate() {
    super.onCreate();
    Realm.init(this);
  }
}
