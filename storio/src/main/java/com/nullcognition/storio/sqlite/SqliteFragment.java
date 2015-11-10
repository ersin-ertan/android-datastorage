package com.nullcognition.storio.sqlite;
// ersin 09/11/15 Copyright (c) 2015+ All rights reserved.


import android.app.Fragment;
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
import com.nullcognition.storio.sqlite.entity.Model;
import com.nullcognition.storio.sqlite.table.ModelTable;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;

import java.util.List;
import java.util.Random;

public class SqliteFragment extends Fragment implements AdapterView.OnItemSelectedListener{

	public static final String TAG = "SqliteFragment";

	Spinner      spinner;
	StorIOSQLite db;
	RecyclerView rv;
	Model        modelToDelete;

	@Override public void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		db = DefaultStorio.provideStorIOSQLite(new DbOpenHelper(getActivity()));
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
				List<Model> models = dbGet();
				ModelAdapter modelAdapter = new ModelAdapter();
				modelAdapter.setModels(models);
				modelAdapter.notifyDataSetChanged();
				rv.setAdapter(modelAdapter);

				break;
			case "Put":
				boolean result = dbPut();
				Toast.makeText(c, "Put " + String.valueOf(result), Toast.LENGTH_SHORT).show();
				break;
			case "Delete":
				String delete = dbDelete();
				Toast.makeText(c, "Delete " + delete, Toast.LENGTH_SHORT).show();
				break;
			default:
				Toast.makeText(c, "Default", Toast.LENGTH_SHORT).show();
				break;
		}
	}

	private String dbDelete(){
//		db.delete().byQuery();
//		db.delete().object();
//		db.delete().objects();
		if(modelToDelete != null){
			return db.delete().object(modelToDelete).prepare().executeAsBlocking().toString();
		}
		return "nothing";
	}

	private boolean dbPut(){

//		db.put().objects();
//		db.put().object().withPutResolver();
//		db.put().contentValues(new ContentValues());

		Random r = new Random();
		modelToDelete = Model.newModel(String.valueOf(r.nextInt()), String.valueOf(r.nextInt()));

		return db.put()
		         .object(modelToDelete)
		         .prepare()
		         .executeAsBlocking()
		         .wasInserted();
	}

	public void onNothingSelected(AdapterView<?> parent){ }

	private List<Model> dbGet(){

//		db.get().numberOfResults();
//		db.get().cursor().withQuery().prepare();
//		db.get().cursor().withQuery().withGetResolver();

		return db.get().listOfObjects(Model.class).withQuery(ModelTable.QUERY_ALL).prepare().executeAsBlocking();
	}

	public void setRecyclerView(final View rootView){
		rv = ((RecyclerView) rootView.findViewById(R.id.recyclerView));
		rv.setLayoutManager(new LinearLayoutManager(getActivity()));
	}
}
