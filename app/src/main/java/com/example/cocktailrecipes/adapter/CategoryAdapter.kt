package com.example.cocktailrecipes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailrecipes.R
import com.example.cocktailrecipes.apiManager.model.category
import com.example.cocktailrecipes.databinding.TemplateRecyclerViewCategoryBinding

class CategoryAdapter(
    private val data: ArrayList<category.Drink>
    ) : RecyclerView.Adapter<CategoryAdapter.Holder>() {

    lateinit var binding: TemplateRecyclerViewCategoryBinding

    inner class Holder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bindData(drinkData:category.Drink) {
            binding.txtDrinkName.text = drinkData.strDrink

            Glide.with(binding.root)
                .load(drinkData.strDrinkThumb)
                .into(binding.imgDrink)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = TemplateRecyclerViewCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding.root)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}