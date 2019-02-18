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

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

import androidx.annotation.NonNull;

public class Cache {

	// we cache used typefaces to avoid memory leaks due to framework bug #9904:
	// http://code.google.com/p/android/issues/detail?id=9904
	protected final Hashtable<String, Typeface> mTypefaceCache = new Hashtable<>();

	protected Cache() {
		// singleton
	}

	protected static Cache _instance;


	/**
	 * Returns instance of typeface cache (singleton)
	 *
	 * @return instance of Cache for easy chaining
	 */
	public static Cache getInstance() {
		if (_instance == null) {
			_instance = new Cache();
		}

		return _instance;
	}


	/**
	 * Adds new typeface to the cache. Throws RuntimeException if given alias already exists
	 *
	 * @param alias    font name alias
	 * @param typeface typeface to cache
	 *
	 * @return instance of Cache for easy chaining
	 */
	public Cache add(String alias, Typeface typeface) {
		if (mTypefaceCache.containsKey(alias)) {
			throw new RuntimeException("Typeface '" + alias + "' already exists in cache");
		}

		mTypefaceCache.put(alias, typeface);

		return this;
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
	public Cache add(@NonNull Context context, String alias, String fileName) {
		synchronized (mTypefaceCache) {
			if (mTypefaceCache.containsKey(alias) == false) {
				Typeface typeface = Typeface.createFromAsset(context.getAssets(), fileName);
				mTypefaceCache.put(alias, typeface);
			}

			return this;
		}
	}

	/**
	 * Returns typeface referenced by given alias. Throws RuntimeException when alias is invalid
	 *
	 * @param alias typeface alias
	 *
	 * @return the typeface
	 */
	public Typeface get(@NonNull String alias) {
		synchronized (mTypefaceCache) {
			if (has(alias)) {
				return mTypefaceCache.get(alias);
			} else {
				throw new RuntimeException("Font alias '" + alias + "' not found.");
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
	public boolean has(@NonNull String alias) {
		return mTypefaceCache.containsKey(alias);
	}

}
