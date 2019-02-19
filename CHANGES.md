Changelog
=========
 * @dev
   - Deprecated `done()` method is now removed.
   - Demo app converted to Kotlin

 * v3.0.0 (2019-02-18)
   - Migrated to Jetpack (androidx.*) libraries.
   - No longer pulls databinding library.

 * v2.2.0 (2018-08-27)
   - `done()` is now deprecated in favor of commonly used `build()`

 * v2.1.0 (2018-02-24)
   - `regularTypeface()` is now deprecated. Use `normalTypeface()`
   - Typeface fallback is now enabled by default
   - Fixed navigation drawer's header in demo app not showing any content

 * v2.0.0 (2017-04-24)
   - Renamed `init()` method to `context()`
   - Configuration chain now must end with `done()`
   - Improved public methods argument validation
   - [#1] Added italic support (`italicTypeface()`)
   - Added typeface fallback feature. See `typefaceFallback()`
   - Fixed styling of `TextInputLayout`'s `EditText` widget
   - Added `NavigationDrawer` and `Toolbar` to demo app

 * v1.2.0 (2017-04-23)
   - [#2] Added support for `TextInputLayout`

 * v1.1.0 (2017-03-12)
   - Default font dir is `fonts/`
   - `fontDir()` accepts `null` as argument
   - `addTypeface()` is now `add()`

 * v1.0.0 (2017-03-11)
   - Initial public release
