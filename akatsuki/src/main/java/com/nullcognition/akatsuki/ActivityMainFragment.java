package com.nullcognition.akatsuki;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Retained;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityMainFragment extends Fragment{

	@Retained AnnotatedType at;
	@Retained // annotation order does not matter
	@BindString(R.string.defaultString)
	String myString;

	@Bind(R.id.txt_retainedAnntatedType) TextView txtVRetainedAnnotatedType;
	@Bind(R.id.txt_retainedField)        TextView textView;

	@OnClick(R.id.txt_retainedField) void clickText(){
		at.i = 99;
		myString = "tobe retained annotatedType set:99";
		textView.setText(myString);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);

		ButterKnife.bind(this, rootView); // binding and restoration order matters
		at = new AnnotatedType();
		Akatsuki.restore(this, savedInstanceState);

		txtVRetainedAnnotatedType.setText("annotated type i:" + at.i);
		textView.setText(myString);
		return rootView;
	}

	@Override public void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		Akatsuki.save(this, outState);
	}

	public ActivityMainFragment(){}
}
