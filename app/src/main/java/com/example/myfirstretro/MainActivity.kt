package com.example.myfirstretro

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import timber.log.Timber
import java.lang.ArithmeticException
import java.lang.Exception
import java.lang.NullPointerException
//import kotlin.NullPointerException

class MainActivity : AppCompatActivity() {

    lateinit var vm : BookViewModel
    var recipeList  = ArrayList<Recipe>()
    var isDebugMode = "prod"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("Inside Main activity create fun")
        //Logcat
        if(isDebugMode == "dev") {
            Log.d("debug", "inside mainactivity :username, password")
            Log.e("error", "asdsdada")
            Log.i("info", "sdfdsfds")
            Log.w("warn", "sdfsdfsdfs")
            Log.v("sdfsfs", "sdfdfs")
        }
        //Quality Code
        //1. Coding Standards ( space, UPPERCASE, camelCase, TitleCase, )
        //2. Doc on top of every file
        //3. //on top of every function one liner explaining what it is
        //4. Exceptions Handling
        //5. Logging & Crashlytics & Sonarqube
        //6. Clean Architecture -- adheres to a design pattern which is MVVM in our case
        //7. Write Unit Tests and make sure atleast you have 70% coverage
        //8. Remove all println in prod app
        //9. Do not log or print sensitive information
        //10. Do not let the Log in prod env (Production app)

        try {
            var num = 10 / 0
        } catch (e: ArithmeticException) {
            println("this is the error:: $e")
        }


        fun div(a:Int, b:Int) {
            println("asdsdsad:::: ${a/b}")
        }
        //Howe to write the same for ArrayIndex out of bound
        try {
            var a = arrayOf(1,2,34)//allAllUserInput() // user has not inputted anything
            println(a[10])
        } catch (e: Exception) {
            //Logging
            println("Error in accessing array :: $e")
        } finally {
            //remedies -  redirect to different activity or reset the obj
            println("error I get called always even when there is no exception")

        }

        //throw
        try {
            var testSubject = "hacker"  // scenario
            if (testSubject == "hacker") {
                throw  NullPointerException(" error Users is trying to hack")
            }
        } catch (e:Exception){
            println("caught the hacker::: $e")
        }




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
//                    addToRecipeList(it)
                    recipeList.addAll(it)
                },
                onComplete ={
                    println(recipeList) // adapter
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
        obj.put("id",110)
        obj.put("title","Chicken Soup")
        obj.put("yield","6 servings")
        obj.put("prepTime","10 mins")
        obj.put("totalTime","1hr 40 mins")
        obj.put("ingredients","1 whole chicken (about 4 pounds), cut into pieces (including back)")
        obj.put("directions","Step 1: Bring chicken, water, and 1 tablespoon salt to a boil in a large stockpot. Skim foam. Add onions, celery, and garlic. Reduce heat. Simmer, partially covered, for 30 minutes.")

        var myRecipe = Recipe(
            870,
            "Goat Soup",
            "6 servings",
            "10 mins",
            "1hr 40 mins",
            "1 whole chicken (about 4 pounds), cut into pieces (including back)",
            "Step 1: Bring chicken, water, and 1 tablespoon salt to a boil in a large stockpot. Skim foam. Add onions, celery, and garlic. Reduce heat. Simmer, partially covered, for 30 minutes."
        )

//        val objStr = obj.toString()
//        val requestBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), objStr)


//        val requestBody = objStr.toRequestBody("application/json".toMediaTypeOrNull())

        try {
            vm.insertRecipe(myRecipe)
        } catch (e: Exception) {
            println("This is Primary key exception:: $e")
            // send the $e to my Logger
        }

        // get all recipe --  api call
        // recipeList
        //recipeList[0]


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

    fun addToRecipeList(_recipeList: List<Recipe>) {
        println("every item is :::$_recipeList")
        recipeList.addAll(_recipeList)
    }
}

//3. Create retrofit instance -  same as your DB instance that you created as part of RoomDB
//4. Create retrofit interface for all our request methods -- get method
//5. Consume rest api end points (response -> Success , error )
//6. process and attach it to you view ( Recycler view)
//
//

//Timber
//1. Add Dependency
//2. plant
//3. DebugTree or ReleaseTree

