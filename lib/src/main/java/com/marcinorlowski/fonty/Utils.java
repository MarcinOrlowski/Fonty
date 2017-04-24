package com.marcinorlowski.fonty;

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

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.Log;

public class Utils {

	/**
	 * Selects substitution typeface based on old Typeface's style
	 *
	 * @param currentTypeface  current Typeface of widget
	 * @param widgetId         Id of widget
	 * @param typefaceFallback if @true, will fall back to REGULAR if request BOLD or ITALICS is not configured
	 *
	 * @return
	 */
	public static Typeface substituteTypeface(@Nullable Typeface currentTypeface, int widgetId, boolean typefaceFallback) {
		Cache cache = Cache.getInstance();

		String key = Fonty.TYPE_REGULAR;

		if (currentTypeface != null) {
			if (currentTypeface.isBold()) {
				key = Fonty.TYPE_BOLD;
				if (typefaceFallback) {
					if (!cache.has(key)) {
						Log.e(Fonty.LOG_TAG, String.format("Missing typeface for '%s', widget Id: 0x%x", key, widgetId));
						key = Fonty.TYPE_REGULAR;
					}
				}
			} else if (currentTypeface.isItalic()) {
				key = Fonty.TYPE_ITALICS;
				if (typefaceFallback) {
					if (!cache.has(key)) {
						Log.e(Fonty.LOG_TAG, String.format("Missing typeface for '%s', widget Id: 0x%x", key, widgetId));
						key = Fonty.TYPE_REGULAR;
					}
				}
			}
		}

		return cache.get(key);
	}

}
