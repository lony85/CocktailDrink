package com.example.cocktailrecipes.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailrecipes.apiManager.model.category
import com.example.cocktailrecipes.databinding.TemplateRecyclerViewCategoryBinding
import com.example.cocktailrecipes.roomDb.FavoriteDrinks

class favoriteAdapter(private val data: List<FavoriteDrinks>, val recyclerCallBack:RecyclerCallBack) : RecyclerView.Adapter<favoriteAdapter.Holder>() {

    inner class Holder(private val binding: TemplateRecyclerViewCategoryBinding) :  RecyclerView.ViewHolder(binding.root) {

        fun bindData(drinkData:FavoriteDrinks) {

            binding.txtDrinkName.text = drinkData.drinkName

            Glide.with(binding.root)
                .load(drinkData.drinkImg)
                .into(binding.imgDrink)

            itemView.setOnClickListener {
                recyclerCallBack.onItemClickListener(drinkData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = TemplateRecyclerViewCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    //Click Event
    interface RecyclerCallBack{
        fun onItemClickListener(itemClicked:FavoriteDrinks)
    }
}