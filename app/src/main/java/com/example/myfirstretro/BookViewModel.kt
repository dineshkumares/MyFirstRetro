package com.example.myfirstretro

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import timber.log.Timber

class BookViewModel(val repo: BookRepository) : ViewModel() {

//    var bookList = MutableLiveData<List<Books>>()
//    var userList = MutableLiveData<List<Users>>()
//    var job: Job? =null

//    fun getAllBooks() {
//        CoroutineScope(Dispatchers.IO).launch {
//            var res = repo.getAllBooks()
//            if(res.isSuccessful) {
//                bookList.postValue(res.body())
//            }
//
//        }
//    }

    //What is external

    //3. Create a function getAllApiBooks Observable<List<Books>>
    //if you want to map do it here

    fun getAllApiRecipe():Observable<List<Recipe>> {
        //try catch
        //logger
        Timber.d("timber inside view model")
        Timber.e("timber inside view model error")
        return repo.getAllApiRecipe()
    }

    fun insertRecipe(recipe: Recipe) {
        repo.insertRecipe(recipe)
    }

//
//    fun getAllUsers() {
//        CoroutineScope(Dispatchers.IO).launch {
//            var res = repo.getAllUsers()
//            if(res.isSuccessful) {
//                userList.postValue(res.body())
//            }
//
//        }
//    }




    fun createUsers(requestBody: RequestBody) {
        CoroutineScope(Dispatchers.IO).launch {
            var res = repo.createUsers(requestBody)
            if(res.isSuccessful) {
                println("adsa")

//                if(isDebugMode == "dev") {
//                    Log.d("debug", "this is the response $res")
//                }
                println(res.body())
                // res now is json
                val gson = GsonBuilder().setPrettyPrinting().create()
                val pJson = gson.toJson(
                    JsonParser.parseString(
                        res.body()?.string()
                    )
                )
                println(pJson)
            }

        }
    }


//Types of Testing Frameworks

//    1. Junit + Mockito   --> Unit Testing
//     2. Espresso - UI Testing







}