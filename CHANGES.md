Changelog
=========

 * @dev
   - `init()` is now `context()`
   - Improved public methods argument validation
   - Added italics support
   - Now falls back to regular typeface for widgets requesting BOLD or ITALICS when such is not configured.
   - Configuration chain now must end with `done()`

 * v1.2.0 (2017-04-23)
   - [#2] Added support for `TextInputLayout`

 * v1.1.0 (2017-03-12)
   - Fefault font dir is `fonts/`
   - `fontDir()` accepts `null` as argument
   - `addTypeface()` is now `add()`

 * v1.0.0 (2017-03-11)
   - Initial public release
