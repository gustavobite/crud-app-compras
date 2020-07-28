package br.com.gustavobit.comprascrud.remotes.dtos


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompraDTO(
    val id: String?,
    val nome: String,
    val quantidade: Int,
    val marca: String,
    val validade: String
)