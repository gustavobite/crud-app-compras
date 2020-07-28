package br.com.gustavobit.comprascrud.domain.usecases

import br.com.gustavobit.comprascrud.common.Either
import br.com.gustavobit.comprascrud.common.Failure
import br.com.gustavobit.comprascrud.common.Success
import br.com.gustavobit.comprascrud.domain.models.Compra
import br.com.gustavobit.comprascrud.repositories.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetShopping(private val repository: ShoppingRepository) {

    suspend fun invoke(id: String): Either<Exception, Compra?> {
        return try {
            withContext(Dispatchers.IO) {
                val response = repository.getById(id = id)
                Success(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Failure(e)
        }
    }

}