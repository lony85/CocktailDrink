package com.example.cocktailrecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.example.cocktailrecipes.databinding.ActivityMainBinding
import com.example.cocktailrecipes.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainer,FragmentHome())
        transaction.commit()

//        binding.collapsingBarMain.collapsingToolbar.title = "Passion fruit \nMojito" // set toolbar names
//        binding.collapsingBarMain.collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(this,R.color.white))
//        binding.collapsingBarMain.collapsingToolbar.setExpandedTitleTextAppearance(R.style.text_huge_collapsing)


        // Toolbar
//        setSupportActionBar(binding.collapsingBarMain.toolbar)
//        binding.collapsingBarMain.collapsingToolbar.setExpandedTitleColor(
//            ContextCompat.getColor(
//                this,
//                android.R.color.transparent
//            )
//        )
//        supportActionBar!!.setHomeButtonEnabled(true)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home) {
//            onBackPressed()
//        }
//        return true
//    }


}