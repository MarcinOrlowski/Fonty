package com.marcinorlowski.fonty;

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

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class Utils {

	protected static final String mWidgetLogFmt = "Missing '%s', id: 0x%x, class: '%s'";

	/**
	 * Selects substitution typeface based on old Typeface's style
	 *
	 * @param currentTypeface  current Typeface of widget
	 * @param typefaceFallback if @true, will fall back to REGULAR if request BOLD or ITALICS is not configured
	 * @param className        name of class we are substituting typface for
	 * @param widgetId         Id of object (widget, menuItem etc)
	 *
	 * @return
	 */
	public static Typeface substituteTypeface(@Nullable Typeface currentTypeface,
											  boolean typefaceFallback,
											  @NonNull String className,
											  int widgetId) {
		Cache cache = Cache.getInstance();

		String key = Fonty.TYPE_REGULAR;

		if (currentTypeface != null) {
			if (currentTypeface.isBold()) {
				key = Fonty.TYPE_BOLD;
				if (typefaceFallback) {
					if (!cache.has(key)) {
						Log.e(Fonty.LOG_TAG, String.format(mWidgetLogFmt, key, widgetId, className));
						key = Fonty.TYPE_REGULAR;
					}
				}
			} else if (currentTypeface.isItalic()) {
				key = Fonty.TYPE_ITALICS;
				if (typefaceFallback) {
					if (!cache.has(key)) {
						Log.e(Fonty.LOG_TAG, String.format(mWidgetLogFmt, key, widgetId, className));
						key = Fonty.TYPE_REGULAR;
					}
				}
			}
		}

		return cache.get(key);
	}

}
