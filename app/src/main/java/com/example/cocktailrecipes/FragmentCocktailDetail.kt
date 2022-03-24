package com.example.cocktailrecipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cocktailrecipes.databinding.FragmentCocktailDetailBinding

class FragmentCocktailDetail:Fragment() {
    lateinit var binding : FragmentCocktailDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCocktailDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}