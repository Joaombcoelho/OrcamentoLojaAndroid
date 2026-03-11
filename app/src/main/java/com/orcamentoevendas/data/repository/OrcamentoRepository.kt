package com.orcamentoevendas.data.repository

import com.orcamentoevendas.data.local.relation.OrcamentoComItens
import kotlinx.coroutines.flow.Flow
import com.orcamentoevendas.data.local.dao.OrcamentoDao
import com.orcamentoevendas.data.local.entity.OrcamentoEntity

class OrcamentoRepository(private val dao: OrcamentoDao) {

    fun listarHistorico(): Flow<List<OrcamentoEntity>> = dao.listarHistorico()

    suspend fun inserir(orcamento: OrcamentoEntity) = dao.inserir(orcamento)

    suspend fun limparHistorico() = dao.limparHistorico()
}