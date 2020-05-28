package com.example.homeworkmobile.di

import com.example.homeworkmobile.App
import com.example.homeworkmobile.di.module.AppModule
import com.example.homeworkmobile.di.module.BuildersViewModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        BuildersViewModule::class, AppModule::class]
)

interface ApplicationComponent {
    fun inject(app: App)
}