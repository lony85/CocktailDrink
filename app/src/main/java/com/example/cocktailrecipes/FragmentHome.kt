package com.example.cocktailrecipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailrecipes.adapter.CategoryAdapter
import com.example.cocktailrecipes.apiManager.ApiManager
import com.example.cocktailrecipes.apiManager.model.category
import com.example.cocktailrecipes.databinding.FragmentHomeBinding

class FragmentHome :Fragment(){
    private val apiManager = ApiManager()
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAlcoholicDrinks()
        Log.i("test","run")
    }

    private fun getAlcoholicDrinks() {
        apiManager.getAlcoholicDrinks(object :ApiManager.ApiCallback<List<category.Drink>>{
            override fun onSuccess(data: List<category.Drink>) {
                Log.i("testdata", data.toString())
                showDataInRecyclerView(data)
            }

            override fun onError(errorMessage: String) {
                Log.i("test", errorMessage)
            }

        })
    }

    fun showDataInRecyclerView(data : List<category.Drink>){
        val drinkAdapter = CategoryAdapter(ArrayList(data))
        Log.i("test-adapter",data.toString())
        binding.drinkRecyclerView.adapter = drinkAdapter
//        binding.drinkRecyclerView.layoutManager = LinearLayoutManager(this.requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.drinkRecyclerView.layoutManager = GridLayoutManager(this.requireActivity(),3)

    }


}