package com.example.myfirstretro

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter

//1 -  annotation       //2 - entities

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
//3 - abstract and extend
abstract class AppDatabase : RoomDatabase(){
    //4 abstract and return Dao
    abstract fun recipeDao(): RecipeDao
    //5 - Singleton
    companion object {
        var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if(INSTANCE == null) {
                //6 - we are acquiring an instance of RoomDB builder
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "recipe.db")
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

