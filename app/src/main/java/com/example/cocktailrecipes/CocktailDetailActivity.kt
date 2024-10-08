package com.example.cocktailrecipes

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.cocktailrecipes.apiManager.ApiManager
import com.example.cocktailrecipes.apiManager.model.DrinkDetails
import com.example.cocktailrecipes.databinding.ActivityCocktailDetailBinding
import com.example.cocktailrecipes.roomDb.AppDatabase
import com.example.cocktailrecipes.roomDb.FavoriteDrinks
import com.example.cocktailrecipes.roomDb.FavoritesDao
import java.text.BreakIterator

class CocktailDetailActivity : AppCompatActivity() {
    lateinit var favoritesDao: FavoritesDao
    private val apiManager = ApiManager()
    lateinit var binding: ActivityCocktailDetailBinding
    private lateinit var cocktailId: String
    private lateinit var drinkName: String
    lateinit var drinkImg: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCocktailDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get Intent String
        cocktailId = intent.getStringExtra("dataToSend").toString()

        initCollapsingBarModule()
        initDrinkDetailsModule()
        initFavoriteFabButton(cocktailId)


        // Toolbar
        setSupportActionBar(binding.moduleCollapsingToolbar.toolbar)
        binding.moduleCollapsingToolbar.collapsingToolbar.setExpandedTitleColor(
            ContextCompat.getColor(
                this,
                android.R.color.white
            )
        )
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }

    private fun initCollapsingBarModule() {
        binding.moduleCollapsingToolbar.collapsingToolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                this,
                android.R.color.white
            )
        )
    }

    private fun initDrinkDetailsModule() {
        val drinkId = cocktailId
        apiManager.getDrinkDetail(object : ApiManager.ApiCallback<List<DrinkDetails.Drink?>?> {
            override fun onSuccess(data: List<DrinkDetails.Drink?>?) {
                bindDrinkData(data)
            }

            override fun onError(errorMessage: String) {
                Toast.makeText(this@CocktailDetailActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }, drinkId)
    }

    @SuppressLint("SetTextI18n")
    fun bindDrinkData(data: List<DrinkDetails.Drink?>?) {

        data!!.forEach {

            Glide.with(binding.root)
                .load(it!!.strDrinkThumb)
                .into(binding.moduleCollapsingToolbar.collapsingToolbarImage)

            drinkName = it.strDrink.toString()
            drinkImg = it.strDrinkThumb.toString()

            binding.moduleCollapsingToolbar.collapsingToolbar.title = it.strDrink
            binding.moduleDrinkDetails.txtGlassType.text = it.strGlass
            binding.moduleDrinkDetails.txtAlchoholicType.text = it.strAlcoholic
            binding.moduleDrinkDetails.txtInstruction.text = it.strInstructions
            var ingredientNo = 1

            binding.moduleDrinkDetails.txtIngredients.text =
                it.strIngredient1 + " : " + it.strMeasure1

            if (it.strIngredient2 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient2 + " : " + it.strMeasure2)
                ingredientNo += 1
            }
            if (it.strIngredient3 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient3 + " : " + it.strMeasure3)
                ingredientNo += 1
            }
            if (it.strIngredient4 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient4 + " : " + it.strMeasure4)
                ingredientNo += 1
            }
            if (it.strIngredient5 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient5 + " : " + it.strMeasure5)
                ingredientNo += 1
            }
            if (it.strIngredient6 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient6 + " : " + it.strMeasure6)
                ingredientNo += 1
            }
            if (it.strIngredient7 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient7 + " : " + it.strMeasure7)
                ingredientNo += 1
            }
            if (it.strIngredient8 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient8 + " : " + it.strMeasure8)
                ingredientNo += 1
            }
            if (it.strIngredient9 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient9 + " : " + it.strMeasure9)
                ingredientNo += 1
            }
            if (it.strIngredient10 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient10 + " : " + it.strMeasure10)
                ingredientNo += 1
            }
            if (it.strIngredient11 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient11 + " : " + it.strMeasure11)
                ingredientNo += 1
            }
            if (it.strIngredient12 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient12 + " : " + it.strMeasure12)
                ingredientNo += 1
            }
            if (it.strIngredient13 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient13 + " : " + it.strMeasure13)
                ingredientNo += 1
            }
            if (it.strIngredient14 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient14 + " : " + it.strMeasure14)
                ingredientNo += 1
            }
            if (it.strIngredient15 != null) {
                binding.moduleDrinkDetails.txtIngredients.append("\n" + it.strIngredient15 + " : " + it.strMeasure15)
                ingredientNo += 1
            }

            binding.moduleDrinkDetails.txtIngredientNo.text = "$ingredientNo items"
        }
    }

    private fun initFavoriteFabButton(cocktailId: String) {

        favoritesDao = AppDatabase.getInstance(this).favoritesDao

        val appDatabase = favoritesDao.getAllData()
        if (appDatabase.toString().contains(cocktailId)) {
            binding.fabFavoriteBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
            binding.fabFavoriteBtn.imageTintList = ColorStateList.valueOf(getColor(R.color.red))
        } else {
            binding.fabFavoriteBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
            binding.fabFavoriteBtn.imageTintList = ColorStateList.valueOf(getColor(R.color.purple))

        }

        binding.fabFavoriteBtn.setOnClickListener {
            val appDatabase = favoritesDao.getAllData()
            if (appDatabase.isEmpty()) {    //Empty Database
                Log.i("database-d", "empty database")
                binding.fabFavoriteBtn.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favorite
                    )
                )
                binding.fabFavoriteBtn.imageTintList = ColorStateList.valueOf(getColor(R.color.red))

                favoritesDao.insert(
                    FavoriteDrinks(
                        drinkId = cocktailId,
                        drinkName = drinkName,
                        drinkImg = drinkImg
                    )
                )
            } else if (appDatabase.isNotEmpty()) {  //Not Empty Database
                Log.i("database-d", "NOT empty database")
                if (appDatabase.toString().contains(cocktailId)) {
                    Log.i("database-d", "drinkId == cocktailId")
                    binding.fabFavoriteBtn.imageTintList =
                        ColorStateList.valueOf(getColor(R.color.red))
                    binding.fabFavoriteBtn.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.ic_favorite_border
                        )
                    )
                    binding.fabFavoriteBtn.imageTintList =
                        ColorStateList.valueOf(getColor(R.color.purple))
                    favoritesDao.deleteByDrinkName(drinkName)

                } else {
                    Log.i("database-d", "drinkId ! = cocktailId")
                    binding.fabFavoriteBtn.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.ic_favorite
                        )
                    )
                    binding.fabFavoriteBtn.imageTintList =
                        ColorStateList.valueOf(getColor(R.color.red))
                    favoritesDao.insert(
                        FavoriteDrinks(
                            drinkId = cocktailId,
                            drinkName = drinkName,
                            drinkImg = drinkImg
                        )
                    )


                }
            }
        }

    }
}
