package com.dicoding.furniscan.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.furniscan.data.local.entity.FavoriteProduct

@Database(entities = [FavoriteProduct::class], version = 1)
abstract class FavoriteDatabase: RoomDatabase(){

    abstract fun favDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteDatabase::class.java, "fav_db")
                        .build()
                }
            }
            return INSTANCE as FavoriteDatabase
        }
    }
}