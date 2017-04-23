package com.marcinorlowski.fonty.demo;

/*
 ******************************************************************************
 *
 * Copyright 2013-2017 Marcin Orłowski
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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.marcinorlowski.fonty.Fonty;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.github).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					startActivity(
							new Intent(Intent.ACTION_VIEW,
									Uri.parse("https://github.com/MarcinOrlowski/fonty/")
							)
					);
				} catch (Exception e) {
					Toast.makeText(MainActivity.this, "Failed to open browser", Toast.LENGTH_SHORT).show();
				}
			}
		});

		// apply our font replacement
		Fonty.setFonts(this);

		int[] ids = {R.id.github, R.id.button};
		for (int id : ids) {
			findViewById(id).setOnClickListener(mOnClickListener);
		}
	}


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
					mHandler.postDelayed(mRunnable, 3 * 1000);

					break;

				case R.id.github:
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse("https://github.com/MarcinOrlowski/Fonty"));
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					try {
						startActivity(intent);
					} catch (ActivityNotFoundException e) {
						Toast.makeText(MainActivity.this, "Failed to launch Web Browser", Toast.LENGTH_SHORT).show();
					}
					break;
			}
		}
	};

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
