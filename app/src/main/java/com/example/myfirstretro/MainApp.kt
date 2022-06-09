package com.example.myfirstretro

import android.app.Application
import timber.log.Timber
//2 create a class extends Application and add it to manifest
class MainApp : Application() {
    //3 ocreate is call implicitly
    override fun onCreate() {
        super.onCreate()
        //4 check for Buildconfig Debug
        if(BuildConfig.DEBUG) {
            //5 plant the timber
//            Timber.plant(Timber.DebugTree())

            Timber.plant(object : Timber.DebugTree(){
                //6 if you want meta data style the log like below
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format("Class ${super.createStackElementTag(element)} :" +
                            " Line ${element.lineNumber}  Method ${element.methodName}")
                }
            })
            //How your debug messages can be styled or even added more meta data

        } else{
            //7 -- if the app is in production
            // build a custom class ReleaseTree and then make it act as an interceptor which will
            //collect all your logs and puts it to crashlytics
            Timber.plant(ReleaseTree())
        }
    }
}