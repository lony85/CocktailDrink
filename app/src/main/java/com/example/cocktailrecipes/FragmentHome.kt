package com.example.cocktailrecipes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cocktailrecipes.adapter.CategoryAdapter
import com.example.cocktailrecipes.apiManager.ApiManager
import com.example.cocktailrecipes.apiManager.model.category
import com.example.cocktailrecipes.databinding.FragmentHomeBinding

class FragmentHome : Fragment(), CategoryAdapter.RecyclerCallBack {
    private val apiManager = ApiManager()
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAlcoholicDrinks()

        binding.alcoholBtn.setOnClickListener {
            getAlcoholicDrinks()
            binding.alcoholBtn.backgroundTintList = AppCompatResources.getColorStateList(this.requireActivity(), R.color.yellow_primary)
            binding.nonAlcoholBtn.backgroundTintList = AppCompatResources.getColorStateList(this.requireActivity(), R.color.grey_dark_transparent)
        }
        binding.nonAlcoholBtn.setOnClickListener {
            getNonAlcoholicDrinks()
            binding.alcoholBtn.backgroundTintList = AppCompatResources.getColorStateList(this.requireActivity(), R.color.grey_dark_transparent)
            binding.nonAlcoholBtn.backgroundTintList = AppCompatResources.getColorStateList(this.requireActivity(), R.color.yellow_primary)
        }

    }

    private fun getAlcoholicDrinks() {
        apiManager.getAlcoholicDrinks(object : ApiManager.ApiCallback<List<category.Drink>> {
            override fun onSuccess(data: List<category.Drink>) {
                showDataInRecyclerView(data)
                Log.i("test12",data.toString())
            }

            override fun onError(errorMessage: String) {
                Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        },"Alcoholic")
    }
    private fun getNonAlcoholicDrinks() {
        apiManager.getAlcoholicDrinks(object : ApiManager.ApiCallback<List<category.Drink>> {
            override fun onSuccess(data: List<category.Drink>) {
                showDataInRecyclerView(data)
                Log.i("test12",data.toString())
            }

            override fun onError(errorMessage: String) {
                Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        },"Non_Alcoholic")
    }

    fun showDataInRecyclerView(data: List<category.Drink>) {
        val drinkAdapter = CategoryAdapter(data,this)
        binding.drinkRecyclerView.adapter = drinkAdapter
//        binding.drinkRecyclerView.layoutManager = LinearLayoutManager(this.requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.drinkRecyclerView.layoutManager = GridLayoutManager(this.requireActivity(), 2)
    }

    override fun onItemClickListener(itemClicked: category.Drink) {
        val intent = Intent(this.requireActivity(), CocktailDetailActivity::class.java)
        intent.putExtra("dataToSend",itemClicked.idDrink)
        startActivity(intent)
    }
}