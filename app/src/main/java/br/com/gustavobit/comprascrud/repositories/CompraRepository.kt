package br.com.gustavobit.comprascrud.repositories

import br.com.gustavobit.comprascrud.domain.models.Compra

interface CompraRepository {

    suspend fun add(compra: Compra)

    suspend fun getAll(): List<Compra>

    suspend fun getById(id: String): Compra?

    suspend fun deleteById(id: String)

    suspend fun update(compra: Compra)

}