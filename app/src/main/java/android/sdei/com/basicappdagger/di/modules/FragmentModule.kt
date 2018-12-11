package android.sdei.com.basicappdagger.di.modules

import android.content.Context
import androidx.fragment.app.Fragment
import android.sdei.com.basicappdagger.di.scopequalifier.ActivityContext
import android.sdei.com.basicappdagger.di.scopequalifier.FragmentScope
import android.sdei.com.basicappdagger.network.NetworkManager

import dagger.Module
import dagger.Provides


@Module
class FragmentModule(private val mFragment: Fragment) {

    @Provides
    @ActivityContext
    internal fun providesContext(): Context? {
        return mFragment.context
    }

    @Provides
    @FragmentScope
    internal fun providesNetWorkManager(): NetworkManager {
        return NetworkManager()
    }
}
