package com.nullcognition.requery.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;
import io.requery.CascadeAction;
import io.requery.Entity;
import io.requery.Generated;
import io.requery.JunctionTable;
import io.requery.Key;
import io.requery.ManyToMany;
import io.requery.Persistable;
import java.util.List;

/**
 * Created by mms on 11/6/16.
 */

@Entity public interface Group extends Observable, Parcelable, Persistable {

  @Key @Generated int getId();

  @Bindable String getName();

  @JunctionTable
  @ManyToMany(mappedBy = "groups", cascade = { CascadeAction.DELETE, CascadeAction.SAVE })
  List<Person> getMembers();
}