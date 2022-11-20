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

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast

import com.google.android.material.textfield.TextInputLayout
import com.marcinorlowski.fonty.Fonty

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.marcinorlowski.fonty.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    private var mHandler = Handler(Looper.getMainLooper())

    // Runnable that hides TIL's error messages
    private val mRunnable: Runnable = Runnable {
        val ids = intArrayOf(R.id.til1, R.id.til2)
        for (id in ids) {
            (findViewById<View>(id) as TextInputLayout).error = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewRoot = binding.root
        setContentView(viewRoot)

        binding.toolbar.title = getString(R.string.toolbar_title)
        binding.toolbar.subtitle = getString(R.string.toolbar_subtitle)
        setSupportActionBar(binding.toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

            actionBar.setDisplayHomeAsUpEnabled(true)
            mDrawerToggle = object :
                ActionBarDrawerToggle(this, drawerLayout, binding.toolbar, R.string.toggle_opend, R.string.toggle_closed) {
                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    invalidateOptionsMenu()
                }

                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    invalidateOptionsMenu()
                }
            }
            mDrawerToggle.isDrawerIndicatorEnabled = true

            drawerLayout.addDrawerListener(mDrawerToggle)
            mDrawerToggle.syncState()
        }

        binding.github.setOnClickListener { clickGithub() }
        binding.button.setOnClickListener { clickButton() }

        // apply our fonts now
        Fonty.setFonts(this)
    }

    fun clickGithub() {
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/MarcinOrlowski/fonty/")
                )
            )
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this@MainActivity, R.string.error_browser_failed, Toast.LENGTH_SHORT).show()
        }
    }

    fun clickButton() {
        val ids = intArrayOf(R.id.til1, R.id.til2)
        for (id in ids) {
            val til = findViewById<TextInputLayout>(id)
            til.isErrorEnabled = true
            til.error = getString(R.string.til_error)
        }

        mHandler.removeCallbacks(mRunnable)
        mHandler.postDelayed(mRunnable, (6 * 1000).toLong())
    }
}
