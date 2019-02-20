
package com.marcinorlowski.fonty.demo;

import com.marcinorlowski.fonty.Fonty;

/**
 * *****************************************************************************
 * <p>
 * Copyright 2013-2019 Marcin Orlowski <github@MarcinOrlowski.com>
 * <p>
 * Licensed under the Apache License 2.0
 * <p>
 * *****************************************************************************
 */
public class Application extends android.app.Application {

	@Override
	public void onCreate() {
		super.onCreate();

		Fonty.context(this)
//            .fontDir("otherFolder")
//            .typefaceFallback(false)
			 .normalTypeface("Exo-Regular.ttf")
			 .italicTypeface("Aramis-Italic.ttf")
			 .boldTypeface("Capture_it.ttf")
			 .build();
	}

}
