package com.orcamentoevendas.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.orcamentoevendas.data.local.entity.OrcamentoEntity

@Dao
interface OrcamentoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(orcamento: OrcamentoEntity)

    @Query("SELECT * FROM orcamentos ORDER BY data DESC")
    fun listarHistorico(): Flow<List<OrcamentoEntity>>

    @Query("DELETE FROM orcamentos")
    suspend fun limparHistorico()
}