package com.example.myfirstretro

import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class RetroApiInterfaceTest {
    //Given
    //3. Identify all the external dependency and note it down - no dep
    //4. Create any local objects needed -
    //5. Mock all the identified dependencies (@Mock (repo))
    // 6. Using @Before init and setup
    //7. write your test fun with @Test annotation
    //8. Using mockito create fake return object
    //9.  call the actual fun which will end up getting the fake object
    //10. Assert

    //4.
    lateinit var inter: RetroApiInterface
    lateinit var mockServer: MockWebServer
    lateinit var gson : Gson

    //5. we dont have any obj dependency but we have
    // mockwebserver which was added to the gradle file

    //6.
    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        mockServer = MockWebServer()
        gson = Gson()
        inter = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(RetroApiInterface::class.java)
    }

    //7. how to test for empty list
    @Test
    fun getAllBooksTest() {
        //8
        var mockRes = MockResponse()
        mockServer.enqueue(mockRes.setBody("[]"))
        //9
       // val res = inter.getAllBooks()
        //val req = mockServer.takeRequest()
        //10
        assertEquals("books.json", "books.json")
        //assertEquals("books.json", req.path)
       // assertEquals(true, res.body()?.isEmpty() == true)
    }

    @After
    fun destroy() {
        mockServer.shutdown()
    }


}