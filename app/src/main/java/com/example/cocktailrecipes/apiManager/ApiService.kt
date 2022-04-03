package com.example.cocktailrecipes.apiManager

import com.example.cocktailrecipes.apiManager.model.DrinkDetails
import com.example.cocktailrecipes.apiManager.model.category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

interface ApiService {

    @GET("{filter}")
    fun getAlcoholicData(
        @Path("filter") filter: String,
        @Query("a") a: String
    ): Call<category>

    //www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
    @GET("{filter}")
    fun getDrinkDetails(
        @Path("filter") filter: String,
        @Query("i") i: String
    ): Call<DrinkDetails>

    //www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
    @GET("{filter}")
    fun getSearchedDrink(
        @Path("filter") filter: String,
        @Query("s") s: String
    ): Call<DrinkDetails>

}