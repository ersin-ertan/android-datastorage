package com.nullcognition.requery.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;
import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.OneToOne;
import io.requery.Persistable;

/**
 * Created by mms on 11/6/16.
 */

@Entity public interface Address extends Observable, Parcelable, Persistable {

  @Key @Generated int getId();

  @Bindable String getLine1();

  void setLine1(String line1);

  @Bindable String getLine2();

  void setLine2(String line2);

  @Bindable String getZip();

  void setZip(String zip);

  @Bindable String getCountry();

  void setCountry(String country);

  @Bindable String getCity();

  void setCity(String city);

  @Bindable String getState();

  void setState(String state);

  @Bindable @OneToOne(mappedBy = "address") Person getPerson();
}

