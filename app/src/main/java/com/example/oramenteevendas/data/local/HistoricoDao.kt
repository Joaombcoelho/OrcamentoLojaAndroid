package com.example.oramenteevendas.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoricoDao {

    @Insert
    suspend fun inserir(item: ResultadoEntity)

    @Query("SELECT * FROM historico ORDER BY id DESC")
    fun listar(): Flow<List<ResultadoEntity>>

    @Query("DELETE FROM historico")
    suspend fun limpar()
}