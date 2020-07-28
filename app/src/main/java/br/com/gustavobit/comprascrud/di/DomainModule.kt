package br.com.gustavobit.comprascrud.di

import br.com.gustavobit.comprascrud.domain.usecases.ExcluirCompra
import br.com.gustavobit.comprascrud.domain.usecases.BuscarTodasCompras
import br.com.gustavobit.comprascrud.domain.usecases.BuscaCompra
import br.com.gustavobit.comprascrud.domain.usecases.SalvarCompra
import org.koin.core.module.Module
import org.koin.dsl.module

var createDomainModule: Module = module {
    single { BuscaCompra(repository = get()) }
    single { BuscarTodasCompras(repository = get()) }
    single { SalvarCompra(repository = get()) }
    single { ExcluirCompra(repository = get()) }
}