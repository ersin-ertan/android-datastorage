package com.nullcognition.akatsuki;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Retained;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ActivityMain01 extends Activity{

	@Bind(R.id.textView) TextView textView;

	@Override
	protected void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main01);
		// restore your bean from the Bundle
		ButterKnife.bind(this);

		// if we were passed the bundle as part of the intent but want the data to be mutable
		// editable via this activity such that rotations will provide the inputted data, not the intents
		// check the state of the saveInstanceState

		if(null == savedInstanceState && getIntent() != null){
			if(null != getIntent().getExtras()){ // intent extras are persistent after rotations
				MyBean bean = Akatsuki.deserialize(new MyBean(), getIntent().getExtras());
				textView.setText("retained:" + bean.retained + " retained protected:" + bean.retainedProtected);
			}
		}
		else{
			bean = new MyBean();
			bean.retained = 199;
			bean.retainedProtected = 188;

			textView.setText("no state passed from activity\nclick me @for rotation");

			textView.setOnClickListener(new View.OnClickListener(){
				@Override public void onClick(final View v){
					Akatsuki.restore(this, savedInstanceState);
					textView.setText("retained:" + bean.retained + " retained protected:" + bean.retainedProtected);
				}
			});
		}
	}

	@Retained MyBean bean;

	@Override protected void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		Akatsuki.save(this, outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.activity_main01, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int id = item.getItemId();

		if(id == R.id.action_settings){
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
