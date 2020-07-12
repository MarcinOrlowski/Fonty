Changelog
=========
 * v3.1.5 (2020-07-11)
   - Converted demo application class to Koltin
   - Corrected Space widget usage

 * v3.1.4 (2019-05-08)
   - Fixed `get()` not being properly exported.
   - Internal library code cleanup.

 * v3.1.3 (2019-02-19)
   - Corrected `versionCode` and `versionName`.
   - Fixed Java interface.
   - Portion of demo app is back in Java (for testing purposes).

 * v3.1.0 (2019-02-19)
   - Fixed demo app using wrong widget classes and crashing.
   - Deprecated `done()`, `regularTypeface()` methods are now removed.
   - Converted library and demo app converted to Kotlin.
   - `fontDir()` no longer accepts `null` arguments.
   - Reworked builder, config methods can now be called in any order.
   - Added some extra sanity checks.

 * v3.0.0 (2019-02-18)
   - Migrated to Jetpack (androidx.*) libraries.
   - No longer pulls databinding library.

 * v2.2.0 (2018-08-27)
   - `done()` is now deprecated in favor of commonly used `build()`

 * v2.1.0 (2018-02-24)
   - `regularTypeface()` is now deprecated. Use `normalTypeface()`
   - Typeface fallback is now enabled by default
   - Fixed navigation drawer's header in demo app not showing any content.

 * v2.0.0 (2017-04-24)
   - Renamed `init()` method to `context()`.
   - Configuration chain now must end with `done()`.
   - Improved public methods argument validation.
   - [#1] Added italic support (`italicTypeface()`).
   - Added typeface fallback feature. See `typefaceFallback()`.
   - Fixed styling of `TextInputLayout`'s `EditText` widget.
   - Added `NavigationDrawer` and `Toolbar` to demo app.

 * v1.2.0 (2017-04-23)
   - [#2] Added support for `TextInputLayout`.

 * v1.1.0 (2017-03-12)
   - Default font dir is `fonts/`
   - `fontDir()` accepts `null` as argument.
   - `addTypeface()` is now `add()`.

 * v1.0.0 (2017-03-11)
   - Initial public release.
