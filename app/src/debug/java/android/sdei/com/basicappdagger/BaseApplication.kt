package android.sdei.com.basicappdagger

import android.app.Activity
import android.app.Application
import android.content.Context


import timber.log.Timber



class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()


        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return super.createStackElementTag(element) + ":" + element.lineNumber
            }
        })
    }


}