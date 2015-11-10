package com.nullcognition.storio.sqlite;
// ersin 09/11/15 Copyright (c) 2015+ All rights reserved.


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.nullcognition.storio.R;

public class SqliteFragment extends Fragment implements AdapterView.OnItemSelectedListener{

	public static final String TAG = "SqliteFragment";

	Spinner spinner;

	@Nullable @Override public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.sqlite_layout, container, false);

		setSpinner(rootView);

		return rootView;
	}

	private void setSpinner(View rootView){
		spinner = (Spinner) rootView.findViewById(R.id.db_commands);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.db_commands, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}


	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
		Context c = getActivity();
		switch((String) parent.getItemAtPosition(pos)){
			case "Get":
				Toast.makeText(c, "Get", Toast.LENGTH_SHORT).show();
				break;
			case "Put":
				Toast.makeText(c, "Put", Toast.LENGTH_SHORT).show();
				break;
			case "Delete":
				Toast.makeText(c, "Delete", Toast.LENGTH_SHORT).show();
				break;
			default:
				Toast.makeText(c, "Default", Toast.LENGTH_SHORT).show();
				break;
		}
	}

	public void onNothingSelected(AdapterView<?> parent){ }
}
