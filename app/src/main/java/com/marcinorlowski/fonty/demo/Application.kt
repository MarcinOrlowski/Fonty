package com.marcinorlowski.fonty.demo

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

import android.app.Application
import com.marcinorlowski.fonty.Fonty.Companion.context

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
