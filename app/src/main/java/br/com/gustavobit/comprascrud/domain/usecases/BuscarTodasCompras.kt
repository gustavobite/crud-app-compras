package br.com.gustavobit.comprascrud.domain.usecases

import br.com.gustavobit.comprascrud.common.Either
import br.com.gustavobit.comprascrud.common.Failure
import br.com.gustavobit.comprascrud.common.Success
import br.com.gustavobit.comprascrud.domain.models.Compra
import br.com.gustavobit.comprascrud.repositories.CompraRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BuscarTodasCompras(private val repository: CompraRepository) {

    suspend fun invoke(): Either<Exception, List<Compra>> {
        return try {
            withContext(Dispatchers.IO) {
                val response = repository.getAll()
                Success(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Failure(e)
        }
    }

}