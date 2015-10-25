package com.nullcognition.akatsuki.v0_2_0;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.nullcognition.akatsuki.R;
import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Arg;

public class Main4Activity extends Activity{

	@Arg String s;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main4);
		Akatsuki.restore(this, savedInstanceState);

		Toast.makeText(Main4Activity.this, s, Toast.LENGTH_SHORT).show();
	}
}
