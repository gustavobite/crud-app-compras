package br.com.gustavobit.comprascrud.di

import br.com.gustavobit.comprascrud.remotes.repositories.CompraRepositoryImpl
import br.com.gustavobit.comprascrud.repositories.CompraRepository
import org.koin.core.module.Module
import org.koin.dsl.module

var createRepositoryModule: Module = module {
    factory<CompraRepository> { CompraRepositoryImpl(service = get()) }
}