package com.hackaprende.registrodesuperheroes

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import com.hackaprende.registrodesuperheroes.databinding.ActivityMainBinding

private const val PHOTO_REQUEST_CODE = 2000

class MainActivity : AppCompatActivity() {
    private lateinit var superheroImage: ImageView
    private var heroBitmap: Bitmap? = null

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
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, PHOTO_REQUEST_CODE)
    }

    private fun openDetailsActivity(hero: Hero) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.HERO_KEY, hero)
        intent.putExtra(DetailActivity.HERO_BITMAP_KEY, heroBitmap)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == PHOTO_REQUEST_CODE) {
            val extras = data?.extras
            heroBitmap = extras?.get("data") as Bitmap
            superheroImage.setImageBitmap(heroBitmap!!)
        }
    }
}