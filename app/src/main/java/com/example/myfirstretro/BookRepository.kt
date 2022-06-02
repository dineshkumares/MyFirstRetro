package com.example.myfirstretro

class BookRepository(val inter : RetroApiInterface) {
    suspend fun getAllBooks() = inter.getAllBooks()
}