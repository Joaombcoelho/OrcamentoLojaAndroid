package com.orcamentoevendas.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.orcamentoevendas.data.local.entity.ItemOrcamentoEntity
import com.orcamentoevendas.data.local.entity.OrcamentoEntity

data class OrcamentoComItens(

    @Embedded
    val orcamento: OrcamentoEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "orcamentoId"
    )
    val itens: List<ItemOrcamentoEntity>
)