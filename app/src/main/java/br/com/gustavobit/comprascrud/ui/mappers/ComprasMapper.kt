package br.com.gustavobit.comprascrud.ui.mappers

import br.com.gustavobit.comprascrud.domain.models.Compra
import br.com.gustavobit.comprascrud.ui.viewModels.ComprasViewData

fun ComprasViewData.toModel() = Compra(
    id = id,
    nome = nome,
    quantidade = quantidade,
    marca = marca,
    validade = validade
)

fun Compra.toViewData() =
    ComprasViewData(
        id = id,
        nome = nome,
        quantidade = quantidade,
        marca = marca,
        validade = validade
    )