package kg.geektech

import android.app.Application
import kg.geektech.kotlinapplicationyoutube.di.koinModules
import kg.geektech.kotlinapplicationyoutube.repository.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
            modules(koinModules)
        }
    }
}