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

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class TypefaceSpan extends MetricAffectingSpan {
	private final Cache mFontCache;

	/**
	 * @param fontCache An instance of Cache.
	 */
	public TypefaceSpan(Cache fontCache) {
		mFontCache = fontCache;
	}

	@Override
	public void updateDrawState(TextPaint textPaint) {
		apply(textPaint);
	}

	@Override
	public void updateMeasureState(TextPaint textPaint) {
		apply(textPaint);
	}

	private void apply(Paint paint) {
		Typeface tf = mFontCache.get("regular");

		Typeface oldTf = paint.getTypeface();
		if ((oldTf != null) && (oldTf.isBold())) {
			tf = mFontCache.get("bold");
		}

		paint.setTypeface(tf);
	}
}
