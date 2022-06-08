package com.example.myfirstretro

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


@Dao
interface RecipeDao {
    @Query("select * from recipe")
    fun selectRecipes(): Single<List<Recipe>>

    @Insert
    fun insertRecipe(recipe: Recipe)

}