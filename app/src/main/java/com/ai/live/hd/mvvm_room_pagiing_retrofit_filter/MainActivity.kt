package com.ai.live.hd.mvvm_room_pagiing_retrofit_filter

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.adapter.ItemAdapter
import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.vm.ItemViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Directly create the ViewModel without a factory
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        setupRecyclerView()

        itemViewModel.itemsLiveData.observe(this, Observer { items ->
            itemAdapter.submitList(items)
        })

        itemViewModel.fetchItems(1, 10)

        // Add Search Filter
        findViewById<EditText>(R.id.searchInput).addTextChangedListener {
            val query = it.toString()
            itemViewModel.filterItems(query)
        }
    }

    private fun setupRecyclerView() {
        itemAdapter = ItemAdapter()
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemAdapter
        }

        // Pagination - Load more data when reaching the end of the list
        findViewById<RecyclerView>(R.id.recyclerView).addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    val nextPage = (itemAdapter.itemCount / 10) + 1
                    itemViewModel.fetchItems(nextPage, 10)
                }
            }
        })
    }
}
