package br.com.gustavobit.comprascrud.remotes.services

import br.com.gustavobit.comprascrud.remotes.dtos.ResponseDTO
import br.com.gustavobit.comprascrud.remotes.dtos.CompraDTO
import retrofit2.Response
import retrofit2.http.*

interface CompraService {

    @POST("compra")
    suspend fun add(@Body compra: CompraDTO): ResponseDTO<CompraDTO>

    @GET("compra")
    suspend fun getAll(): ResponseDTO<List<CompraDTO>>

    @GET("compra/{id}")
    suspend fun getById(@Path("id") id: String): ResponseDTO<CompraDTO>?

    @DELETE("compra/{id}")
    suspend fun deleteById(@Path("id") id: String): Response<Unit>

    @PUT("compra")
    suspend fun update(@Body compra: CompraDTO): ResponseDTO<CompraDTO>

}