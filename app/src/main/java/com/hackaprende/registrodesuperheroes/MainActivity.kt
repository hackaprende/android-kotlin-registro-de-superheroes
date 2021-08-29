package com.hackaprende.registrodesuperheroes

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.hackaprende.registrodesuperheroes.databinding.ActivityMainBinding

import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var superheroImage: ImageView
    private var heroBitmap: Bitmap? = null

    private val getContent = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            bitmap ->
        heroBitmap = bitmap
        superheroImage.setImageBitmap(heroBitmap)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val heroName = binding.superheroNameEdit.text.toString()
            val alterEgoName = binding.alterEgoEdit.text.toString()
            val bio = binding.bioEdit.text.toString()
            val power = binding.powerBar.rating
            val hero = Hero(heroName, alterEgoName, bio, power)
            openDetailsActivity(hero)
        }

        superheroImage = binding.superheroImage
        superheroImage.setOnClickListener {
            openCamera()
        }
    }

    private fun openCamera() {
        getContent.launch(null)
    }

    private fun openDetailsActivity(hero: Hero) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.HERO_KEY, hero)
        intent.putExtra(DetailActivity.HERO_BITMAP_KEY, heroBitmap)
        startActivity(intent)
    }
}