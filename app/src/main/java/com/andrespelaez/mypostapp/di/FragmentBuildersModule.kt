package com.andrespelaez.mypostapp.di

import com.andrespelaez.mypostapp.ui.main.PlaceholderFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributePlaceholderFragment(): PlaceholderFragment
}
