package com.example.cocktailrecipes.apiManager

import android.util.Log
import com.example.cocktailrecipes.apiManager.model.DrinkDetails
import com.example.cocktailrecipes.apiManager.model.category
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    //Api Samples
    //www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
    //www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
    //www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic
    //www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non_Alcoholic

    fun getAlcoholicDrinks(apiCallback: ApiCallback<List<category.Drink>>,a:String) {
        apiService.getAlcoholicData("filter.php", a).enqueue(object : Callback<category> {
            override fun onResponse(call: Call<category>, response: Response<category>) {
                val data = response.body()
                apiCallback.onSuccess(data!!.drinks)
            }

            override fun onFailure(call: Call<category>, t: Throwable) {
                Log.i("test", t.message!!)
            }
        })
    }

    fun getDrinkDetail(apiCallback: ApiCallback<List<DrinkDetails.Drink?>?>,i:String) {
        apiService.getDrinkDetails("lookup.php", i).enqueue(object : Callback<DrinkDetails> {
            override fun onResponse(call: Call<DrinkDetails>, response: Response<DrinkDetails>) {
                val data = response.body()
                apiCallback.onSuccess(data!!.drinks)
            }

            override fun onFailure(call: Call<DrinkDetails>, t: Throwable) {
                Log.i("drinkDetailserror", t.message!!)
            }

        })

    }
    fun searchDrink(apiCallback: ApiCallback<List<DrinkDetails.Drink?>?>,s:String){
        apiService.getSearchedDrink("search.php", s).enqueue(object : Callback<DrinkDetails> {
            override fun onResponse(call: Call<DrinkDetails>, response: Response<DrinkDetails>) {
                val data = response.body()
                apiCallback.onSuccess(data!!.drinks)
            }

            override fun onFailure(call: Call<DrinkDetails>, t: Throwable) {
                Log.i("drinkDetailserror", t.message!!)
            }

        })
    }
    interface ApiCallback<T> {
        fun onSuccess(data: T)
        fun onError(errorMessage: String)
    }
}