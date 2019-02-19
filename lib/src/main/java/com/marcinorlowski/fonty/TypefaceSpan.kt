package com.marcinorlowski.fonty

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

/**
 ******************************************************************************
 *
 * Copyright 2013-2019 Marcin Orlowski <github@MarcinOrlowski.com>
 *
 * Licensed under the Apache License 2.0
 *
 ******************************************************************************
 */
class TypefaceSpan
/**
 * @param fallback
 * @param className
 * @param itemId
 */
(private val mFallback: Boolean, private val mClassName: String, private val mItemId: Int) : MetricAffectingSpan() {

    override fun updateDrawState(textPaint: TextPaint) {
        apply(textPaint)
    }

    override fun updateMeasureState(textPaint: TextPaint) {
        apply(textPaint)
    }

    private fun apply(paint: Paint) {
        val oldTf = paint.typeface
        paint.typeface = Utils.substituteTypeface(oldTf, mFallback, mClassName, mItemId)
    }
}
