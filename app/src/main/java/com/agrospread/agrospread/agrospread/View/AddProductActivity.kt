package com.agrospread.agrospread.agrospread.View

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.ViewModel.AddProductViewModel
class AddProductActivity : AppCompatActivity() {

    private var fileUri: Uri? = null
    private val viewModel: AddProductViewModel by viewModels()

    private lateinit var chooseImg: Button
    private lateinit var uploadImg: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        // Initialize views using findViewById
        chooseImg = findViewById(R.id.choose_image)
        uploadImg = findViewById(R.id.upload_image)
        imageView = findViewById(R.id.image_view)

        chooseImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            startActivityForResult(intent, 0)
        }

        uploadImg.setOnClickListener {
            fileUri?.let {
                viewModel.uploadImage(it)
            } ?: Toast.makeText(this, "Please select image to upload", Toast.LENGTH_SHORT).show()
        }

        viewModel.uploadStatus.observe(this, Observer { status ->
            Toast.makeText(this, status, Toast.LENGTH_LONG).show()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == RESULT_OK && data?.data != null) {
            fileUri = data.data
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
                imageView.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
