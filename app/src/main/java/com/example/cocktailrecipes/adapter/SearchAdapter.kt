package com.example.cocktailrecipes.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailrecipes.apiManager.model.DrinkDetails
import com.example.cocktailrecipes.apiManager.model.category
import com.example.cocktailrecipes.databinding.TemplateRecyclerViewCategoryBinding

class SearchAdapter(private val data: List<DrinkDetails.Drink?>?, val recyclerCallBack:RecyclerCallBack) : RecyclerView.Adapter<SearchAdapter.Holder>() {

    inner class Holder(private val binding: TemplateRecyclerViewCategoryBinding) :  RecyclerView.ViewHolder(binding.root) {

        fun bindData(drinkData:DrinkDetails.Drink) {

            binding.txtDrinkName.text = drinkData.strDrink

            Glide.with(binding.root)
                .load(drinkData.strDrinkThumb)
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
        data!![position]?.let { holder.bindData(it) }
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    //Click Event
    interface RecyclerCallBack{
        fun onItemClickListener(itemClicked:DrinkDetails.Drink)
    }
}