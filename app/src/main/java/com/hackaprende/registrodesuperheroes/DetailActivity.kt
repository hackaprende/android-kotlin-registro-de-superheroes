package com.hackaprende.registrodesuperheroes

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val bitmap = extras.getParcelable<Bitmap>(HERO_BITMAP_KEY)

        if (bitmap != null) {
            binding.heroImage.setImageBitmap(bitmap)
        }
    }
}