package com.example.cocktailrecipes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cocktailrecipes.adapter.favoriteAdapter
import com.example.cocktailrecipes.databinding.FragmentFavoritesBinding
import com.example.cocktailrecipes.roomDb.AppDatabase
import com.example.cocktailrecipes.roomDb.FavoriteDrinks
import com.example.cocktailrecipes.roomDb.FavoritesDao
import java.util.Collections.list

class FragmentFavorites:Fragment(),favoriteAdapter.RecyclerCallBack {
    private lateinit var favoritesDao: FavoritesDao
    lateinit var binding:FragmentFavoritesBinding
lateinit var adapter:favoriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater,container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        favoritesDao = AppDatabase.getInstance(this.requireActivity()).favoritesDao
        val favoritesDrinks = favoritesDao.getAllData()
        adapter = favoriteAdapter(favoritesDrinks,this)
        binding.drinkRecyclerView.adapter = adapter
        binding.drinkRecyclerView.layoutManager = GridLayoutManager(this.requireActivity(),2)

    }
    override fun onItemClickListener(itemClicked: FavoriteDrinks) {
        val intent = Intent(this.requireActivity(), CocktailDetailActivity::class.java)
        intent.putExtra("dataToSend",itemClicked.drinkId)
        startActivity(intent)

    }
}