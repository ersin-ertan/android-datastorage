package com.nullcognition.akatsuki.v0_0_3;
// ersin 28/08/15 Copyright (c) 2015+ All rights reserved.


import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;
import com.sora.util.akatsuki.Retained;

@ParcelablePlease
public class BeanInheritedParcelable extends BIPRoot implements Parcelable{

	String myString = "binp.myString";

	@Override public int describeContents(){ return 0; }
	@Override public void writeToParcel(Parcel dest, int flags){BeanInheritedParcelableParcelablePlease.writeToParcel(this, dest, flags);}
	public static final Creator<BeanInheritedParcelable> CREATOR = new Creator<BeanInheritedParcelable>(){
		public BeanInheritedParcelable createFromParcel(Parcel source){
			BeanInheritedParcelable target = new BeanInheritedParcelable();
			BeanInheritedParcelableParcelablePlease.readFromParcel(target, source);
			return target;
		}
		public BeanInheritedParcelable[] newArray(int size){return new BeanInheritedParcelable[size];}
	};
}


class BIPRoot{

	@Retained String myStringRoot = "binpRoot.myString";
}
