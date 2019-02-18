package com.marcinorlowski.fonty;

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

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

import androidx.annotation.NonNull;

public class TypefaceSpan extends MetricAffectingSpan {
	private final boolean mFallback;
	private final int mItemId;
	private final String mClassName;

	/**
	 * @param fallback
	 * @param className
	 * @param itemId
	 */
	public TypefaceSpan(boolean fallback, @NonNull String className, int itemId) {
		mFallback = fallback;
		mClassName = className;
		mItemId = itemId;
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
		Typeface oldTf = paint.getTypeface();
		paint.setTypeface(Utils.substituteTypeface(oldTf, mFallback, mClassName, mItemId));
	}
}
