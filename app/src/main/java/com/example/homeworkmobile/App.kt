package com.example.homeworkmobile

import android.app.Activity
import android.app.Application
import com.example.homeworkmobile.di.DaggerApplicationComponent
import com.example.homeworkmobile.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

@Suppress("DEPRECATION")
class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

            DaggerApplicationComponent.builder().appModule(AppModule(this))
//            .restApiModule(RestApiModule(Constants.BASE_URL_REST_API))
            .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}