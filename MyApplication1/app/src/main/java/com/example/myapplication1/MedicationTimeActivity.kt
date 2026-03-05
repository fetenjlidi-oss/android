package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication1.databinding.ActivityMedicationTimeBinding

class MedicationTimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMedicationTimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMedicationTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {

        // Back button
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Skip
        binding.tvSkip.setOnClickListener {
            navigateToNext()
        }

        // Next button
        binding.btnNext.setOnClickListener {
            val selectedTime = getSelectedTime()
            if (selectedTime != null) {
                Toast.makeText(
                    this,
                    "Selected: $selectedTime",
                    Toast.LENGTH_SHORT
                ).show()
                navigateToNext()
            } else {
                Toast.makeText(
                    this,
                    "Please select a time",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.btnNext.setOnClickListener {
            val name = binding.btnNext.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter medication name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // ✅ Go to frequency screen
            startActivity(Intent(this, MedicationFrequencyActivity::class.java))
        }
    }


    private fun getSelectedTime(): String? {
        return when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radio0600 -> "06:00"
            R.id.radio0700 -> "07:00"
            R.id.radio0800 -> "08:00"
            R.id.radio0900 -> "09:00"
            R.id.radio1000 -> "10:00"
            R.id.radio1100 -> "11:00"
            R.id.radioOther -> "Other"
            else -> null
        }
    }

    private fun navigateToNext() {
        startActivity(Intent(this, TraitementActivity::class.java))
        finish()
    }
}