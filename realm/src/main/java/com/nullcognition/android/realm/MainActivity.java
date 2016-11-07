package com.nullcognition.android.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

  private Realm realm;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    realm = Realm.getDefaultInstance();

    autoUpdate();
  }

  private void autoUpdate() {
    // RealmObjects are live, auto-updating views to data, no need to refresh, modifying objects are reflected immediately
    realm.executeTransaction(new Realm.Transaction() {
      @Override public void execute(Realm realm) {
        TestObj testObj = realm.createObject(TestObj.class);
        testObj.setId(0);
        testObj.setA(1);
        testObj.setH(2);
        testObj.setN("n");
      }
    });

    TestObj testObj = realm.where(TestObj.class).findFirst();

    Toast.makeText(this, "a" + " " + String.valueOf(testObj.getA()) +
        " h" + " " + String.valueOf(testObj.getH()) +
        " id" + " " + String.valueOf(testObj.getId()) +
        " n" + " " + testObj.getN(), Toast.LENGTH_SHORT).show();

    realm.executeTransaction(new Realm.Transaction() {
      @Override public void execute(Realm realm) {
        TestObj testObj = realm.where(TestObj.class).findFirst();
        testObj.setN("changed");
      }
    });

    Toast.makeText(this, "a" + " " + String.valueOf(testObj.getA()) +
        " h" + " " + String.valueOf(testObj.getH()) +
        " id" + " " + String.valueOf(testObj.getId()) +
        " n" + " " + testObj.getN(), Toast.LENGTH_SHORT).show();
  }

  @Override protected void onDestroy() {
    realm.close();
    super.onDestroy();
  }
}
