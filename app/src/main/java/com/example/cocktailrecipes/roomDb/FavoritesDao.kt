package com.example.cocktailrecipes.roomDb

import androidx.room.*

@Dao
interface FavoritesDao {

    @Insert
    fun insert(favoriteDrinks: FavoriteDrinks)

    @Update
    fun update(favoriteDrinks: FavoriteDrinks)

    @Query("SELECT * FROM favoriteDrinks_table")
    fun getAllData():List<FavoriteDrinks>

    @Query("DELETE FROM favoriteDrinks_table WHERE drinkName = :drinkName")
    fun deleteByDrinkName(drinkName: String)
}