package com.nullcognition.akatsuki;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Retained;

/**
 A placeholder fragment containing a simple view.
 */
public class ActivityMainFragment extends Fragment{

	@Retained
	String myString = "old String";
	TextView tv;

	public ActivityMainFragment(){
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);
		Akatsuki.restore(this, savedInstanceState);
		tv = (TextView) rootView.findViewById(R.id.textView);
		tv.setText(myString);
		tv.setOnClickListener(new View.OnClickListener(){
			@Override public void onClick(final View v){
				myString = "new String";
				tv.setText(myString);
			}
		});

		return rootView;
	}

	@Override public void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		Akatsuki.save(this, outState);
	}
}
