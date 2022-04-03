package com.example.cocktailrecipes

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cocktailrecipes.adapter.CategoryAdapter
import com.example.cocktailrecipes.adapter.SearchAdapter
import com.example.cocktailrecipes.apiManager.ApiManager
import com.example.cocktailrecipes.apiManager.model.DrinkDetails
import com.example.cocktailrecipes.databinding.FragmentSearchBinding

class FragmentSearch : Fragment() , SearchAdapter.RecyclerCallBack{
    private val apiManager = ApiManager()
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.addTextChangedListener {

            if (it!!.isNotEmpty()) {
                getSearchData(it.toString())
            }else{
                binding.recyclerViewSearch.visibility = View.GONE
            }

        }

    }

    private fun getSearchData(it: String) {
        apiManager.searchDrink(object : ApiManager.ApiCallback<List<DrinkDetails.Drink?>?> {
            override fun onSuccess(data: List<DrinkDetails.Drink?>?) {
                if (data!=null){
                    binding.recyclerViewSearch.visibility = View.VISIBLE
                    Log.i("search",data.toString())
                    showDataInRecyclerView(data)
                }else{
                    binding.recyclerViewSearch.visibility = View.GONE
                }
            }

            override fun onError(errorMessage: String) {
                Log.i("search",errorMessage)
            }

        }, it)
    }

    fun showDataInRecyclerView(data: List<DrinkDetails.Drink?>?){
        val drinkAdapter = SearchAdapter(data,this)
        binding.recyclerViewSearch.adapter = drinkAdapter
        binding.recyclerViewSearch.layoutManager = GridLayoutManager(this.requireActivity(),2)
    }

    override fun onItemClickListener(itemClicked: DrinkDetails.Drink) {
        val intent = Intent(this.requireActivity(), CocktailDetailActivity::class.java)
        intent.putExtra("searchItem",itemClicked.idDrink)
        startActivity(intent)
    }
}