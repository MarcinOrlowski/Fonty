package com.marcinorlowski.fonty.demo

import com.marcinorlowski.fonty.Fonty

/**
 ******************************************************************************
 *
 * Copyright 2013-2019 Marcin Orlowski <github@MarcinOrlowski.com>
 *
 * Licensed under the Apache License 2.0
 *
 ******************************************************************************
 */
class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        Fonty
            .context(this)
//            .fontDir("otherFolder")
//            .typefaceFallback(false)
            .normalTypeface("Exo-Regular.ttf")
            .italicTypeface("Aramis-Italic.ttf")
            .boldTypeface("Capture_it.ttf")
            .build()
    }

}
