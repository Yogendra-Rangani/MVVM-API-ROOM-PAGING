package com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.data.Item
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<Item>)

    @Query("SELECT * FROM items WHERE name LIKE :searchQuery ORDER BY id LIMIT :limit OFFSET :offset")
    fun getItems(searchQuery: String, limit: Int, offset: Int): List<Item>
}


