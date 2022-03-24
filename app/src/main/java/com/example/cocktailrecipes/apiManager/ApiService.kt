package com.example.cocktailrecipes.apiManager

import com.example.cocktailrecipes.apiManager.model.category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

interface ApiService {

    @GET("{filter}")
    fun getAlcoholicData(
        @Path("filter") filter : String,
        @Query("a")a:String
    ):Call<category>
}