package com.example.myfirstretro

import androidx.core.view.ViewParentCompat
import com.google.gson.Gson
import io.reactivex.rxjava3.kotlin.subscribeBy
import okhttp3.HttpUrl
import okhttp3.internal.applyConnectionSpec
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.verify
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
//        mockServer.start()
//        val baseUrl: HttpUrl = mockServer.url("/")
        gson = Gson()
        inter = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(RetroApiInterface::class.java)
//        mockServer.start()

//        val dispatcher = SimpleDispatcher()
//        mockServer.dispatcher = dispatcher

    }


    //7. how to test for empty list
    @Test
    fun getAllBooksTest() {
        //8





        var mockRes = MockResponse()
//            .addHeader("Content-Type", "application/json; charset=utf-8")
//            .addHeader("Cache-Control", "no-cache")
//            .setBody("{}");
        mockServer.enqueue(mockRes)
        var result = inter.getAllApiRecipe()
//            .toObservable()


        result.subscribeBy {
            it
        }

        val path = mockServer.takeRequest()
        println(path.path)
        assertEquals("/posts", path.path)

//        assertEquals("application/json; charset=utf-8", path.getHeader("Content-Type"))

//
//
////        mockServer.setDispatcher(new Dispatcher() {
////            @Override
////            public MockResponse dispatch(RecordedRequest request)
////            throws InterruptedException {
////                return response; // this could have been more sophisticated
////            }
////        });
//        //9
//        var fakeList :List<Recipe> = (listOf<Recipe>(
//            Recipe(234,"fromtest","","","","","")
//        ))
//        val req = mockServer.takeRequest()
////        verify(req)
//        mockServer.enqueue(mockRes)
//        inter.getAllApiRecipe()
//        assertEquals("application/json; charset=utf-8", req.getHeader("Content-Type"))
//        val res = inter.getAllApiRecipe()
//        assertEquals(fakeList, res.subscribeBy {
//            it
//        })
//        assertEquals("GET", req.method)
//        assertEquals("posts", req.path)
//        //10
//        assertEquals("books.json", "books.json")
        //assertEquals("books.json", req.path)
       // assertEquals(true, res.body()?.isEmpty() == true)
    }

    @After
    fun destroy() {
        mockServer.shutdown()
    }


}

class SimpleDispatcher: Dispatcher() {
    @Override
    override fun dispatch(request: RecordedRequest): MockResponse {
        if (request.method == "GET"){
            return MockResponse().setResponseCode(200).setBody("""{ "message": "It was a GET request" }""")
        } else if (request.method == "POST") {
            return MockResponse().setResponseCode(200).setBody("""{ "message": "It was a POST request" }""")
        }
        return MockResponse().setResponseCode(200)
    }
}