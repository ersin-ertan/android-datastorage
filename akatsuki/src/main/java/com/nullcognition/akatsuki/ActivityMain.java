package com.nullcognition.akatsuki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.sora.util.akatsuki.Akatsuki;

public class ActivityMain extends Activity{


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int id = item.getItemId();

		if(id == R.id.action_settings){
			wireIt();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void wireIt(){

		// serialize your bean into a Bundle
		MyBean bean = new MyBean();
		bean.retained = 99;
		bean.retainedProtected = 88;
		Bundle bundle = Akatsuki.serialize(bean);

		Intent i = new Intent(this, ActivityMain01.class);
		i.putExtras(bundle);
		startActivity(i);
	}

}
