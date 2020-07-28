package br.com.gustavobit.comprascrud.ui.viewModels


data class ComprasViewData(
    var id: String?,
    var nome: String,
    var quantidade: Int,
    var marca: String,
    var validade: String
)