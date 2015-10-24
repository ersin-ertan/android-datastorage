package com.nullcognition.retain_state;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// retain state throughout configuration changes
// RetainState.get() can be used in any place where the Activity's context is available, like in a Fragment or a custom View


public class MainActivity extends BaseActivity{

	private Model model;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		model = RetainState.get(this).state(R.id.main_activity, new RetainState.OnCreate<Model>(){
			@Override
			public Model onCreate(){
				return new Model();
			}
		});

		tv = ((TextView) findViewById(R.id.textView));
		tv.setText(model.name);
		tv.setOnClickListener(new View.OnClickListener(){
			@Override public void onClick(final View v){
				new Runnable(){
					@Override public void run(){
						try{
							Thread.sleep(5000);
						}
						catch(InterruptedException e){
							e.printStackTrace();
						}
						model.name = "new name";
						tv.setText(model.name);

					}
				}.run();
			}
		});

	}

	@Override protected void onDestroy(){
		super.onDestroy();
		tv.setOnClickListener(null);
		// Make sure you remove any references that can cause your Activity to leak!
//		model.setListener(null);
	}

	public static class Model{

		public String name = "default";
	}
}
