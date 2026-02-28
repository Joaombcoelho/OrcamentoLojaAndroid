package com.example.oramenteevendas.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historico")
data class ResultadoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val comprimento: Double,
    val largura: Double,
    val valorUnitario: Double,
    val resultado: Double
)