package br.com.gustavobit.comprascrud.domain.usecases

import br.com.gustavobit.comprascrud.common.Either
import br.com.gustavobit.comprascrud.common.Failure
import br.com.gustavobit.comprascrud.common.Success
import br.com.gustavobit.comprascrud.domain.models.Compra
import br.com.gustavobit.comprascrud.repositories.CompraRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SalvarCompra(private val repository: CompraRepository) {

    suspend fun invoke(compra: Compra): Either<Exception, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                val response =
                    if (compra.id.isNullOrEmpty()) {
                        repository.update(compra = compra)
                    } else {
                        repository.add(compra = compra)
                    }
                Success(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Failure(e)
        }
    }

}