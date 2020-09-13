package com.alserdar.gads2020leaderboard.retrofit.url

import com.alserdar.gads2020leaderboard.retrofit.the_interfaces.RetrofitForHours
import com.alserdar.gads2020leaderboard.retrofit.RetrofitClient

object TheRestUrlForHours {
    private val BASE_URL = "https://gadsapi.herokuapp.com"

    val retrofitForHours: RetrofitForHours
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitForHours::class.java)
}