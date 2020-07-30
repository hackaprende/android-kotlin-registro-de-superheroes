package com.hackaprende.registrodesuperheroes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hackaprende.registrodesuperheroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val heroName = binding.superheroNameEdit.text.toString()
            val alterEgoName = binding.alterEgoEdit.text.toString()
            val bio = binding.bioEdit.text.toString()
            val power = binding.powerBar.rating
            openDetailsActivity(heroName, alterEgoName, bio, power)
        }
    }

    private fun openDetailsActivity(heroName: String, alterEgo: String, bio: String, power: Float) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.HERO_NAME_KEY, heroName)
        intent.putExtra(DetailActivity.ALTER_EGO_KEY, alterEgo)
        intent.putExtra(DetailActivity.BIO_KEY, bio)
        intent.putExtra(DetailActivity.POWER_KEY, power)
        startActivity(intent)
    }
}