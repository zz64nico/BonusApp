package com.example.bonusapp.adapter

import com.example.bonusapp.entity.Bonus

interface IBonusListener {
    fun onDeleteBonus(bonus: Bonus)
}