package com.nullcognition.simplefragment;

import android.os.Bundle;

import me.tatarka.simplefragment.SimpleFragmentIntent;
import me.tatarka.simplefragment.activity.SimpleFragmentActivity;
import me.tatarka.simplefragment.key.LayoutKey;

// Survive orientation changes

// First-class nesting

// Immediately added to the view when they are created

// Don't have to worry about commitAllowingStateLoss()

// Fails fast if you attempt to add the same fragment twice

// Many of the same features as native fragments: view paging, inflation from layouts, startActivityForResult(), back stack, dialogs

// Be careful to only keep references any view or activity state between onViewCreated() and onViewDestroyed()



// works with fragment view paging, start activity for results, nesting, extras, backstack, and dialogs

public class MainActivity extends SimpleFragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getSimpleFragmentManager().findOrAdd(SimpleFragmentIntent.of(HelloWorldFragment.class), LayoutKey.of(R.id.linearLayout));
	}
}
