package com.nullcognition.akatsuki.v0_0_3;
// ersin 28/08/15 Copyright (c) 2015+ All rights reserved.


import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

@ParcelablePlease
public class BeanImplementsParcelable implements Parcelable{

// default is required here, else on rotation the value will be displayed as null(this overwrites what
// is used as the default in the layout file
	String myString = "bip.myString default";
	int    myInt = 2;

	@Override public int describeContents(){ return 0; }

	@Override public void writeToParcel(Parcel dest, int flags){BeanImplementsParcelableParcelablePlease.writeToParcel(this, dest, flags);}

	public static final Creator<BeanImplementsParcelable> CREATOR = new Creator<BeanImplementsParcelable>(){
		public BeanImplementsParcelable createFromParcel(Parcel source){
			BeanImplementsParcelable target = new BeanImplementsParcelable();
			BeanImplementsParcelableParcelablePlease.readFromParcel(target, source);
			return target;
		}

		public BeanImplementsParcelable[] newArray(int size){return new BeanImplementsParcelable[size];}
	};
}
