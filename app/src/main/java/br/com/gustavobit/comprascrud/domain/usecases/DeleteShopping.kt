package br.com.gustavobit.comprascrud.domain.usecases

import br.com.gustavobit.comprascrud.common.Either
import br.com.gustavobit.comprascrud.common.Failure
import br.com.gustavobit.comprascrud.common.Success
import br.com.gustavobit.comprascrud.repositories.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteShopping(private val repository: ShoppingRepository) {

    suspend fun invoke(id: String): Either<Exception, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                repository.deleteById(id = id)
                Success(Unit)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Failure(e)
        }
    }

}