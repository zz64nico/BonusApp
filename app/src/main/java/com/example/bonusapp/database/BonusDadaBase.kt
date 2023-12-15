package com.example.bonusapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bonusapp.dao.BonusDao
import com.example.bonusapp.entity.Bonus

@Database(entities = [Bonus::class], version = 1, exportSchema = false)
abstract class BonusDadaBase:RoomDatabase() {
    abstract fun getBonusDao(): BonusDao

    companion object{
        private var instance:BonusDadaBase? = null

        @Synchronized
        fun getDatabase(context: Context):BonusDadaBase{
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                BonusDadaBase::class.java,
                "Bonus.db")
                .allowMainThreadQueries()
                .build().apply {
                    instance = this
                }
        }
    }
}