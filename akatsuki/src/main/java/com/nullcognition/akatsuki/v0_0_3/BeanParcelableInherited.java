package com.nullcognition.akatsuki.v0_0_3;
// ersin 28/08/15 Copyright (c) 2015+ All rights reserved.


import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;
import com.sora.util.akatsuki.Retained;

public class BeanParcelableInherited extends BPIRoot{

	@Retained String myString = "Bpin.myString default";
}


@ParcelablePlease
class BPIRoot implements Parcelable{

	String myStringRoot = "BpinRoot.myString default";

	@Override public int describeContents(){ return 0; }
	@Override public void writeToParcel(Parcel dest, int flags){BPIRootParcelablePlease.writeToParcel(this, dest, flags);}
	public static final Creator<BPIRoot> CREATOR = new Creator<BPIRoot>(){
		public BPIRoot createFromParcel(Parcel source){
			BPIRoot target = new BPIRoot();
			BPIRootParcelablePlease.readFromParcel(target, source);
			return target;
		}
		public BPIRoot[] newArray(int size){return new BPIRoot[size];}
	};
}
