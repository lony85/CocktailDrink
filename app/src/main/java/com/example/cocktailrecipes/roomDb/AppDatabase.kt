package com.example.cocktailrecipes.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(version = 1, exportSchema = false, entities = [FavoriteDrinks::class])
abstract class AppDatabase : RoomDatabase() {
    abstract val favoritesDao: FavoritesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "favoritesDrinks.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
                return instance
            }
        }
    }
}


