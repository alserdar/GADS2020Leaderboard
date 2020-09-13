package com.alserdar.gads2020leaderboard.retrofit.url

import com.alserdar.gads2020leaderboard.retrofit.RetrofitClient
import com.alserdar.gads2020leaderboard.retrofit.the_interfaces.RetrofitForSkillIq

object TheRestUrlForSkillIq {
    private val BASE_URL = "https://gadsapi.herokuapp.com"

    val retrofitForSkillIq: RetrofitForSkillIq
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitForSkillIq::class.java)
}