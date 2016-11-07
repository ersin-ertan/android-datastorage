package com.nullcognition.android.realm;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Required;

/**
 * Created by mms on 10/21/16.
 */

public class TestObj extends RealmObject {

  // supported field types : boolean, byte, short, int, long, float, double, String, Date
  // subclasses of RealmObject and RealmList<? extends RealmObject> are supported to model relationships.
  // boxed types: Boolean, Byte, Short, Integer, Long, Float and Double can also be set to null

  public int h;
  // Boolean, Byte, Short, Integer, Long, Float, Double, String, byte[] and Date
  protected int a;
  @Required private String n; // @Required will enforce non null values for fields of
  @Ignore private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getH() {
    return h;
  }

  public void setH(int h) {
    this.h = h;
  }

  public int getA() {
    return a;
  }

  public void setA(int a) {
    this.a = a;
  }

  public String getN() {
    return n;
  }

  public void setN(String n) {
    this.n = n;
  }
}
