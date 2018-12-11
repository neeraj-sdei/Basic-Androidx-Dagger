package android.sdei.com.basicappdagger.di.scopequalifier

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import javax.inject.Scope

/**
 * Created by neerajm on 13/3/18.
 */
@Scope
@kotlin.annotation.Retention(value = AnnotationRetention.RUNTIME)
annotation class FragmentScope
