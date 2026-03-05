package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication1.databinding.ActivityMedicationFrequencyBinding

class MedicationFrequencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMedicationFrequencyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMedicationFrequencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {

        // Back
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Enable Next when option selected
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val hasSelection = checkedId != -1
            binding.btnNext.isEnabled = hasSelection
            binding.btnNext.backgroundTintList =
                android.content.res.ColorStateList.valueOf(
                    if (hasSelection)
                        android.graphics.Color.parseColor("#1A0F0F")
                    else
                        android.graphics.Color.parseColor("#BBBBBB")
                )
        }

        // Next
        binding.btnNext.setOnClickListener {
            val selectedId = binding.radioGroup.checkedRadioButtonId
            val selectedText = findViewById<RadioButton>(selectedId).text.toString()

            when (selectedId) {
                R.id.radioOnceDaily,
                R.id.radioTwiceDaily,
                R.id.radioOnDemand -> {
                    // go to time selection
                    startActivity(
                        Intent(this, MedicationTimeActivity::class.java).apply {
                            putExtra("frequency", selectedText)
                        }
                    )
                }
                R.id.radioMoreOptions -> {
                    // go to advanced options
                    startActivity(
                        Intent(this, MedicationTimeActivity::class.java).apply {
                            putExtra("frequency", "custom")
                        }
                    )
                }
            }
        }
    }
}