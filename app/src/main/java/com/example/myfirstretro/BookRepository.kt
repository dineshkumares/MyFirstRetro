package com.example.myfirstretro

import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody

class BookRepository(val inter : RetroApiInterface, private val dao: RecipeDao) {

//    var cachedUsers = emptyList<Recipe>()

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
//        return Observable.just(cachedUsers)
//            .mergeWith(getAllRecipeFromDb())
//            .mergeWith(getAllRecipeFromApi())
//            .doOnNext { cachedUsers = it }


//        return Observable.merge(
//            getAllRecipeFromApi(),
//            getAllRecipeFromDb()


    }

    fun getAllRecipeFromApi():Observable<List<Recipe>> {
        return inter.getAllApiRecipe()
            .toObservable()
//            .doOnNext { println("it is ${it.size}") }
    }

    fun getAllRecipeFromDb():Observable<List<Recipe>> {
        return dao.selectRecipes()
            .toObservable()
//            .filter { it.isNotEmpty() }
//            .doOnNext { println("it is ${it.size}") }
//            .doOnNext {
//                // here is where we can add logging so its useful for debugging
//            }
    }


    fun insertRecipe(recipe: Recipe) {
        dao.insertRecipe(recipe)
    }

}