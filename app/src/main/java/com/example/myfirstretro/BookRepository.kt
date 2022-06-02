package com.example.myfirstretro

import okhttp3.RequestBody

class BookRepository(val inter : RetroApiInterface) {
    suspend fun getAllBooks() = inter.getAllBooks()
    suspend fun getAllUsers() = inter.getAllUsers()
    suspend fun createUsers(requestBody: RequestBody) = inter.createUsers(requestBody)
}