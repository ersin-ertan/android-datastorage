package com.nullcognition.akatsuki.v0_0_2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nullcognition.akatsuki.R;
import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Retained;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityMainFragment extends Fragment{


	@Retained // annotation order does not matter
	@BindString(R.string.defaultString)
	String myString;

	@Retained                                           AnnotatedType   at;
	@Retained                                           ModelParcelable modelParcelable;
	@Retained(converter = FinalTypeTypeConverter.class) FinalType       finalType;

	@Bind(R.id.txt_retainedAnnotatedType) TextView txtVRetainedAnnotatedType;
	@Bind(R.id.txt_retainedField)         TextView txtVRetainedField;
	@Bind(R.id.txt_modelParcel)           TextView txtVModelParcel;
	@Bind(R.id.txt_customType)            TextView txtVModelParcelCustomType;

	@OnClick(R.id.txt_customType) void clickCustom(){
		txtVModelParcelCustomType.setText(createCustomTypesText());
	}

	private String createCustomTypesText(){
		finalType.intt = 909;
		finalType.string = "finaltype.string saved ";
		if(null == finalType.customType){ finalType.customType = new CustomType(); }
		finalType.customType.CTStrin = "fin.cust.CTStrin saved ";
		if(null == finalType.customTypeP){ finalType.customTypeP = new CustomTypeP(); }
		finalType.customTypeP.CTPString = "fin.custTP.CTPString ";
		String sb = String.valueOf(finalType.intt) + "\n" + finalType.string + "\n" +
				finalType.customType.CTStrin + "\n" + finalType.customTypeP.CTPString;
		return sb;
	}

	@OnClick(R.id.txt_modelParcel) void clickParcel(){
		modelParcelable.name = "modelParcelable.name will be retained on rotation";
		txtVModelParcel.setText(modelParcelable.name);
	}

	@OnClick(R.id.txt_retainedField) void clickField(){
		myString = "myString field will be retained on rotation";
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
		bean.retainedChild = "bean.retainedChild via serialization";

		bean.model = new MyBean.Model();
		bean.model.name = "bean.model.name via serialization";

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
		at = new AnnotatedType(); // will be depreciated
		if(savedInstanceState == null){
			modelParcelable = new ModelParcelable();
			finalType = new FinalType();
		}
		else{ Akatsuki.restore(this, savedInstanceState);}

		txtVModelParcel.setText(modelParcelable.name);
		txtVRetainedAnnotatedType.setText(at.string);
		txtVRetainedField.setText(myString);

		// does not work yet
//		txtVModelParcelCustomType.setText(String.valueOf(finalType.intt) + "\n" + finalType.string + "\n" +
//				finalType.customType.CTStrin + "\n" + finalType.customTypeP.CTPString);

		return rootView;
	}

	@Override public void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		Akatsuki.save(this, outState);
	}

	public ActivityMainFragment(){}
}
