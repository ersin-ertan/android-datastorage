package com.nullcognition.akatsuki.v0_0_2;
// ersin 25/08/15 Copyright (c) 2015+ All rights reserved.

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

@ParcelablePlease
public class CustomTypeParcelable implements Parcelable{

	String CTPString;

	@Override public int describeContents(){ return 0; }

	@Override public void writeToParcel(Parcel dest, int flags){CustomTypeParcelableParcelablePlease.writeToParcel(this, dest, flags);}

	public static final Creator<CustomTypeParcelable> CREATOR = new Creator<CustomTypeParcelable>(){
		public CustomTypeParcelable createFromParcel(Parcel source){
			CustomTypeParcelable target = new CustomTypeParcelable();
			CustomTypeParcelableParcelablePlease.readFromParcel(target, source);
			return target;
		}

		public CustomTypeParcelable[] newArray(int size){return new CustomTypeParcelable[size];}
	};
}
