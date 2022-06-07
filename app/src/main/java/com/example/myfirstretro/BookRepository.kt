package com.example.myfirstretro

import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody

class BookRepository(val inter : RetroApiInterface, private val dao: RecipeDao) {
    suspend fun getAllBooks() = inter.getAllBooks()
    suspend fun getAllUsers() = inter.getAllUsers()
    suspend fun createUsers(requestBody: RequestBody) = inter.createUsers(requestBody)

    //2. create a function getAllApiBooks : Observable<List<Books>> return
    fun getAllApiRecipe(): Observable<List<Recipe>> {
//        return inter.getAllApiRecipe()
        return Observable.concatArray(
            getAllRecipeFromApi(),
            getAllRecipeFromDb()
        )
    }

    fun getAllRecipeFromApi():Observable<List<Recipe>> {
        return inter.getAllApiRecipe()
    }

    fun getAllRecipeFromDb():Observable<List<Recipe>> {
        return dao.selectRecipes()
    }

}