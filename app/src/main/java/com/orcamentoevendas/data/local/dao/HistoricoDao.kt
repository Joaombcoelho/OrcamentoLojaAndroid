package com.orcamentoevendas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.orcamentoevendas.data.local.entity.ResultadoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoricoDao {

    @Insert
    suspend fun inserir(resultado: ResultadoEntity)

    @Query("SELECT * FROM resultado ORDER BY id DESC")
    fun listar(): Flow<List<ResultadoEntity>>

    @Query("DELETE FROM resultado")
    suspend fun limpar()
}