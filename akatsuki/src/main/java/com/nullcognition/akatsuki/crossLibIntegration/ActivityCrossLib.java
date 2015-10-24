package com.nullcognition.akatsuki.crossLibIntegration;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nullcognition.akatsuki.R;
import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Retained;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import android.support.v7.app.AppCompatActivity;

public class ActivityCrossLib extends Activity{

	@Bind(R.id.textView_activity) TextView textView;

	// see @RetainConfig(restorePolicy = RestorePolicy.IF_NULL) in MainActivity
	@Retained String textViewsRetainableText = null;

	@OnClick(R.id.buttonRetain_activity) void retain(){
		textViewsRetainableText = "clicked & retained";
		textView.setText(textViewsRetainableText);
	}

	@OnClick(R.id.buttonCreateFrag_activity) void createFrag(final View view){
		Fragment fragment = new FragBuilder(new AParcelable()).build();
		getFragmentManager().beginTransaction().add(R.id.linearLayout_activity, fragment).commit();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cross_lib);

		ButterKnife.bind(this);
		Akatsuki.restore(this, savedInstanceState);

		textView.setText(textViewsRetainableText);
	}

	@Override protected void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		Akatsuki.save(this, outState);
	}


}
