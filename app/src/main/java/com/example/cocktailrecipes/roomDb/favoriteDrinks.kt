package com.example.cocktailrecipes.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteDrinks_table")
data class FavoriteDrinks(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val drinkId: String,
    val drinkName :String,
    val drinkImg:String

)
