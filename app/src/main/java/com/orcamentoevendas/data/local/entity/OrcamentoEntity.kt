package com.orcamentoevendas.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orcamentos")
data class OrcamentoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tipoPeca: String,

    val comprimento: Double,

    val dimensoes: String,

    val pesoTotal: Double,

    val valorTotal: Double,

    val data: Long
)