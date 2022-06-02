package com.example.myfirstretro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var vm : BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intr = RetroApiInterface.create()
        val repo = BookRepository(intr)
        vm = BookViewModel(repo)

        vm.bookList.observe(this) {
            //update your view
                // attach to rcycler vieww adapter
//                adapter.setBookList(it)
            println(it) // this will print the list of books


        }

        vm.getAllBooks()

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