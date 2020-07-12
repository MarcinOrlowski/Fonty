package com.marcinorlowski.fonty.demo

import android.app.Application
import com.marcinorlowski.fonty.Fonty.Companion.context

/**
 ******************************************************************************
 *
 * Copyright 2013-2020 Marcin Orlowski <github@MarcinOrlowski.com>
 *
 * Licensed under the Apache License 2.0
 *
 ******************************************************************************
 */
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        context(this)
//              .fontDir("otherFolder")
//              .typefaceFallback(false)
                .italicTypeface("Aramis-Italic.ttf")
                .normalTypeface("Exo-Regular.ttf")
                .boldTypeface("Capture_it.ttf")
                .build()
    }
}
