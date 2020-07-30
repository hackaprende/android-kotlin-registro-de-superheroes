package com.hackaprende.registrodesuperheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hackaprende.registrodesuperheroes.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val HERO_NAME_KEY = "hero_name"
        const val ALTER_EGO_KEY = "alter_ego"
        const val BIO_KEY = "bio"
        const val POWER_KEY = "power"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras!!
        val heroName = extras.getString(HERO_NAME_KEY) ?: ""
        val alterEgo = extras.getString(ALTER_EGO_KEY) ?: ""
        val bio = extras.getString(BIO_KEY) ?: ""
        val power = extras.getFloat(POWER_KEY)

        binding.heroNameText.text = heroName
        binding.alterEgoText.text = alterEgo
        binding.bioText.text = bio
        binding.powerBar.rating = power
    }
}