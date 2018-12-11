package android.sdei.com.basicappdagger

import android.app.Activity
import android.app.Application
import android.content.Context
import android.sdei.com.basicappdagger.app.AppComponent
import android.sdei.com.basicappdagger.app.AppModule
import android.sdei.com.basicappdagger.app.DaggerAppComponent
import android.sdei.com.basicappdagger.di.modules.FragmentModule
import android.sdei.com.basicappdagger.di.modules.PerActivityModule
import android.sdei.com.basicappdagger.di.subcomponents.PerActivityComponent
import android.sdei.com.basicappdagger.di.subcomponents.PerFragmentComponent
import android.sdei.com.basicappdagger.network.NetworkModule
import androidx.fragment.app.Fragment


import timber.log.Timber



class BaseApplication : Application() {

    private var appComponent: AppComponent? = null
    private var perActivityComponent: PerActivityComponent? = null
    private var perFragmentComponent: PerFragmentComponent? = null
    override fun onCreate() {
        super.onCreate()

        appComponent= createAppComponent()

        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return super.createStackElementTag(element) + ":" + element.lineNumber
            }
        })
    }
    fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
    }

    fun createPerActivityComponent(context: Context): PerActivityComponent {
        perActivityComponent = appComponent?.plusPerActivity(PerActivityModule(context as Activity))
        return perActivityComponent as PerActivityComponent
    }

    fun releaseActivityComponet() {
        perActivityComponent = null
    }

    fun createFragmentComponent(fragment: Fragment): PerFragmentComponent {
        perFragmentComponent = appComponent?.plusFragmentComponent(FragmentModule(fragment))
        return perFragmentComponent as PerFragmentComponent
    }

    fun releaseFragmentComponet() {
        perFragmentComponent = null
    }



    fun getAppComponent(): AppComponent? {
        return appComponent
    }

}