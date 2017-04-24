package com.marcinorlowski.fonty;

/*
 ******************************************************************************
 *
 * Copyright 2013-2017 Marcin Orlowski
 *
 * Licensed under the Apache License 2.0
 *
 ******************************************************************************
 *
 * @author Marcin Orlowski <mail@MarcinOrlowski.com>
 *
 ******************************************************************************
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Fonty {

	public static final String LOG_TAG = "Fonty";

	public static final String TYPE_REGULAR = "regular";
	public static final String TYPE_BOLD = "bold";
	public static final String TYPE_ITALIC = "italic";

	/**
	 * Font file folder **relative** to your "assets" folder
	 */
	protected static String mFontFolderName = "fonts/";

	/**
	 * Application context
	 */
	protected Context mContext;

	/**
	 * Prevents instantiation with new operator. Use context() instead
	 */
	protected Fonty() {}

	/**
	 * Instantiates a new FontTools object
	 *
	 * @param context the context
	 */
	protected Fonty(@NonNull Context context) {
		mContext = context;
	}

	protected static Fonty _instance;

	// --------------------------------------------------------------------------------------------

	/**
	 * Init font tools context. Must be called as first thin in setup chain! Throws
	 * RuntimeException is context is `null` and IllegalArgumentException if passed
	 * object is not a Context subclass.
	 *
	 * @param context the context to use with Fonty. Preferably application context.
	 *
	 * @return instance of Fonty object for easy chaining
	 */
	public static Fonty context(@NonNull Context context) {
		if (context != null) {
			if (!Context.class.isInstance(context)) {
				throw new IllegalArgumentException("Invalid context object passed");
			}

			if (_instance == null) {
				_instance = new Fonty(context);
			}
		} else {
			throw new RuntimeException("Context cannot be null");
		}

		return _instance;
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * Sets font path (relative to your "assets" folder) to be used to find the font files.
	 *
	 * @param fontDir the font dir **relative** to your "assets" folder.
	 *
	 * @return instance of Fonty object for easy chaining
	 */
	public Fonty fontDir(@Nullable String fontDir) {
		if (fontDir != null) {
			if (!fontDir.substring(fontDir.length() - 1).equals("/")) {
				fontDir = fontDir + "/";
			}
		} else {
			fontDir = "";
		}
		mFontFolderName = fontDir;

		return this;
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * Set typeface to be used for BOLD
	 *
	 * @param fileName the file name of TTF asset file name
	 *
	 * @return instance of Fonty object for easy chaining
	 */
	public Fonty boldTypeface(@SuppressWarnings ("SameParameterValue") @NonNull String fileName) {
		return add(TYPE_BOLD, fileName);
	}

	/**
	 * Set typeface to be used for BOLD
	 *
	 * @param fileNameId string resource id that holds TTF asset file name
	 *
	 * @return instance of Fonty object for easy chaining
	 */
	public Fonty boldTypeface(int fileNameId) {
		return add(TYPE_BOLD, fileNameId);
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * Set typeface to be used for REGULAR/NORMAL
	 *
	 * @param fileName the file name of TTF asset file name
	 *
	 * @return instance of Fonty object for easy chaining
	 */
	public Fonty regularTypeface(@SuppressWarnings ("SameParameterValue") @NonNull String fileName) {
		return add(TYPE_REGULAR, fileName);
	}

	/**
	 * Set typeface to be used for ITALIC style
	 *
	 * @param fileNameId string resource id that holds TTF asset file name
	 *
	 * @return instance of Fonty object for easy chaining
	 */
	public Fonty italicTypeface(int fileNameId) {
		return add(TYPE_ITALIC, fileNameId);
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * Set typeface to be used for ITALIC style
	 *
	 * @param fileName string resource id that holds TTF asset file name
	 *
	 * @return instance of Fonty object for easy chaining
	 */
	public Fonty italicTypeface(@NonNull String fileName) {
		return add(TYPE_ITALIC, fileName);
	}

	/**
	 * Set typeface to be used for REGULAR
	 *
	 * @param fileNameId string resource id that holds TTF asset file name
	 *
	 * @return instance of Fonty object for easy chaining
	 */
	public Fonty regularTypeface(int fileNameId) {
		return add(TYPE_REGULAR, fileNameId);
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * Add typeface to cache. Throws RuntimeException if Fonty's context is not set up.
	 *
	 * @param alias      the typeface alias
	 * @param fileNameId string resource id that holds TTF asset file name
	 *
	 * @return instance of Fonty object for easy chaining
	 */
	protected Fonty add(@NonNull String alias, @StringRes int fileNameId) {
		if (mContext == null) {
			throw new RuntimeException("You must call 'context()' first");
		}

		return add(alias, mContext.getResources().getString(fileNameId));
	}

	/**
	 * Add typeface to cache. Throws RuntimeException if Fonty's context is not set up.
	 *
	 * @param aliasId    the typeface alias string resource Id
	 * @param fileNameId string resource id that holds TTF asset file name
	 *
	 * @return instance of Fonty object for easy chaining
	 */
	protected Fonty add(@StringRes int aliasId, @StringRes int fileNameId) {
		if (mContext == null) {
			throw new RuntimeException("You must call 'context()' first");
		}

		Resources res = mContext.getResources();
		return add(res.getString(aliasId), res.getString(fileNameId));
	}

	/**
	 * Add typeface to Fonty's cache. Throws RuntimeException if Fonty's context is not set up,
	 *
	 * @param alias        the typeface alias
	 * @param fontFileName the file name of TTF asset. Can contain folder names too (i.e. "fnt/foo.ttf"). It will be
	 *                     automatically "glued" with font folder name (see @fontDir()). If you do not want this to happen
	 *                     add "/" to fontFileName, i.e. "/foo.ttf" or "/foo/other-folder/foo.ttf". In such case
	 *                     fontDir is not used.
	 *
	 * @return instance of Fonty object for easy chaining
	 */
	protected Fonty add(@NonNull String alias, @NonNull String fontFileName) {
		if (mContext == null) {
			throw new RuntimeException("You must call 'context()' first");
		}

		if (alias == null) {
			throw new RuntimeException("Typeface alias cannot be null");
		} else if (alias.length() == 0) {
			throw new RuntimeException("Typeface alias cannot be empty string");
		}

		if (fontFileName == null) {
			throw new RuntimeException("Typeface filename cannot be null");
		} else if (fontFileName.length() == 0) {
			throw new RuntimeException("Typeface filename cannot be empty string");
		}

		if (fontFileName.substring(0, 1).equals("/")) {
			// strip leading "/"
			fontFileName = fontFileName.substring(1);
		} else {
			fontFileName = mFontFolderName + fontFileName;
		}

		Cache.getInstance().add(mContext, alias, fontFileName);

		return this;
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * Get typeface.
	 *
	 * @param alias the alias
	 *
	 * @return the typeface
	 */
	public static Typeface get(@NonNull String alias) {
		return Cache.getInstance().get(alias);
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * Sets custom fonts.
	 *
	 * @param activity the activity
	 */
	public static void setFonts(@NonNull Activity activity) {
		setFonts((ViewGroup)(((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0)));
	}

	/**
	 * Sets custom fonts.
	 *
	 * @param viewGroup the view group
	 */
	public static void setFonts(@Nullable ViewGroup viewGroup) {
		if (mConfigured) {
			if ((viewGroup != null)
					&& (viewGroup.getContext().getResources().getDisplayMetrics().densityDpi >= DisplayMetrics.DENSITY_MEDIUM)) {
				setFontsRaw(viewGroup);
			}
		} else {
			throw new RuntimeException("You must conclude your configuration chain with done()");
		}
	}

	// --------------------------------------------------------------------------------------------

	protected static boolean fallback = false;

	/**
	 * Controls typeface fallback mechanism. When widget requires BOLD or ITALIC font and such
	 * typeface is not configured, then: when this option is set to @true RuntimeException
	 * will be thrown due to missing typeface. If is set to @false, then error will be logged
	 * and Fonty will fall back to REGULAR typeface.
	 *
	 * @param mode @true (default) to enable font substitution fallback, @false otherwise
	 *
	 * @return
	 */
	public Fonty typefaceFallback(boolean mode) {
		fallback = mode;

		return _instance;
	}


	// --------------------------------------------------------------------------------------------

	protected static boolean mConfigured = false;

	/**
	 * Concludes configuration phase. Must be called as last method of Fonty config call chain
	 */
	public void done() {
		mConfigured = true;
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * All the font setting work is done here
	 *
	 * @param viewGroup we alter all known children of this group
	 */
	protected static void setFontsRaw(@NonNull ViewGroup viewGroup) {

		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			View view = viewGroup.getChildAt(i);

			if (EditText.class.isInstance(view)) {
				Typeface oldTf = ((EditText)view).getTypeface();
				((EditText)view).setTypeface(
						Utils.substituteTypeface(oldTf, fallback, view.getClass().getName(), view.getId())
				);

			} else if (TextView.class.isInstance(view)) {
				Typeface oldTf = ((TextView)view).getTypeface();
				((TextView)view).setTypeface(
						Utils.substituteTypeface(oldTf, fallback, view.getClass().getName(), view.getId())
				);

			} else if (Button.class.isInstance(view)) {
				Typeface oldTf = ((Button)view).getTypeface();
				((Button)view).setTypeface(
						Utils.substituteTypeface(oldTf, fallback, view.getClass().getName(), view.getId())
				);

			} else if (NavigationView.class.isInstance(view)) {
				NavigationView nv = (NavigationView)view;
				// change font in header (if any)
				int headerCount = nv.getHeaderCount();
				for (int headerIndex = 0; headerIndex < headerCount; headerIndex++) {
					setFontsRaw((ViewGroup)nv.getHeaderView(headerIndex));
				}

				// then modify menu items
				setFontsMenu(nv.getMenu());

			} else if (TextInputLayout.class.isInstance(view)) {
				EditText et = ((TextInputLayout)view).getEditText();
				if (et != null) {
					Typeface oldTf = et.getTypeface();
					((TextInputLayout)view).setTypeface(
							Utils.substituteTypeface(oldTf, fallback, view.getClass().getName(), view.getId())
					);

					et.setTypeface(Utils.substituteTypeface(oldTf, fallback, et.getClass().getName(), et.getId()));
				}

			} else if (ViewGroup.class.isInstance(view)) {
				setFontsRaw((ViewGroup)view);
			}
		}
	}


	/**
	 * Sets custom fonts for menu items (and sub-menus too) using custom TypefaceSpan
	 *
	 * @param menu Menu to work on
	 */
	protected static void setFontsMenu(Menu menu) {
		for (int menuIndex = 0; menuIndex < menu.size(); menuIndex++) {
			MenuItem menuItem = menu.getItem(menuIndex);
			if ((menuItem != null) && (menuItem.getTitle() != null)) {
				SpannableString spannableString = new SpannableString(menuItem.getTitle());
				spannableString.setSpan(
						new TypefaceSpan(fallback, menuItem.getClass().getName(), menuItem.getItemId()),
						0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
				);

				menuItem.setTitle(spannableString);

				if (menuItem.hasSubMenu()) {
					setFontsMenu(menuItem.getSubMenu());
				}
			}
		}
	}


	// end of class
}
