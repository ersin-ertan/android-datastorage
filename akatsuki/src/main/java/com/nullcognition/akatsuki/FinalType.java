package com.nullcognition.akatsuki;
// ersin 25/08/15 Copyright (c) 2015+ All rights reserved.


import android.os.Bundle;

import com.sora.util.akatsuki.MultiKeyTypeConverter;

public final class FinalType{

	String      string;
	int         intt;
	CustomType  customType;
	CustomTypeP customTypeP;
}


final class CustomType{

	String CTStrin;
}


final class CustomTypeP{

	String CTPString;
}


class FinalTypeTypeConverter extends MultiKeyTypeConverter<FinalType>{

	// because of this bundle.put method, our FinalType or the CustomType can be
	// wrapped within a parcelable and but inside of the bundle without having to individually map
	// the variables

	@Override
	protected String[] saveMultiple(Bundle bundle, FinalType finalType){
		String[] keys = generateKey(3);
		bundle.putString(keys[0], finalType.string);
		bundle.putInt(keys[1], finalType.intt);
		bundle.putString(keys[2], finalType.customType.CTStrin);
		CustomTypeParcelable ctp = new CustomTypeParcelable();
		ctp.CTPString = finalType.customTypeP.CTPString; // and if the field was a custom type we would use bagger
		// to create a new type wrapper for parcelable
		bundle.putParcelable(keys[3], ctp);
		return keys;
	}

	@Override
	protected FinalType restoreMultiple(Bundle bundle, FinalType finalType, String[] keys){
		finalType.string = bundle.getString(keys[0]);
		finalType.intt = bundle.getInt(keys[1]);
		finalType.customType.CTStrin = bundle.getString(keys[2]);
		CustomTypeParcelable ctp = bundle.getParcelable(keys[3]);
		finalType.customTypeP.CTPString = ctp.CTPString;

		return finalType;
	}

}
