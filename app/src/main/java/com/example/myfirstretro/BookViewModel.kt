package com.example.myfirstretro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BookViewModel(val repo: BookRepository) : ViewModel() {

    var bookList = MutableLiveData<List<Books>>()
    var job: Job? =null

    fun getAllBooks() {
        CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getAllBooks()
            if(res.isSuccessful) {
                bookList.postValue(res.body())
            }

        }
    }




}