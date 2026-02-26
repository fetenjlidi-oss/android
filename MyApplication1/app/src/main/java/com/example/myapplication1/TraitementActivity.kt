package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication1.databinding.ActivityTraitementBinding

class TraitementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTraitementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTraitementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {

        // Add Medicine Button
        binding.btnAddMedicine.setOnClickListener {
            // Navigate to Add Medicine screen
            // startActivity(Intent(this, AddMedicineActivity::class.java))
        }

        // Settings
        binding.imgSettings.setOnClickListener {
            // startActivity(Intent(this, SettingsActivity::class.java))
        }

        // Bottom Nav
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_today -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_traitement -> true
                else -> false
            }
        }
    }
}