package com.github.ludoviccarlu.pokemon.presentation.home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.github.ludoviccarlu.pokemon.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(5000)
                    val intent = Intent(baseContext, HomeActivity::class.java)
                    startActivity(intent)

                } catch (e : Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
