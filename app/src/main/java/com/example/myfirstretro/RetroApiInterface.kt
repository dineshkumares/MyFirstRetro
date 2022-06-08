package com.example.myfirstretro

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.*

interface RetroApiInterface {


    //1. add a function to your ApiInterface getAllApiBooks() Observable<List<Books>>

    @GET("posts")
    fun getAllApiRecipe(): Single<List<Recipe>>

    //singleton
    @GET("books.json")
    suspend fun getAllBooks(): Response<List<Books>>

//    @PUT()

    @GET("/")
    suspend fun getAllRecipe(): Response<List<Users>>



    @POST("/posts")
    suspend fun createRecipe(@Body requestBody: RequestBody): Response<ResponseBody>

    @PUT("/posts/{id}")
    suspend fun updateRecipe(@Path("id") id:Int, @Body requestBody: RequestBody): Response<ResponseBody>

    @DELETE("/posts/{id}")
    suspend fun updateRecipe(@Path("id") id:Int): Response<ResponseBody>


    @GET("users")
    suspend fun getAllUsers(): Response<List<Users>>

    @POST("users")
    suspend fun createUsers(@Body requestBody: RequestBody): Response<ResponseBody>

//    @POST()
/// https://thapasabiran.github.io/Data/books.json
    companion object {
        var BASE_URL = "https://23d2-136-185-8-167.in.ngrok.io/ssblue18/"
        fun create() : RetroApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RetroApiInterface::class.java)
        }
    }


    //git init - only once in a project
    //git status
    //git add .
    // git commit -m ''
    // git log
    // git diff
    // git branch




}