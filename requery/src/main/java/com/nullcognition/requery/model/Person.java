package com.nullcognition.requery.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;
import io.requery.CascadeAction;
import io.requery.Column;
import io.requery.Entity;
import io.requery.ForeignKey;
import io.requery.Generated;
import io.requery.Index;
import io.requery.Key;
import io.requery.ManyToMany;
import io.requery.OneToMany;
import io.requery.OneToOne;
import io.requery.Persistable;
import io.requery.query.MutableResult;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by mms on 11/6/16.
 */
@Entity public interface Person extends Observable, Parcelable, Persistable {

  @Key @Generated int getId();

  @Bindable String getName();

  @Bindable @Index(value = "email_index") String getEmail();

  @Bindable Date getBirthday();

  @Bindable int getAge();

  @Bindable @ForeignKey @OneToOne Address getAddress();

  @OneToMany(mappedBy = "owner", cascade = { CascadeAction.DELETE, CascadeAction.SAVE })
  MutableResult<Phone> getPhoneNumbers();

  @Bindable @Column(unique = true) UUID getUUID();

  @OneToMany(mappedBy = "owner", cascade = { CascadeAction.DELETE, CascadeAction.SAVE })
  List<Phone> getPhoneNumberList();

  @ManyToMany(mappedBy = "members", cascade = { CascadeAction.DELETE, CascadeAction.SAVE })
  List<Group> getGroups();
}