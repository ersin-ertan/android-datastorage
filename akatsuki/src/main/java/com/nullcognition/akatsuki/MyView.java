package com.nullcognition.akatsuki;
// ersin 24/08/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.os.Parcelable;
import android.view.View;

import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Retained;

public class MyView extends View{

	@Retained int i;

	public MyView(final Context context){
		super(context);
	}

	@Override protected Parcelable onSaveInstanceState(){
		return Akatsuki.save(this, super.onSaveInstanceState());
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state){
		super.onRestoreInstanceState(Akatsuki.restore(this, state));
	}
}
