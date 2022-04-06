package com.example.cocktailrecipes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AlphaAnimation
import com.example.cocktailrecipes.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    lateinit var binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.akTxt.visibility = View.GONE
        binding.baristaTxt.visibility = View.GONE

        alphaAnimationTxt1()
        alphaAnimationTxt2()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },2800)

    }


    private fun alphaAnimationTxt1() {
        val anim = AlphaAnimation(0f,1f)
        anim.duration = 1300
        Handler(Looper.getMainLooper()).postDelayed({
            binding.akTxt.visibility = View.VISIBLE
            binding.akTxt.startAnimation(anim)
        },500)
//
    }
    private fun alphaAnimationTxt2() {
        val anim = AlphaAnimation(0f,1f)
        anim.duration = 1300
        Handler(Looper.getMainLooper()).postDelayed({
            binding.baristaTxt.visibility = View.VISIBLE
            binding.baristaTxt.startAnimation(anim)
        }, 1500)
    }

}