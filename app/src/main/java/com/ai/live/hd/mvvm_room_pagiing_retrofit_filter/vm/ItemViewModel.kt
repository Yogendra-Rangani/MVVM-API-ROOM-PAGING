package com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.data.Item
import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.net.RetrofitInstance
import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.repo.ItemRepository
import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.room.AppDatabase
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : ViewModel() {
    private val itemDao = AppDatabase.getDatabase(application).itemDao()
    private val apiService = RetrofitInstance.apiService
    private val repository = ItemRepository(itemDao, apiService)

    val itemsLiveData = MutableLiveData<List<Item>>()

    fun fetchItems(page: Int, size: Int) {
        viewModelScope.launch {
            repository.fetchAndStoreItems(page, size)
            loadItems("", size, (page - 1) * size)
        }
    }

    fun loadItems(searchQuery: String, limit: Int, offset: Int) {
        viewModelScope.launch {
            val items = repository.getFilteredItems(searchQuery, limit, offset)
            itemsLiveData.value = items
        }
    }

    fun filterItems(searchQuery: String) {
        loadItems(searchQuery, 10, 0)
    }
}
