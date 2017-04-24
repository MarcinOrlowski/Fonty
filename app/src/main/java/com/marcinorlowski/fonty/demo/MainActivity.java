package com.marcinorlowski.fonty.demo;

/*
 ******************************************************************************
 *
 * Copyright 2013-2017 Marcin Orlowski
 *
 * Licensed under the Apache License 2.0
 *
 ******************************************************************************
 *
 * @author Marcin Orlowski <mail@MarcinOrlowski.com>
 *
 ******************************************************************************
 */

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.marcinorlowski.fonty.Fonty;

public class MainActivity extends AppCompatActivity {

	ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		toolbar.setTitle("Fonty by Marcin Orlowski");
		toolbar.setSubtitle("Easily change fonts of your app!");
		setSupportActionBar(toolbar);

		final ActionBar actionBar = getSupportActionBar();

		if (actionBar != null) {
			DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

			actionBar.setDisplayHomeAsUpEnabled(true);
			mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.toggle_opend, R.string.toggle_closed) {
				public void onDrawerClosed(View view) {
					supportInvalidateOptionsMenu();
				}

				public void onDrawerOpened(View drawerView) {
					supportInvalidateOptionsMenu();
				}
			};
			mDrawerToggle.setDrawerIndicatorEnabled(true);

			drawerLayout.setDrawerListener(mDrawerToggle);

			mDrawerToggle.syncState();
		}


		// apply our font replacement
		Fonty.setFonts(this);

		int[] ids = {R.id.github, R.id.button};
		for (int id : ids) {
			findViewById(id).setOnClickListener(mOnClickListener);
		}
	}


	protected Handler mHandler = new Handler();

	protected View.OnClickListener mOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
				case R.id.button:
					int[] ids = {R.id.til1, R.id.til2};
					for (int id : ids) {
						TextInputLayout til = (TextInputLayout)findViewById(id);
						til.setErrorEnabled(true);
						til.setError("Some error text");
					}

					mHandler.removeCallbacks(mRunnable);
					mHandler.postDelayed(mRunnable, 6 * 1000);
					break;

				case R.id.github:
					try {
						startActivity(
								new Intent(Intent.ACTION_VIEW,
										Uri.parse("https://github.com/MarcinOrlowski/fonty/")
								)
						);
					} catch (ActivityNotFoundException e) {
						Toast.makeText(MainActivity.this, "Failed to launch Web Browser", Toast.LENGTH_SHORT).show();
					}
					break;
			}
		}
	};


	// Runnable that hides TIL's error messages
	protected Runnable mRunnable = new Runnable() {
		@Override
		public void run() {
			int[] ids = {R.id.til1, R.id.til2};
			for (int id : ids) {
				((TextInputLayout)findViewById(id)).setError(null);
			}
		}
	};
}
