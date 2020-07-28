package br.com.gustavobit.comprascrud.di

import br.com.gustavobit.comprascrud.remotes.repositories.ShoppingRepositoryImpl
import br.com.gustavobit.comprascrud.repositories.ShoppingRepository
import org.koin.core.module.Module
import org.koin.dsl.module

var createRepositoryModule: Module = module {
    factory<ShoppingRepository> { ShoppingRepositoryImpl(service = get()) }
}