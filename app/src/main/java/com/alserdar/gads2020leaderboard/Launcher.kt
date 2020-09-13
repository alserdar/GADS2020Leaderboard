package com.alserdar.gads2020leaderboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Launcher : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        Handler().postDelayed(
            {

                val i = Intent(this , Home::class.java)
                startActivity(i)
            }, 2300)
    }
}