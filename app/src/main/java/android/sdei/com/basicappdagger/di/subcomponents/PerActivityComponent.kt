package android.sdei.com.basicappdagger.di.subcomponents


import android.sdei.com.basicappdagger.MainActivity
import android.sdei.com.basicappdagger.di.scopequalifier.ActivityScope
import android.sdei.com.basicappdagger.di.modules.PerActivityModule
import dagger.Subcomponent

/**
 * Created by neerajm on 13/3/18.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(PerActivityModule::class))
interface PerActivityComponent{

    fun inject(mainActivity: MainActivity)


}
