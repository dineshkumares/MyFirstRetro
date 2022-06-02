package com.example.myfirstretro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class BookViewModel(val repo: BookRepository) : ViewModel() {

    var bookList = MutableLiveData<List<Books>>()
    var userList = MutableLiveData<List<Users>>()
    var job: Job? =null

    fun getAllBooks() {
        CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getAllBooks()
            if(res.isSuccessful) {
                bookList.postValue(res.body())
            }

        }
    }


    fun getAllUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getAllUsers()
            if(res.isSuccessful) {
                userList.postValue(res.body())
            }

        }
    }

    fun createUsers(requestBody: RequestBody) {
        CoroutineScope(Dispatchers.IO).launch {
            var res = repo.createUsers(requestBody)
            if(res.isSuccessful) {
                println("adsa")
                println(res.body())
                // res now is json
                val gson = GsonBuilder().setPrettyPrinting().create()
                val pJson = gson.toJson(
                    JsonParser.parseString(
                        res.body()?.toString()
                    )
                )
                println(pJson)
            }

        }
    }









}