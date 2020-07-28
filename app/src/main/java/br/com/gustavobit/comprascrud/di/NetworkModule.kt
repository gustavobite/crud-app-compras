package br.com.gustavobit.comprascrud.di

import android.content.Context
import br.com.gustavobit.comprascrud.network.RetrofitFactory
import br.com.gustavobit.comprascrud.remotes.services.ShoppingService
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

const val BASE_URL = "http://192.168.0.12:8080/api/"

val createNetworkModule: Module = module {
    single {
        createRetrofit(
            baseUrl = BASE_URL,
            context = get()
        ).create(ShoppingService::class.java)
    }
}

private fun createRetrofit(
    baseUrl: String,
    context: Context
): Retrofit {
    return RetrofitFactory.createRetrofit(baseUrl, context)
}