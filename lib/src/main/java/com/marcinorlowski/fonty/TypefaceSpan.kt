package com.marcinorlowski.fonty

/** ******************************************************************************
 *
 * Fonty - Android custom fonts made easy
 *
 * @author      Marcin Orlowski <mail (#) marcinOrlowski (.) com>
 * @copyright   2013-2022 2022 Marcin Orlowski
 * @license     https://opensource.org/licenses/Apache-2.0
 * @link        https://github.com/MarcinOrlowski/fonty
 *
 ***************************************************************************** **/

import android.graphics.Paint
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

class TypefaceSpan
/**
 * @param fallback
 * @param className
 * @param itemId
 */
(private val fallback: Boolean, private val className: String, private val itemId: Int) : MetricAffectingSpan() {

    override fun updateDrawState(textPaint: TextPaint) {
        apply(textPaint)
    }

    override fun updateMeasureState(textPaint: TextPaint) {
        apply(textPaint)
    }

    private fun apply(paint: Paint) {
        paint.typeface = Utils.substituteTypeface(paint.typeface, fallback, className, itemId)
    }
}
