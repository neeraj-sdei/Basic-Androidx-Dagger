package android.sdei.com.basicappdagger.app

import android.sdei.com.basicappdagger.di.modules.FragmentModule
import android.sdei.com.basicappdagger.di.modules.PerActivityModule
import android.sdei.com.basicappdagger.di.subcomponents.PerFragmentComponent
import android.sdei.com.basicappdagger.di.subcomponents.PerActivityComponent
import android.sdei.com.basicappdagger.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {

   fun plusPerActivity(perActivityModule: PerActivityModule): PerActivityComponent
    fun plusFragmentComponent(fragmentModule: FragmentModule): PerFragmentComponent

}
