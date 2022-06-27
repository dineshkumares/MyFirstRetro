package com.example.myfirstretro

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.rxjava3.parallel.ParallelFlowable.from
import io.reactivex.rxjava3.schedulers.Schedulers.from
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


//@RunWith(MockitoJUnitRunner::class)
@RunWith(JUnit4::class)
class BookViewModelTest {
//    @Mock
//    @Before
//    @Test
//    @After
    lateinit var vm: BookViewModel
//    lateinit var repo: BookRepository
    lateinit var recipeList: Observer<List<Recipe>>

//    @Mock
//    lateinit var dao: RecipeDao
//
//    @Mock
//    lateinit var inter: RetroApiInterface

    @Mock
    lateinit var repo: BookRepository

    @Before
    fun setUp(){
//        MockitoAnnotations.initMocks(this)
        MockitoAnnotations.openMocks(this)
//        repo = BookRepository(inter, dao)
        vm = BookViewModel(repo)
//        setupObservers()
    }
    private fun setupObservers(){
        recipeList = mock(Observer::class.java) as Observer<List<Recipe>>
    }

    @Test
    fun getAllApiRecipeTest(){
        var fakeList :List<Recipe> = (listOf<Recipe>(
            Recipe(234,"fromtest","","","","","")
        ))

        Mockito.`when`(repo.getAllApiRecipe())
            .thenReturn(Observable.fromArray(fakeList))


        val result = vm.getAllApiRecipe()
        var actuals = listOf<Recipe>()

        result.test()
            .assertResult(
                listOf<Recipe>(
                    Recipe(234,"fromtest","","","","","")
                )
            )

//        result.doOnNext {
//            actuals = it
//            assertEquals("sadsdaa", listOf<Recipe>(
//                Recipe(234,"fromtest","","","","","")
//            ))
//        }







//        result.subscribeBy(
//            onNext = {
//                assertEquals(listOf<Recipe>(
//                    Recipe(234,"fromtest","","","","","")
//                ),"sdfdsfss")
//        },
//            onError = { assert(false)}
//        )

//        val result = vm.getAllApiRecipe().subscribe(recipeList)
//
//        assertEquals(listOf<Recipe>(
//            Recipe(234,"fromtest","","","","","")
//        ),result)

//
    }

    @Test
    fun `given repository when calling recipelist then list is empty and assert its empty`() {

    }

    //1. add all the dependency required for mockito
    //2. Add class annotation @RunWith
    //Given
    //3. Identify all the external dependency and note it down
    //4. Create any local objects needed
    //5. Mock all the identified dependencies (@Mock (repo), @Before)
    //When
    // Call the respective function on your mocked obj from the
        // function that needs to be tested
    //then( return dummy)
    // make the actual call --- which is verify
    //and assert




}