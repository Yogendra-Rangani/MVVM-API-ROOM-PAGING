package com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String
)
