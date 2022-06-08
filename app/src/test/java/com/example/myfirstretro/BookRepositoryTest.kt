package com.example.myfirstretro

import io.reactivex.rxjava3.core.Flowable.fromArray
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.fromArray
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

//2. Add class annotation @RunWith
@RunWith(JUnit4::class)
class BookRepositoryTest {
    //Given
    //3. Identify all the external dependency and note it down
    //4. Create any local objects needed
    //5. Mock all the identified dependencies (@Mock (repo))
    // 6. Using @Before init and setup
    //7. write your test fun with @Test annotation
    //8. Using mokito create fake return object
    //9.  call the actual fun which will end up getting the fake object
    //10. Assert

    lateinit var repo: BookRepository

    @Mock
    lateinit var dao: RecipeDao

    @Mock
    lateinit var inter: RetroApiInterface

    @Before()
    fun setup(){
        MockitoAnnotations.openMocks(this)
        repo = BookRepository(inter, dao)
    }

    //this is a normal function returning observable
    @Test
    fun getAllRecipeFromApiTest() {

        var fakeList :List<Recipe> = (listOf<Recipe>(
            Recipe(234,"fromtest","","","","","")
        ))

        // mock the function call to the api
        Mockito.`when`(inter.getAllApiRecipe())
            .thenReturn(Single.just(fakeList))

        var result = repo.getAllRecipeFromApi()

        result.subscribeBy(
            onNext = {
                Assert.assertEquals(fakeList, it)
            },
            onError = { println("error :$it")}
        )


    }

    //Response object - normal list
    @Test
    fun getAllBooksTest(){
        var fakeList :List<Books> = (listOf<Books>(
            Books("234","fromtest",
                "","","","",
                20,"","")
        ))
        // mock the function call to the api
        Mockito.`when`(inter.getAllBooks())
            .thenReturn(Response.success(fakeList))

        var response = repo.getAllBooks()

        assertEquals(fakeList, response.body())

    }

    //coroutines with response object
    @Test
    fun getAllUsersTest(){
        runBlocking {
            var fakeList :List<Users> = (listOf<Users>(
                Users(213,"fromtest","","")
            ))
            // mock the function call to the api
            Mockito.`when`(inter.getAllUsers())
                .thenReturn(Response.success(fakeList))

            var response = repo.getAllUsers()

            assertEquals(fakeList, response.body())
        }
    }



    //When
    // Call the respective function on your mocked obj from the
    // function that needs to be tested
    //then( return dummy)
    // make the actual call --- which is verify
    //and assert
}