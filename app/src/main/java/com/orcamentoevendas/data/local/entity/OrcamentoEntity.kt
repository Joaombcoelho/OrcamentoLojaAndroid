package com.orcamentoevendas.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orcamentos")
data class OrcamentoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val clienteId: Long? = null,
    val pesoTotal: Double,
    val valorTotal: Double,
    val data: Long
)