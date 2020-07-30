package com.hackaprende.registrodesuperheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hackaprende.registrodesuperheroes.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val HERO_KEY = "hero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras!!
        val hero = extras.getParcelable<Hero>(HERO_KEY)!!

        binding.heroNameText.text = hero.name
        binding.alterEgoText.text = hero.alterEgo
        binding.bioText.text = hero.bio
        binding.powerBar.rating = hero.power
    }
}