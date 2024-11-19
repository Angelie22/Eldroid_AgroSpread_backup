package com.agrospread.agrospread.agrospread.View


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.agrospread.agrospread.R
import com.agrospread.agrospread.databinding.ActivityPesticideDetailBinding

class PesticideDetail : AppCompatActivity() {
    private lateinit var binding: ActivityPesticideDetailBinding
    private var quantity: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesticideDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, PesticideActivity::class.java)
            startActivity(intent)
        }

        val btnPlus = binding.btnPlus
        val btnMinus = binding.btnMinus
        val quantityTextView = binding.quantityTextView

        val intent = intent
        if (intent != null) {
            val name = intent.getStringExtra("name")
            val image = intent.getIntExtra("image", R.drawable.f1)

            binding.detailName.text = name
            binding.detailImage.setImageResource(image)

            // Set initial quantity
            quantityTextView.text = quantity.toString()

            // Set onClickListener for the plus button
            btnPlus.setOnClickListener {
                quantity++
                updateQuantity()
            }

            // Set onClickListener for the minus button
            btnMinus.setOnClickListener {
                if (quantity > 0) {
                    quantity--
                    updateQuantity()
                }
            }
        }
    }

    private fun updateQuantity() {
        binding.quantityTextView.text = quantity.toString()
    }
}

