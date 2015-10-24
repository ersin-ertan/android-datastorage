package com.nullcognition.simplefragment;
// ersin 24/10/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.tatarka.simplefragment.SimpleFragment;

public class HelloWorldFragment extends SimpleFragment{

	// on create only called once, onview created/destroyed is where to get view based
	// and set based properties as they would alreay be loaded

	static final String STATE_HELLO_TEXT = "STATE_HELLO_TEXT";
	String   helloText;
	TextView helloTextView;

	@Override public void onCreate(final Context context, @Nullable Bundle state){

		if(state != null){helloText = state.getString(STATE_HELLO_TEXT);}

		if(helloText == null){

			new Runnable(){
				@Override public void run(){
					try{
						Thread.sleep(3000);
					}
					catch(InterruptedException e){
						e.printStackTrace();
					}

					helloText = "hello world returned";
					if(helloTextView != null){
						helloTextView.setText(helloText);
					}

				}
			}.run();
		}
	}

	@Override public void onSave(Bundle state){
		state.putString(STATE_HELLO_TEXT, helloText);
	}

	@Override public View onCreateView(final LayoutInflater inflater, final ViewGroup parent){
		return inflater.inflate(R.layout.hello_world, parent, false);
	}

	// keep activity state/ view state in between these life cycles

	@Override public void onViewCreated(View view){
		helloTextView = (TextView) view.findViewById(R.id.hello_world);
		helloTextView.setText(helloText);
	}

	@Override public void onViewDestroyed(View view){
		helloTextView = null;
	}
}
