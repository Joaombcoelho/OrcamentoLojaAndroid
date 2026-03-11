package com.orcamentoevendas.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "itens_orcamento",
    foreignKeys = [
        ForeignKey(
            entity = OrcamentoEntity::class,
            parentColumns = ["id"],
            childColumns = ["orcamentoId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ItemOrcamentoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val orcamentoId: Long,

    val descricao: String,

    val material: String,

    val quantidade: Int,

    val peso: Double,

    val valor: Double
)