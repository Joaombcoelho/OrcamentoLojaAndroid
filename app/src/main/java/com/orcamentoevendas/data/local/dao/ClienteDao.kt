package com.orcamentoevendas.data.local.dao

import androidx.room.*
import com.orcamentoevendas.data.local.entity.ClienteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {

    @Insert
    suspend fun inserir(cliente: ClienteEntity)

    @Update
    suspend fun atualizar(cliente: ClienteEntity)

    @Delete
    suspend fun deletar(cliente: ClienteEntity)

    @Query("SELECT * FROM clientes ORDER BY nome")
    fun listarClientes(): Flow<List<ClienteEntity>>
}