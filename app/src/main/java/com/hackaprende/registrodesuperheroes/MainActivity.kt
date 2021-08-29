package com.hackaprende.registrodesuperheroes

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.hackaprende.registrodesuperheroes.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var superheroImage: ImageView
    private var heroBitmap: Bitmap? = null
    private var pictureFullPath = ""

    private val getContent = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        success ->
        if (success && pictureFullPath.isNotEmpty()) {
            heroBitmap = BitmapFactory.decodeFile(pictureFullPath)
            if (heroBitmap != null) {
                superheroImage.setImageBitmap(heroBitmap)
            }
        }
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
        val imageFile = createImageFile()
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(
                this, "$packageName.provider",
                imageFile
            )
        } else {
            Uri.fromFile(imageFile)
        }

        getContent.launch(uri)
    }

    private fun createImageFile(): File {
        val imageFileName = "superhero_image"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile(imageFileName, ".jpg", storageDir)
        pictureFullPath = file.absolutePath
        return file
    }

    private fun openDetailsActivity(hero: Hero) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.HERO_KEY, hero)
        intent.putExtra(DetailActivity.HERO_BITMAP_KEY, pictureFullPath)
        startActivity(intent)
    }
}