package com.marcinorlowski.fonty.demo

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

import com.marcinorlowski.fonty.Fonty

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
