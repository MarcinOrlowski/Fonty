package com.marcinorlowski.fonty;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;

import java.util.Hashtable;

/*
 ******************************************************************************
 *
 * Copyright 2013-2017 Marcin Orłowski
 *
 * Licensed under the Apache License 2.0
 *
 ******************************************************************************
 *
 * @author Marcin Orlowski <mail@MarcinOrlowski.com>
 *
 ******************************************************************************
 */
public class Cache {

	// we cache used typefaces to avoid memory leaks due to framework bug #9904:
	// http://code.google.com/p/android/issues/detail?id=9904
	protected final Hashtable<String, Typeface> mTypefaceCache = new Hashtable<>();

	protected Cache() {
		// singleton
	}

	protected static Cache _instance;

	public static Cache getInstance() {
		if (_instance == null) {
			_instance = new Cache();
		}

		return _instance;
	}

	public Cache add(@NonNull Context context, String alias, String fileName) {
		synchronized (mTypefaceCache) {
			if (mTypefaceCache.containsKey(alias) == false) {
				Typeface typeface = Typeface.createFromAsset(context.getAssets(), fileName);
				mTypefaceCache.put(alias, typeface);
			}

			return this;
		}
	}

	public Typeface get(@NonNull String alias) {
		synchronized (mTypefaceCache) {
			if (mTypefaceCache.containsKey(alias)) {
				return mTypefaceCache.get(alias);
			} else {
				throw new RuntimeException("Font alias '" + alias + "' matches no cache entry. add() font first.");
			}
		}
	}

}
