package com.alserdar.gads2020leaderboard.model


import com.google.gson.annotations.SerializedName

data class HoursItem(
    @SerializedName("badgeUrl")
    val badgeUrl: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("hours")
    val hours: Int,
    @SerializedName("name")
    val name: String
)