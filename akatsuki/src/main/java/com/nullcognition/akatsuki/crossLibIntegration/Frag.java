package com.nullcognition.akatsuki.crossLibIntegration;
// ersin 01/09/15 Copyright (c) 2015+ All rights reserved.

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.nullcognition.akatsuki.R;
import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Retained;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import android.support.annotation.Nullable;

public class Frag extends Fragment{

	@Arg @Retained                AParcelable aParcelable;
	@Bind(R.id.textView_fragment) TextView    textView;

	@OnClick(R.id.buttonRetain_fragment) void retain(){
		if(aParcelable == null){
			aParcelable = new AParcelable();
		}
		aParcelable.retainableString = "retained";
		textView.setText(aParcelable.retainableString);
	}

	@Override public void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		FragmentArgs.inject(this);
		Akatsuki.restore(this, savedInstanceState);
	}

	@Override public View onCreateView(final LayoutInflater inflater,
	                                             final ViewGroup container,
	                                             final Bundle savedInstanceState){

		View rootView = inflater.inflate(R.layout.fragment_cross_lib, container, false);
		ButterKnife.bind(this, rootView);
		textView.setText(aParcelable.retainableString);
		return rootView;
	}

	@Override public void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		Akatsuki.save(this, outState);
	}
}
