package br.com.wcsm.gymtrack.application

import android.app.Application
import br.com.wcsm.gymtrack.di.NetworkKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)

            modules(
                NetworkKoinModule.module
            )
        }
    }
}