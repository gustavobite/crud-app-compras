package br.com.gustavobit.comprascrud.domain.models


data class Compra(
    val id: String?,
    val nome: String,
    val quantidade: Int,
    val marca: String,
    val validade: String
)