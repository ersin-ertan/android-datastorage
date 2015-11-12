package com.nullcognition.storiopract00;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.app.Fragment;
import android.content.ContentResolver;
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

import com.nullcognition.storiopract00.contentprovider.DefaultStorioCR;
import com.nullcognition.storiopract00.model.Tweet;
import com.nullcognition.storiopract00.table.TweetTable;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.DeleteQuery;
import com.pushtorefresh.storio.contentresolver.queries.Query;

import java.util.List;
import java.util.Random;

public class CPFragment extends Fragment implements AdapterView.OnItemSelectedListener{

	public static final String TAG = "CRFragment";

	Spinner               spinner;
	StorIOContentResolver contentResolver;
	RecyclerView          rv;
	Tweet                 modelToDelete;

	@Override public void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		ContentResolver cr = getActivity().getContentResolver();
		contentResolver = DefaultStorioCR.provideStorIOContentResolver(cr);
	}

	@Nullable @Override public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.cpfragment, container, false);

		setSpinner(rootView);
		setRecyclerView(rootView);

		return rootView;
	}

	private void setSpinner(View rootView){
		spinner = (Spinner) rootView.findViewById(R.id.spinner);
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
				updateViews();
				break;
			case "Put":
				boolean result = contentResolverPut();
				Toast.makeText(c, "Put " + String.valueOf(result), Toast.LENGTH_SHORT).show();
				break;
			case "Delete":
				String delete = contentResolverDelete();
				Toast.makeText(c, "Delete " + delete, Toast.LENGTH_SHORT).show();
				updateViews();
				break;
			default:
				Toast.makeText(c, "Default", Toast.LENGTH_SHORT).show();
				break;
		}
	}

	public void updateViews(){
		List<Tweet>  models       = contentResolverGet();
		TweetAdapter modelAdapter = new TweetAdapter();
		modelAdapter.setTweets(models);
		modelAdapter.notifyDataSetChanged();
		rv.setAdapter(modelAdapter);
	}

	private String contentResolverDelete(){
		return contentResolver.delete()
		                      .byQuery(DeleteQuery.builder()
				                      // can we do this with out the = ? whereArgs("john") no, because the ? is replaced with the arg
				                      .uri(TweetTable.TWEET_URI) // this had to be the tweet uri
				                      .where(TweetTable.COLUMN_AUTHOR + " = ?")
				                      .whereArgs("john")
				                      .build())
		                      .prepare()
		                      .executeAsBlocking()
		                      .toString();
	}

	private boolean contentResolverPut(){

		Tweet tweet = Tweet.newTweet("john", String.valueOf(new Random().nextInt()));

		return contentResolver.put()
		                      .object(tweet)
		                      .prepare()
		                      .executeAsBlocking()
		                      .wasInserted();
	}

	public void onNothingSelected(AdapterView<?> parent){ }

	private List<Tweet> contentResolverGet(){

		return contentResolver
				.get()
				.listOfObjects(Tweet.class)
				.withQuery(Query.builder()
						.uri(DefaultStorioCR.CONTENT_URI)
//				                .where("content = ?")
//				                .whereArgs("test")
						.build())
				.prepare()
				.executeAsBlocking();
	}

	public void setRecyclerView(final View rootView){
		rv = ((RecyclerView) rootView.findViewById(R.id.recyclerView));
		rv.setLayoutManager(new LinearLayoutManager(getActivity()));
	}
}
