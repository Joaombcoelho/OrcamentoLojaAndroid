package com.example.oramenteevendas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ResultadoEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultadoDao(): HistoricoDao
}