package br.com.gustavobit.comprascrud

import android.app.Application
import br.com.gustavobit.comprascrud.di.createDomainModule
import br.com.gustavobit.comprascrud.di.createNetworkModule
import br.com.gustavobit.comprascrud.di.createRepositoryModule
import br.com.gustavobit.comprascrud.di.createUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ComprasApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ComprasApplication)
            modules(
                createNetworkModule,
                createRepositoryModule,
                createDomainModule,
                createUiModule
            )
        }

    }

}