package com.example.filedownloadtask.di.module

import com.example.filedownloadtask.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuilderModule {
 @ContributesAndroidInjector()
 abstract fun contributeMainActivity(): MainActivity
 }