package com.example.cocktailrecipes.apiManager.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class category(
    @SerializedName("drinks")
    val drinks: List<Drink>
) : Parcelable {
    @Parcelize
    data class Drink(
        @SerializedName("idDrink")
        val idDrink: String,
        @SerializedName("strDrink")
        val strDrink: String,
        @SerializedName("strDrinkThumb")
        val strDrinkThumb: String
    ) : Parcelable
}