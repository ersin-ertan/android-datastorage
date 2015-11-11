package com.nullcognition.storio.content_resolver;
// ersin 10/11/15 Copyright (c) 2015+ All rights reserved.


import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.nullcognition.storio.R;
import com.nullcognition.storio.content_resolver.entity.CRModel;
import com.nullcognition.storio.content_resolver.table.CRModelTable;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.Query;

import java.util.List;

// should calls be with the content resolver??


public class CRFragment extends Fragment implements AdapterView.OnItemSelectedListener{

	public static final String TAG = "CRFragment";

	Spinner               spinner;
	StorIOContentResolver contentResolver;
	RecyclerView          rv;
	CRModel               modelToDelete;

	@Override public void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		ContentResolver cr = getActivity().getContentResolver();
		contentResolver = DefaultStorioCR.provideStorIOContentResolver(cr);
	}

	@Nullable @Override public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.sqlite_layout, container, false);

		setSpinner(rootView);
		setRecyclerView(rootView);

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
				List<CRModel> models = contentResolverGet();
				CRModelAdapter modelAdapter = new CRModelAdapter();
				modelAdapter.setCRModels(models);
				modelAdapter.notifyDataSetChanged();
				rv.setAdapter(modelAdapter);

				break;
			case "Put":
				boolean result = contentResolverPut();
				Toast.makeText(c, "Put " + String.valueOf(result), Toast.LENGTH_SHORT).show();
				break;
			case "Delete":
				String delete = contentResolverDelete();
				Toast.makeText(c, "Delete " + delete, Toast.LENGTH_SHORT).show();
				break;
			default:
				Toast.makeText(c, "Default", Toast.LENGTH_SHORT).show();
				break;
		}
	}

	private String contentResolverDelete(){
		if(modelToDelete != null){
			return contentResolver.delete().object(modelToDelete).prepare().executeAsBlocking().toString();
		}
		return "nothing";
	}

	private boolean contentResolverPut(){

		modelToDelete = CRModel.newModel("test");

//		return contentResolver.put()
//		                      .object(modelToDelete)
//		                      .prepare()
//		                      .executeAsBlocking()
//		                      .wasInserted();
		ContentValues cv = new ContentValues();
		cv.put(CRModelTable.COLUMN_CONTENT, "test");

		return contentResolver.put()
		                      .object(modelToDelete)
		                      .prepare()
		                      .executeAsBlocking()
		                      .wasInserted();
	}

	// TODO check return values vs cursor returns -----------------------------------

	public void onNothingSelected(AdapterView<?> parent){ }

	private List<CRModel> contentResolverGet(){

		return contentResolver
				.get()
				.listOfObjects(CRModel.class)
				.withQuery(Query.builder()
				                .uri(DefaultStorioCR.CONTENT_URI)
				                .where("content = ?")
				                .whereArgs("test")
				                .build())
				.prepare()
				.executeAsBlocking();
	}

	public void setRecyclerView(final View rootView){
		rv = ((RecyclerView) rootView.findViewById(R.id.recyclerView));
		rv.setLayoutManager(new LinearLayoutManager(getActivity()));
	}
}
