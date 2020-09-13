package com.alserdar.gads2020leaderboard.retrofit.the_interfaces

import com.alserdar.gads2020leaderboard.model.SkillIqItem
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitForSkillIq {
    @GET("/api/skilliq")
    fun getSkillList(): Call<MutableList<SkillIqItem>>
}