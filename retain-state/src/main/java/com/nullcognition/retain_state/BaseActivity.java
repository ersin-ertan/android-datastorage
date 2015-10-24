package com.nullcognition.retain_state;
// ersin 24/10/15 Copyright (c) 2015+ All rights reserved.


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements RetainState.Provider{

	private RetainState retainState;

	@Override protected void onCreate(Bundle savedInstanceState){
		retainState = new RetainState(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override public Object onRetainCustomNonConfigurationInstance(){
		return retainState.getState();
	}

	@Override public RetainState getRetainState(){
		return retainState;
	}
}
