package com.example.bonusapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bonusapp.entity.Bonus

@Dao
interface BonusDao {
    @Insert
    fun insertBonus(vararg bonusDao: Bonus)

    @Update
    fun updateBonus(vararg bonusDao: Bonus)

    @Delete
    fun deleteWords(vararg bonusDao : Bonus)

    @Query("select * from Bonus")
    fun queryAllBonus():List<Bonus>
}