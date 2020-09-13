package com.alserdar.gads2020leaderboard.retrofit.the_interfaces

import com.alserdar.gads2020leaderboard.model.HoursItem
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitForHours {
    @GET("/api/hours")
    fun getHoursList(): Call<MutableList<HoursItem>>
}