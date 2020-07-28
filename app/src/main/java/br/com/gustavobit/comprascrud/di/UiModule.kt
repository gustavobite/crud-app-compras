package br.com.gustavobit.comprascrud.di

import br.com.gustavobit.comprascrud.ui.controllers.ComprasListController
import br.com.gustavobit.comprascrud.ui.viewModels.ComprasListViewModel
import br.com.gustavobit.comprascrud.ui.viewModels.CompraViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

var createUiModule: Module = module {
    viewModel { ComprasListViewModel(buscarTodasComprasUseCase = get(), excluirCompra = get()) }
    viewModel { CompraViewModel(buscaCompra = get(), salvarCompra = get()) }
    factory { ComprasListController() }
}