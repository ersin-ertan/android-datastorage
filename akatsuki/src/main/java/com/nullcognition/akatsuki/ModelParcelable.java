package com.nullcognition.akatsuki;
// ersin 25/08/15 Copyright (c) 2015+ All rights reserved.

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

@ParcelablePlease
public class ModelParcelable implements Parcelable{

	String name = "modelparelable.name default - Click me";

	@Override public int describeContents(){ return 0; }

	@Override public void writeToParcel(Parcel dest, int flags){ModelParcelableParcelablePlease.writeToParcel(this, dest, flags);}

	public static final Creator<ModelParcelable> CREATOR = new Creator<ModelParcelable>(){
		public ModelParcelable createFromParcel(Parcel source){
			ModelParcelable target = new ModelParcelable();
			ModelParcelableParcelablePlease.readFromParcel(target, source);
			return target;
		}

		public ModelParcelable[] newArray(int size){return new ModelParcelable[size];}
	};
}
