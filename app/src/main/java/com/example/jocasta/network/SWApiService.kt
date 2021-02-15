package com.example.jocasta.network

import com.example.jocasta.network.model.*
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

    @GET("api/{resource}")
    suspend fun getFilmSearchFor(@Path("resource") resourceName : String, @Query("search") query: String,
                                   @Query("page") page : Int) : Results<Film>

    @GET("api/{resource}")
    suspend fun getSpeciesSearchFor(@Path("resource") resourceName : String, @Query("search") query: String,
                                 @Query("page") page : Int) : Results<Species>

    @GET("api/{resource}")
    suspend fun getVehicleSearchFor(@Path("resource") resourceName : String, @Query("search") query: String,
                                    @Query("page") page : Int) : Results<Vehicle>

    @GET("api/{resource}")
    suspend fun getStarshipSearchFor(@Path("resource") resourceName : String, @Query("search") query: String,
                                    @Query("page") page : Int) : Results<Starship>

    @GET("api/films/{id}")
    suspend fun getFilmForId(@Path("id") id : String) : Film

    @GET("api/species/{id}")
    suspend fun getSpeciesForId(@Path("id") id : String) : Species

    @GET("api/vehicles/{id}")
    suspend fun getVehiclesForId(@Path("id") id : String) : Vehicle

    @GET("api/starships/{id}")
    suspend fun getStarshipForId(@Path("id") id : String) : Starship

    @GET("api/people/{id}")
    suspend fun getPeopleForId(@Path("id") id : String) : People
}