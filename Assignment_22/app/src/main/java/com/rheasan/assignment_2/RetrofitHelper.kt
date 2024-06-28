package com.rheasan.assignment_2

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class CatFact(
    val data: Array<String>
)

interface Api {
    @GET("/")
    suspend fun getRandomCatFact(@Query("id") factId: Int) : Response<CatFact>
}

class RetrofitHelper {
    fun getInstance(): Retrofit {
        val baseUrl = "https://meowfacts.herokuapp.com/"
        return Retrofit.
            Builder().
            baseUrl(baseUrl).
            addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}