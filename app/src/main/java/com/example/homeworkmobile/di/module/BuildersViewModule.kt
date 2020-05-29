package com.example.homeworkmobile.di.module

import com.example.homeworkmobile.views.login.MainActivity
import com.example.homeworkmobile.views.personalArea.PersonalAreaActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersViewModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
    @ContributesAndroidInjector
    abstract fun contributePersonalAreaActivity(): PersonalAreaActivity
}
