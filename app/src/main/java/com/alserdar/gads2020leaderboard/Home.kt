package com.alserdar.gads2020leaderboard

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.alserdar.gads2020leaderboard.project_submission.ProjectSubmission
import com.alserdar.gads2020leaderboard.ui.main.SectionsPagerAdapter

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val submit: Button = findViewById(R.id.submit)

        submit.setOnClickListener(View.OnClickListener {

            val i = Intent(this , ProjectSubmission::class.java)
            startActivity(i)
        })

    }
}