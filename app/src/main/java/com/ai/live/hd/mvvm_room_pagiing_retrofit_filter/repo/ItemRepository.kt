package com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.repo

import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.data.Item
import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.net.ApiService
import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.room.ItemDao

class ItemRepository(private val itemDao: ItemDao, private val apiService: ApiService) {

    suspend fun fetchAndStoreItems(page: Int, size: Int) {
        val items = apiService.getItems(page, size)
        itemDao.insertItems(items)
    }

    fun getFilteredItems(searchQuery: String, limit: Int, offset: Int): List<Item> {
        return itemDao.getItems("%$searchQuery%", limit, offset)
    }
}
