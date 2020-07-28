package br.com.gustavobit.comprascrud.di

import br.com.gustavobit.comprascrud.domain.usecases.DeleteShopping
import br.com.gustavobit.comprascrud.domain.usecases.GetAllShopping
import br.com.gustavobit.comprascrud.domain.usecases.GetShopping
import br.com.gustavobit.comprascrud.domain.usecases.SaveShopping
import org.koin.core.module.Module
import org.koin.dsl.module

var createDomainModule: Module = module {
    single { GetShopping(repository = get()) }
    single { GetAllShopping(repository = get()) }
    single { SaveShopping(repository = get()) }
    single { DeleteShopping(repository = get()) }
}