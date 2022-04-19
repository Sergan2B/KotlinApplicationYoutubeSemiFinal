package kg.geektech.kotlinapplicationyoutube.di

import kg.geektech.kotlinapplicationyoutube.data.local.AppPreferences
import kg.geektech.kotlinapplicationyoutube.utils.NetworkStatusHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val connectionModule = module {
    single { NetworkStatusHelper(androidContext()) }
}
val prefsModule = module {
    single { AppPreferences(androidContext()) }
}