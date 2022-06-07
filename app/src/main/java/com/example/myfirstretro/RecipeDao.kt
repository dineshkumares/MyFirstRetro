package com.example.myfirstretro

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable


@Dao
interface RecipeDao {
    @Query("select * from recipe")
    fun selectRecipes(): Observable<List<Recipe>>
}