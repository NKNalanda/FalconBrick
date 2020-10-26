package com.nknalanda.falconbrick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.nknalanda.falconbrick.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private var binding: ActivitySplashScreenBinding? = null
    private val SPLASH_TIMEOUT: Long = 2000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        Handler().postDelayed({

            // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this, SearchActivity::class.java))

            // Close this activity
            finish()
        }, SPLASH_TIMEOUT)
    }
}