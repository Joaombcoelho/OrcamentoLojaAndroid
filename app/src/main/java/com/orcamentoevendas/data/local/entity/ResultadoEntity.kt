package com.orcamentoevendas.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resultado")
data class ResultadoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val valorTotal: Double,
    val pesoTotal: Double,
    val data: String
)