package com.example.myfirstretro

import android.util.Log
import timber.log.Timber

//8 create a class that extends Timber Tree
class ReleaseTree: Timber.Tree() {
    //9 - implement the log function
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        //10 if need only error and warning use the if condition below
//        if(priority == Log.ERROR || priority == Log.WARN){
//
//        }
    // send the error reports to crashlytics

    }
}