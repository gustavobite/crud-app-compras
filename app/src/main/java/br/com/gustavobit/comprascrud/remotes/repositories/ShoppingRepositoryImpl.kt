package br.com.gustavobit.comprascrud.remotes.repositories

import br.com.gustavobit.comprascrud.domain.models.Compra
import br.com.gustavobit.comprascrud.remotes.mappers.toDto
import br.com.gustavobit.comprascrud.remotes.mappers.toModel
import br.com.gustavobit.comprascrud.remotes.services.ShoppingService
import br.com.gustavobit.comprascrud.repositories.ShoppingRepository

class ShoppingRepositoryImpl(private val service: ShoppingService) : ShoppingRepository {

    override suspend fun add(compra: Compra) {
        service.add(compra = compra.toDto())
    }

    override suspend fun getAll(): List<Compra> {
        return service.getAll().data.map { it.toModel() }
    }

    override suspend fun getById(id: String): Compra? {
        return service.getById(id = id)?.data?.toModel()
    }

    override suspend fun deleteById(id: String) {
        service.deleteById(id = id)
    }

    override suspend fun update(compra: Compra) {
        service.update(compra = compra.toDto())
    }
}