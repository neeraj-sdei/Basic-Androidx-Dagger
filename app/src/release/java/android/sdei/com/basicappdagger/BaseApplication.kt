package android.sdei.com.basicappdagger

import android.app.Application
import com.neerajm.invitegreen.ReleaseTree


import timber.log.Timber


class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()


        Timber.plant(ReleaseTree())

    }

}