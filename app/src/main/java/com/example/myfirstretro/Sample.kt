package com.example.myfirstretro

object Sample {
    fun validRecipe(recipe: Recipe) : Boolean {
        if(recipe.id != null && recipe.title.isNotEmpty()) {
            return true
        }
        return false
    }
}