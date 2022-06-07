package com.example.myfirstretro

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey
    val id: Int,
    val title: String,
    val yield: String,
    val prepTime: String,
    val totalTime: String,
    val ingredients: String,
    val directions: String)