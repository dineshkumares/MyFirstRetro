package com.example.myfirstretro

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SampleTest {

    @Test
    fun validRecipeTest() {
        val recipe = Recipe(223,"Test Recipe","","","","","")
        assertEquals(true, Sample.validRecipe(recipe))
    }

    @Test
    fun validRecipeEmptyTest() {
        val recipe = Recipe(562,"","","","","","")
        assertEquals(false, Sample.validRecipe(recipe))
    }
}