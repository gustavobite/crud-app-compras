package br.com.gustavobit.comprascrud.remotes.mappers

import br.com.gustavobit.comprascrud.domain.models.Compra
import br.com.gustavobit.comprascrud.remotes.dtos.CompraDTO

fun CompraDTO.toModel() = Compra(
    id = id,
    nome = nome,
    quantidade = quantidade,
    marca = marca,
    validade = validade
)

fun Compra.toDto() = CompraDTO(
    id = id,
    nome = nome,
    quantidade = quantidade,
    marca = marca,
    validade = validade
)