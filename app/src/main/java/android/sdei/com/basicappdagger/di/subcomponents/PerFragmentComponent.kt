package android.sdei.com.basicappdagger.di.subcomponents



import android.sdei.com.basicappdagger.di.modules.FragmentModule
import android.sdei.com.basicappdagger.di.scopequalifier.FragmentScope
import dagger.Subcomponent

/**
 * Created by neerajm on 13/3/18.
 */
@FragmentScope
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface PerFragmentComponent{
 //   fun inject(homeFragment: HomeFragment)

}
