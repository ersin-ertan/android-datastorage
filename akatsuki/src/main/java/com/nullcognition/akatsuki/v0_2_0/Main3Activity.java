package com.nullcognition.akatsuki.v0_2_0;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import com.nullcognition.akatsuki.R;
import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Arg;
import com.sora.util.akatsuki.Retained;

public class Main3Activity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);


		if(savedInstanceState == null){
			MyFrag f = Builders.MyFrag().s("s").build(this);
			getFragmentManager().beginTransaction().add(f, "f").commit();
		}
		Builders.Main4Activity().s("NextActivity").startActivity(this);
	}

	// static nest does not work with arg but top level does

	public static class MyFrag extends Fragment{

		public MyFrag(){}

		@Retained @Arg String s;

		@Override public void onCreate(final Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			int i = 1;
			Akatsuki.restore(this, savedInstanceState, getArguments());
			if(getArguments() == null){
				Toast.makeText(this.getActivity(), "Null", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(this.getActivity(), "Arguments value is: " + getArguments().getString("s"), Toast.LENGTH_SHORT).show();
				s = "retained";

			}
		}

		@Override public void onStart(){
			super.onStart();
			Toast.makeText(this.getActivity(), s, Toast.LENGTH_SHORT).show();
		}

		@Override public void onSaveInstanceState(final Bundle outState){
			super.onSaveInstanceState(outState);
			Akatsuki.save(this, outState);
		}
	}


}
