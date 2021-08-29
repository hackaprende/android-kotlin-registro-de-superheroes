package com.hackaprende.registrodesuperheroes

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hackaprende.registrodesuperheroes.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val HERO_KEY = "hero"
        const val HERO_BITMAP_KEY = "hero_bitmap"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras!!
        val hero = extras.getParcelable<Hero>(HERO_KEY)!!
        binding.hero = hero
        val imagePath = extras.getString(HERO_BITMAP_KEY)

        val bitmap = BitmapFactory.decodeFile(imagePath)

        if (bitmap != null) {
            binding.heroImage.setImageBitmap(bitmap)
        }
    }
}