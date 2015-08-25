package com.nullcognition.akatsuki;

import android.app.Fragment;
import android.content.Intent;
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

	@Bind(R.id.txt_retainedAnnotatedType) TextView txtVRetainedAnnotatedType;
	@Bind(R.id.txt_retainedField)         TextView txtVRetainedField;

	@OnClick(R.id.txt_retainedField) void clickField(){
		myString = "myString field be retained on rotation";
		txtVRetainedField.setText(myString);
	}

	@OnClick(R.id.txt_retainedAnnotatedType) void clickAnnotated(){
		at.string = "AnnotatedType.string will be retained on rotation";
		txtVRetainedAnnotatedType.setText(at.string);
	}

	@OnClick(R.id.btn_passValueToActivity) void passSerializedValueToNextActivity(){
		MyBean bean = new MyBean();
		bean.retained = "bean.retained via serialization";
		bean.retainedProtected = "bean.retainedProtected via serialization";
		bean.notRetained = 1;
		bean.notRetained_forDebugging = 2;
		Bundle bundle = Akatsuki.serialize(bean);

		Intent i = new Intent(getActivity(), ActivityMain01.class);
		i.putExtras(bundle);
		startActivity(i);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);

		ButterKnife.bind(this, rootView); // binding and restoration order matters
		at = new AnnotatedType();
		Akatsuki.restore(this, savedInstanceState);

		txtVRetainedAnnotatedType.setText(at.string);
		txtVRetainedField.setText(myString);
		return rootView;
	}

	@Override public void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		Akatsuki.save(this, outState);
	}

	public ActivityMainFragment(){}
}
