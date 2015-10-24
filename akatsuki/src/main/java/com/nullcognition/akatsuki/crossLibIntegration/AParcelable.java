package com.nullcognition.akatsuki.crossLibIntegration;
// ersin 01/09/15 Copyright (c) 2015+ All rights reserved.

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

@ParcelablePlease
public class AParcelable implements Parcelable{

	String retainableString;

	@Override public int describeContents(){ return 0; }
	@Override public void writeToParcel(Parcel dest, int flags){AParcelableParcelablePlease.writeToParcel(this, dest, flags);}
	public static final Creator<AParcelable> CREATOR = new Creator<AParcelable>(){
		public AParcelable createFromParcel(Parcel source){
			AParcelable target = new AParcelable();
			AParcelableParcelablePlease.readFromParcel(target, source);
			return target;
		}
		public AParcelable[] newArray(int size){return new AParcelable[size];}
	};
}
