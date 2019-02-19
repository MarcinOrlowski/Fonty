package com.marcinorlowski.fonty

import android.graphics.Typeface
import android.util.Log

/**
 ******************************************************************************
 *
 * Copyright 2013-2019 Marcin Orlowski <github@MarcinOrlowski.com>
 *
 * Licensed under the Apache License 2.0
 *
 ******************************************************************************
 */
object Utils {

    private const val widgetLogFmt = "Missing '%s', id: 0x%x, class: '%s'"

    /**
     * Selects substitution typeface based on old Typeface's style
     *
     * @param currentTypeface  current Typeface of widget
     * @param typefaceFallback if @true, will fall back to NORMAL if request BOLD or ITALIC is not configured
     * @param className        name of class we are substituting typeface for
     * @param widgetId         Id of object (widget, menuItem etc)
     *
     * @return
     */
    fun substituteTypeface(currentTypeface: Typeface?,
                           typefaceFallback: Boolean,
                           className: String,
                           widgetId: Int): Typeface? {
        val cache = Cache.instance

        var key = Fonty.TYPE_NORMAL

        if (currentTypeface != null) {
            if (currentTypeface.isBold) {
                key = Fonty.TYPE_BOLD
                if (typefaceFallback && !cache.has(key)) {
                    Log.e(Fonty.LOG_TAG, String.format(widgetLogFmt, key, widgetId, className))
                    key = Fonty.TYPE_NORMAL
                }
            } else if (currentTypeface.isItalic) {
                key = Fonty.TYPE_ITALIC
                if (typefaceFallback && !cache.has(key)) {
                    Log.e(Fonty.LOG_TAG, String.format(widgetLogFmt, key, widgetId, className))
                    key = Fonty.TYPE_NORMAL
                }
            }
        }

        return cache[key]
    }

}
