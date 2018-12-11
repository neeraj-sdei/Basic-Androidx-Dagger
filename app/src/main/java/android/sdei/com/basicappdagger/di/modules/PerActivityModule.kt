package android.sdei.com.basicappdagger.di.modules

import android.app.Activity
import android.content.Context
import android.sdei.com.basicappdagger.di.scopequalifier.ActivityContext
import android.sdei.com.basicappdagger.di.scopequalifier.ActivityScope
import android.sdei.com.basicappdagger.network.NetworkManager
import dagger.Module
import dagger.Provides

@Module
class PerActivityModule constructor(activity: Activity) {
    private val mActivity: Activity

   init {
        this.mActivity = activity

    }

    @Provides
    @ActivityScope
    @ActivityContext
    internal fun providesActivityContext():

            Context {
        return mActivity
    }


    @Provides
    @ActivityScope
    internal fun providesNetWorkManager(@ActivityContext context: Context): NetworkManager {
        return NetworkManager()
    }
}