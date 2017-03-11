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
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
	}

}
