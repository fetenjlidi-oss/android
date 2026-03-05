package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication1.databinding.ActivityAddTreatmentBinding

class AddTreatmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTreatmentBinding
    private var selectedUnit = "pill(s)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityAddTreatmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        setupNameWatcher()
    }

    private fun setupNameWatcher() {
        // Enable Next button only when name is filled
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val hasText = !s.isNullOrEmpty()
                binding.btnNext.isEnabled = hasText
                binding.btnNext.backgroundTintList =
                    android.content.res.ColorStateList.valueOf(
                        if (hasText)
                            android.graphics.Color.parseColor("#1A0F0F")
                        else
                            android.graphics.Color.parseColor("#BBBBBB")
                    )
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupClickListeners() {

        // Back
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Unit selector popup
        binding.tvUnit.setOnClickListener {
            showUnitPopup()
        }

        // Next
        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter medication name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Navigate to time selection
            startActivity(Intent(this, MedicationTimeActivity::class.java))
        }

    }


    private fun showUnitPopup() {
        val popup = PopupMenu(this, binding.tvUnit)
        val units = listOf(
            "pill(s)",
            "tablet(s)",
            "capsule(s)",
            "mg",
            "ml",
            "drop(s)",
            "injection(s)"
        )
        units.forEachIndexed { index, unit ->
            popup.menu.add(0, index, index, unit)
        }
        popup.setOnMenuItemClickListener { item ->
            selectedUnit = units[item.itemId]
            binding.tvUnit.text = "$selectedUnit ▾"
            true
        }
        popup.show()
    }

}