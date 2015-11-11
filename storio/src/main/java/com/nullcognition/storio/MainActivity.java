package com.nullcognition.storio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nullcognition.storio.content_resolver.CRFragment;
import com.nullcognition.storio.sqlite.SqliteFragment;

public class MainActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getFragmentManager().beginTransaction().add(new SqliteFragment(), SqliteFragment.TAG).commit();
		getFragmentManager().beginTransaction().add(R.id.container, new CRFragment(), CRFragment.TAG).commit();
	}
}
