package com.marcinorlowski.fonty.demo;

/*
 ******************************************************************************
 *
 * Copyright 2013-2019 Marcin Orlowski
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
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.marcinorlowski.fonty.Fonty;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

	ActionBarDrawerToggle mDrawerToggle;

	protected Unbinder mButterKnifeUnbinder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = findViewById(R.id.toolbar);
		toolbar.setTitle("Fonty by Marcin Orlowski");
		toolbar.setSubtitle("Easily change fonts of your app!");
		setSupportActionBar(toolbar);

		final ActionBar actionBar = getSupportActionBar();

		if (actionBar != null) {
			DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

			actionBar.setDisplayHomeAsUpEnabled(true);
			mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.toggle_opend, R.string.toggle_closed) {
				public void onDrawerClosed(View drawerView) {
					super.onDrawerClosed(drawerView);
					supportInvalidateOptionsMenu();
				}

				public void onDrawerOpened(View drawerView) {
					super.onDrawerOpened(drawerView);
					supportInvalidateOptionsMenu();
				}
			};
			mDrawerToggle.setDrawerIndicatorEnabled(true);

			drawerLayout.addDrawerListener(mDrawerToggle);
			mDrawerToggle.syncState();
		}

		// apply our font replacement
		Fonty.setFonts(this);

		mButterKnifeUnbinder = ButterKnife.bind(this);
	}

	@Override
	protected void onDestroy() {
		mButterKnifeUnbinder.unbind();
		super.onDestroy();
	}

	@OnClick (R.id.github)
	public void clickGithub() {
		try {
			startActivity(
					new Intent(Intent.ACTION_VIEW,
							Uri.parse("https://github.com/MarcinOrlowski/fonty/")
					)
			);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(MainActivity.this, "Failed to launch Web Browser", Toast.LENGTH_SHORT).show();
		}
	}

	protected Handler mHandler = new Handler();

	@OnClick (R.id.button)
	public void clickButton() {
		int[] ids = {R.id.til1, R.id.til2};
		for (int id : ids) {
			TextInputLayout til = findViewById(id);
			til.setErrorEnabled(true);
			til.setError("Some error text");
		}

		mHandler.removeCallbacks(mRunnable);
		mHandler.postDelayed(mRunnable, 6 * 1000);
	}

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
