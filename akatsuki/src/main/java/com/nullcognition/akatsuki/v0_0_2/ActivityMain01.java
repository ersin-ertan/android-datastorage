package com.nullcognition.akatsuki.v0_0_2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.nullcognition.akatsuki.R;
import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Retained;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ActivityMain01 extends Activity{

	@Bind(R.id.textView) TextView textView;
	@Retained            MyBean   bean;
	@Retained boolean retainSaveValues = false;

	@Override
	protected void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main01);
		ButterKnife.bind(this);

		// whether or not the button is clicked saveInstanceState will be !null after a rotation
		// thus this code block will run causing unneeded computation,
		// which ends up being overwritten by the deserialize
		if(null != savedInstanceState){
			bean = new MyBean();
			bean.model = new MyBean.Model();
			Akatsuki.restore(this, savedInstanceState);
			textView.setText(bean.retained + "\n" + bean.retainedProtected + "\n"
//							+ bean.retainedChild + "\n" + bean.retainedGeneric + "\n"
							+ bean.model.name
			);
		}

		if(!retainSaveValues){
			if(null != getIntent()){
				if(null != getIntent().getExtras()){ // intent extras are persistent after rotations
					MyBean myBean = new MyBean();
					myBean.model = new MyBean.Model(); // or set model as = new ModelParcelable() in MyBean
					bean = Akatsuki.deserialize(myBean, getIntent().getExtras());
					textView.setText(bean.retained + " \n" + bean.retainedProtected + " \n" +
							bean.retainedChild + " \n" +
							bean.model.name + " \n" +
							"bean.notRetained:" + bean.notRetained + "\n" +
							" bean.notRetained_debug:" + bean.notRetained_forDebugging);
				}
			}
		}
	}

	@OnClick(R.id.button) void saveValuesFromWithinActivityOverwrittingSerializedValues(){
		retainSaveValues = true;
		bean.retained = "bean.retain from within activity via save";
		bean.retainedProtected = "bean.retainPro from within activity via save";
		bean.retainedChild = "bean.retainChild from within activity via save";
//		bean.retainedGeneric = "bean.retainGeneric from within activity via save";
		bean.model.name = "bean.model.name retained from within activity save";
		textView.setText("saved - rotate to see values from save instead of the deserialize from intent extra");
	}

	@Override protected void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		if(retainSaveValues){Akatsuki.save(this, outState);}
	}
}
