package com.alserdar.gads2020leaderboard.project_submission.network_post

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

private val submitRetrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl("https://docs.google.com/forms/d/e/")
    .build()


interface RetrofitPostData {




    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
     fun submitTheProject(
        @Field("entry.1824927963") emailAddress: String,
        @Field("entry.1877115667") name: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.284483984") linkToProject: String
    ): Call<Void>


    object GadsApi{

        val retrofitSubmitService : RetrofitPostData by lazy { submitRetrofit.create(RetrofitPostData::class.java) }
    }
}