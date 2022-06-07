package com.example.myfirstretro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class MainActivity : AppCompatActivity() {

    lateinit var vm : BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intr = RetroApiInterface.create()
        val dao = AppDatabase.getInstance(this)?.recipeDao()!!
        val repo = BookRepository(intr, dao)
        vm = BookViewModel(repo)
        println("Inside Main")
//        vm.bookList.observe(this) {
//            //update your view
//                // attach to rcycler vieww adapter
////                adapter.setBookList(it)
//            println(it) // this will print the list of books
//
//
//        }

//        vm.getAllBooks()

        vm.getAllApiRecipe()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    item -> println("My Observable user list $item")
                },
                onError = {e -> println("this is the error $e")}
            )

        //ApiInterface 1. add a function to your ApiInterface getAllApiBooks() Observable<List<Books>>
        //Repo 2. create a function getAllApiBooks : Observable<List<Books>> return
        //VM 3. Create a function getAllApiBooks Observable<List<Books>>
        //if you want to map do it here
        //View 4. use the view model and call getAllApiBooks
        // subscribeon Schedulers.io
        //observeon
        //.subscibe

        //we need to call create books here
// 1. Form with all the fields from the entity  - done
//2. when user submits it get all the filled data -
//  create a jsonObject/Gson using the data filled
//3. convert that json to string .toString
//4. convert the string to .toRequestBody
        val obj = JSONObject()
        obj.put("id",11)
        obj.put("name","Dinesh")
        obj.put("username","dinesh")
        obj.put("email","dinesh@d.com")

        val objStr = obj.toString()
//        val requestBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), objStr)


        val requestBody = objStr.toRequestBody("application/json".toMediaTypeOrNull())

//        vm.createUsers(requestBody)
//        val api = RetroApiInterface.retro.create().getAllBooks()
//
//        api.enqueue(object : Callback<List<Books>>{
//            override fun onResponse(call: Call<List<Books>>, response: Response<List<Books>>) {
//                println(response.body())
//            }
//
//            override fun onFailure(call: Call<List<Books>>, t: Throwable) {
//            // this will be called up on error response from the server
//            }
//        })

    }
}

//3. Create retrofit instance -  same as your DB instance that you created as part of RoomDB
//4. Create retrofit interface for all our request methods -- get method
//5. Consume rest api end points (response -> Success , error )
//6. process and attach it to you view ( Recycler view)
//
//