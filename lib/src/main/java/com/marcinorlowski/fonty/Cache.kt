package com.marcinorlowski.fonty

import android.content.Context
import android.graphics.Typeface

import java.util.Hashtable

/**
 ******************************************************************************
 *
 * Copyright 2013-2019 Marcin Orlowski <github@MarcinOrlowski.com>
 *
 * Licensed under the Apache License 2.0
 *
 ******************************************************************************
 */
class Cache protected constructor()// singleton
{
    // we cache used typefaces to avoid memory leaks due to framework bug #9904:
    // http://code.google.com/p/android/issues/detail?id=9904
    protected val mTypefaceCache = Hashtable<String, Typeface>()

    /**
     * Adds new typeface to the cache. Throws RuntimeException if given alias already exists
     *
     * @param alias    font name alias
     * @param typeface typeface to cache
     *
     * @return instance of Cache for easy chaining
     */
    fun add(alias: String, typeface: Typeface): Cache {
        if (mTypefaceCache.containsKey(alias)) {
            throw RuntimeException("Typeface '$alias' already exists in cache")
        }

        mTypefaceCache[alias] = typeface

        return this
    }

    /**
     * Add typeface to font cache.
     *
     * @param context  application context
     * @param alias    typeface alias
     * @param fileName path to font asset
     *
     * @return instance of Cache for easy chaining
     */
    fun add(context: Context, alias: String, fileName: String): Cache {
        synchronized(mTypefaceCache) {
            if (!mTypefaceCache.containsKey(alias)) {
                val typeface = Typeface.createFromAsset(context.assets, fileName)
                mTypefaceCache[alias] = typeface
            }
        }
        return this
    }

    /**
     * Returns typeface referenced by given alias. Throws RuntimeException when alias is invalid
     *
     * @param alias typeface alias
     *
     * @return the typeface
     */
    operator fun get(alias: String): Typeface? {
        synchronized(mTypefaceCache) {
            if (has(alias)) {
                return mTypefaceCache[alias]
            } else {
                throw RuntimeException("Font alias '$alias' not found.")
            }
        }
    }

    /**
     * Checks if given alias has in cache
     *
     * @param alias typeface alias to look for
     *
     * @return @true if alias is cached, @false otherwise
     */
    fun has(alias: String): Boolean {
        return mTypefaceCache.containsKey(alias)
    }

    companion object {
        protected var instance_: Cache? = null

        /**
         * Returns instance of typeface cache (singleton)
         *
         * @return instance of Cache for easy chaining
         */
        val instance: Cache
            get() {
                if (instance_ == null) {
                    instance_ = Cache()
                }

                return instance_!!
            }
    }

}
