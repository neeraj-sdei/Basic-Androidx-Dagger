package android.sdei.com.basicappdagger.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.sdei.com.basicappdagger.di.scopequalifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(application: Application) {
    private var context: Context?=null
    val PREF_NAME = "InviteGreenPref"
    private val PRIVATE_MODE = 0


    init {
        context = application
    }

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext(): Context? {
        return context
    }


    @Provides
    @Singleton
    internal fun provideResources(@ApplicationContext context: Context): Resources {
        return context.resources
    }

    @Provides
    @Singleton
    internal fun sharedPrefData(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
    }



}