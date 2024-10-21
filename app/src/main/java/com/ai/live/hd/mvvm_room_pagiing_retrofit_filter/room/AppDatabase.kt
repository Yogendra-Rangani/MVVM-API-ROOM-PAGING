package com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.room

import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.data.Item

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" // Your database name
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
