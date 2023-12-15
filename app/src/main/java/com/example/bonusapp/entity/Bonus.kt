package com.example.bonusapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bonus(var title:String,var description:String,var time:String) {
    @PrimaryKey(autoGenerate = true)
    var  id:Int = 0;
}