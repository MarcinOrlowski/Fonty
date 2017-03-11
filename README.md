Fonty
=====

 [![Release](https://jitpack.io/v/MarcinOrlowski/fonty.svg)](https://jitpack.io/#MarcinOrlowski/fonty)

 `Fonty` is Android library allowing you to easily change the typeface 
 of your UI elements. Contrary to other implementations `Fonty` is 
 designed with the assumption that if you want to change the font for your
 app, then you change it **globally** per whole application, to achieve 
 consistency across your Fragments or Activities.
 
 This means that using `Fonty` will require **no change** to your layout files.
 All you need to do is to initialize the library and specify what typeface
 you want to be used for regular text and what for boldfaced ones. That's it.
 
 ![Screenshot](img/shot.png)
 
 Download demo application APK from [releases section](https://github.com/MarcinOrlowski/fonty/releases). Source code in project's [app/](https://github.com/MarcinOrlowski/fonty/tree/master/app/src/main) module.

Features
========

 - Fast and lightweight
 - Simple API
 - Supports the following widgets:
   * TextView
   * EditText
   * Button
 - Handles navigation menu items too

 
Installation
============

 Edit your master `gradle.build` file and **add** `maven { url 'https://jitpack.io' }` to your current
 `repositories` block content (if you use other jitpack hosted libraries, then this step can be skipped):

    allprojects {
      repositories {
        maven { url 'https://jitpack.io' }
        }
    }

 Next, edit your **module**'s `build.gradle` and the following dependency:

    compile 'com.github.MarcinOrlowski:fonty:<VERSION>'

 For recent value of `<VERSION>` consult [library releases](https://github.com/MarcinOrlowski/fonty/releases)
 or jitpack badge: [![Release](https://jitpack.io/v/MarcinOrlowski/fonty.svg)](https://jitpack.io/#MarcinOrlowski/fonty)

Usage in code
=============

 Put your `TTF` font files into module's `asset/fonts` folder, which usually is:
 `<MODULE>/src/main/assets/fonts` folder, where `<MODULE>` equals `app`.
 
 Then add the following lines to your custom Application's class' `onCreate()`
 method (if you do not use own `Application` subclass, see demo app for how
 to make one and how it should be referenced form your `AndroidManifest.xml` file):

    Fonty.init(this)
	    .regularTypeface("Xenotron.ttf")
        .boldTypeface("XPED.ttf");
	}

 The above sets up `Xenotron.ttf` to be used whenever regular font is used (most cases)
 and `XPED.ttf` to be used if your UI elements sets `android:textStyle="bold"` attribute.

 If you prefer to have font files stored elsewhere than in assets' `fonts/` subfolder use `fontDir()`
 in your builder chain: 

    Fonty.init(this)
        .fontDir("my-fonts")
	    .regularTypeface("Xenotron.ttf")
        .boldTypeface("XPED.ttf");
	}

 and put your font files into `<MODULE>/src/main/assets/my-fonts` folder.

 This sets up font sustitution but we still need to apply it. It basically means
 that all you need to do is to call `setFonts()`.
  
 For `Activity` add this as last entry in your `onCreate()`:
 
    Fonty.setFonts(this);

 Same for `Fragments`, add to your `onCreateView()` implementation:
 
     Fonty.setfonts(view);
 
 where `view` is the `View` is what you just inflated.

 Not much complex is to use it with `RecyclerView`, edit your `onCreateViewHolder()` and
 add:
 
     Fonty.setFonts(view);
     
 where `view` stands for first argument passed to your `onCreateViewHolder()` method.
     
 If you are using [Android Data Binding library](https://developer.android.com/topic/libraries/data-binding/index.html),
 then you just need to call:
  
     Fonty.setFonts((ViewGroup)binding.getRoot());


Layout files
============

 Once `Fonty` is properly initialied and applied, all supported widgets will automatically
 be convinced to use fonts of your choice. By default font set by `setRegularFont()` applies
 and to switch to boldface, simply set `android:textStyle="bold"` to element of 
 choice:
 

        <TextView
            android:text="This will use regular typeface" 
            ... />

        <EditText
            android:text="This will use boldfaced typeface"
            android:textStyle="bold"
            ... />


Fonty and Toolbars
==================

 Unfortunately changing `Toolbar`/`ActionBar` title and subtitle fonts cannot be handled automatically
 `Fonty`. This is due to `Toolbar` internals as it simply have not instance of `TextView` 
 created unless title or subtitle is set, so there's nothing `Fonty` can manipulate in advance.
 So to change `Toolbar` fonts too we need some code (add this to your base activity class):
 
     private Toolbar mActivityActionBarToolbar;
    
     @Override
     public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        mActivityActionBarToolbar = toolbar;
     }
     
     @Override
     public void setTitle(CharSequence title) {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
           ab.setTitle(title);
           Fonty.setFonts(mActivityActionBarToolbar);
        }
     }
    
     public void setSubtitle(CharSequence subtitle) {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
           ab.setSubtitle(subtitle);
           Fonty.setFonts(mActivityActionBarToolbar);
        }
    }


Project support
===============
  
 `Fonty` is free software and you can use it fully free of charge in any of your projects, open source or 
 commercial, however if you feel it prevent you from reinventing the wheel, helped having your projects done or simply
 saved you time and money  then then feel free to donate to the project by sending some BTC to 
 `1LbfbmZ1KfSNNTGAEHtP63h7FPDEPTa3Yo`.
  
 ![BTC](img/btc.png)
  

Limitations
===========

 - You can only have `regular` and `bold` attributes supported (`italic` is
 not yet supported).
  

Contributing
============
  
 Please report any issue spotted using [GitHub's project tracker](https://github.com/MarcinOrlowski/fonty/issues).
   
 If you'd like to contribute to the this project, please [open new ticket](https://github.com/MarcinOrlowski/fonty/issues) 
 **before doing any work**. This will help us save your time in case I'd not be able to accept such changes. But if all is good and 
 clear then follow common routine:
  
  * fork the project
  * create new branch
  * do your changes
  * send pull request
 
  
License
=======
  
  * Written and copyrighted &copy;2013-2017 by Marcin Orlowski <mail (#) marcinorlowski (.) com>
  * `Fonty` is open-sourced library licensed under the Apache 2.0 license
