package com.example.jocasta.network

import com.example.jocasta.network.model.People
import com.example.jocasta.network.model.Planet
import com.example.jocasta.network.model.Resources
import com.example.jocasta.network.model.Results
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SWApiService {
    companion object{
        private const val BASE_URL = "https://swapi.dev/"

        fun create() : SWApiService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC
            val okHttpClient = OkHttpClient.Builder().addInterceptor(logger).build()

            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

            return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(
                MoshiConverterFactory.create(moshi)).build().create(SWApiService::class.java)
        }
    }

    @GET("api")
    suspend fun getAllResources() : Resources

    @GET("api/{resource}")
    suspend fun getPeopleSearchFor(@Path("resource") resourceName : String, @Query("search") query: String,
                                   @Query("page") page : Int) : Results<People>

    @GET("api/{resource}")
    suspend fun getPlanetSearchFor(@Path("resource") resourceName : String, @Query("search") query: String,
                                   @Query("page") page : Int) : Results<Planet>
}