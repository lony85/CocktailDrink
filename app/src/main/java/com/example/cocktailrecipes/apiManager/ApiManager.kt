package com.example.cocktailrecipes.apiManager

import android.util.Log
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

    fun getAlcoholicDrinks(apiCallback: ApiCallback<List<category.Drink>>){
        apiService.getAlcoholicData("filter.php","Alcoholic").enqueue(object :Callback<category>{
            override fun onResponse(call: Call<category>, response: Response<category>) {
                val data = response.body()
                Log.i("testdata1",data.toString())
                Log.i("test","hi")
                apiCallback.onSuccess(data!!.drinks)
            }
            override fun onFailure(call: Call<category>, t: Throwable) {
                Log.i("test", t.message!!)
            }

        })
    }
    interface ApiCallback<T> {
        fun onSuccess(data: T)
        fun onError(errorMessage: String)
    }
}