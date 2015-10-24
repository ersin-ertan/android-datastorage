package com.nullcognition.akatsuki.v0_0_3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nullcognition.akatsuki.R;
import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Retained;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityMain2 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		ButterKnife.bind(this);
		Akatsuki.restore(this, savedInstanceState);
		if(null != savedInstanceState){populateTextViewsFromRetainedFields();}
	}

	private void setText(TextView textView, String string){textView.setText(string);}

	private void populateTextViewsFromRetainedFields(){
		setText(textViewMyInt, String.valueOf(myInt));
		setText(textViewMyString, myString);

// reference object and textView could be null, if never instantiated/delayed view bind
		setText(textViewBipMyString, bip.myString);
		setText(textViewBipMyInt, String.valueOf(bip.myInt));
		setText(textViewBiMyString, bi.beanString + " " + bi.beanRootString);
		setText(textViewBwgMyType, bwg.myType);
		setText(textViewBinpMyString, binp.myString + " " + binp.myStringRoot);
		setText(textViewBpinMyString, bpin.myString + " " + bpin.myStringRoot);
	}

	@Override protected void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		Akatsuki.save(this, outState);
	}

	@Retained @BindInt(R.integer.myInt) int      myInt;
	@Bind(R.id.TextView_myInt)          TextView textViewMyInt;

	@OnClick(R.id.TextView_myInt) void myInt(final TextView textView){
		myInt = 1;
		textView.setText(String.valueOf(myInt));
	}


// To avoid populating the bean classes with @BindString / int, their default variable values are
// set within the class, which could be removed if populated from within the constructor

	@Bind(R.id.TextView_myString) TextView textViewMyString;
	@Retained @BindString(R.string.myString) String myString = "default not used";
	// string default is never set because butterknife @BindString
	@OnClick(R.id.TextView_myString) void myString(final TextView textView){
		myString = "myString retained";
		textView.setText(myString);
	}

	// new here, or else new before every restore
	@Bind(R.id.TextView_bip_myString) TextView textViewBipMyString;
	@Retained BeanImplementsParcelable bip = new BeanImplementsParcelable();
	@OnClick(R.id.TextView_bip_myString) void bip_myString(final TextView textView){
		bip.myString = "bip.myString retained";
		textView.setText(bip.myString);
	}
	@Bind(R.id.TextView_bip_myInt) TextView textViewBipMyInt;
	@OnClick(R.id.TextView_bip_myInt) void bip_myInt(final TextView textView){
		bip.myInt = 3;
		textView.setText(String.valueOf(bip.myInt));
	}

	@Bind(R.id.TextView_bi_beanString) TextView textViewBiMyString;
	@Retained BeanInherited bi = new BeanInherited();
	@OnClick(R.id.TextView_bi_beanString) void bi_myString(final TextView textView){
		bi.beanString = "bi.beanString retained";
		bi.beanRootString = "bi.rootString retained";
		textView.setText(bi.beanString + " " + bi.beanRootString);
	}

	@Bind(R.id.TextView_bwg_myType) TextView textViewBwgMyType;
	@Retained BeanWithGeneric<String> bwg = new BeanWithGeneric<>();
	// does not the the text view as the onclick listener, see BeanWithGeneric class
	@OnClick(R.id.Button_bwg) void bwg_myType(){
		bwg.myType = "bwg.myType retained";
		textViewBwgMyType.setText(bwg.myType);
	}


	// should not mix pracelables with @Retained

	@Bind(R.id.TextView_binp_myString) TextView textViewBinpMyString;
	@Retained BeanInheritedParcelable binp = new BeanInheritedParcelable();
	@OnClick(R.id.TextView_binp_myString) void _binp_myString(){
		binp.myString = "binp.myString retained";
		binp.myStringRoot = "binp.myStringRoot retained";
		textViewBinpMyString.setText(binp.myString + " " + binp.myStringRoot);
	}

	@Bind(R.id.TextView_bpin_myString) TextView textViewBpinMyString;
	@Retained BeanParcelableInherited bpin = new BeanParcelableInherited();
	@OnClick(R.id.TextView_bpin_myString) void _bpin_myString(){
		bpin.myString = "bpin.myString retained";
		bpin.myStringRoot = "bpin.myStringRoot retained";
		textViewBpinMyString.setText(bpin.myString + " " + bpin.myStringRoot);
	}

}
