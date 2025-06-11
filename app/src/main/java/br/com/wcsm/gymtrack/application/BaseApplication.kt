package br.com.wcsm.gymtrack.application

import android.app.Application
import br.com.wcsm.gymtrack.di.FirebaseKoinModule
import br.com.wcsm.gymtrack.di.NetworkKoinModule
import br.com.wcsm.gymtrack.di.SignInKoinModule
import br.com.wcsm.gymtrack.di.SignUpKoinModule
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
                NetworkKoinModule.module,
                FirebaseKoinModule.module,
                SignUpKoinModule.module,
                SignInKoinModule.module
            )
        }
    }
}