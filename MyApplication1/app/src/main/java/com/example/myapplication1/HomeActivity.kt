package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()                          // ← hide default bar
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupClickListeners()
    }

    // ❌ DELETE setupToolbar() entirely
    // ❌ DELETE onCreateOptionsMenu()
    // ❌ DELETE onOptionsItemSelected()

    private fun setupUI() {
        val hour = java.util.Calendar.getInstance()
            .get(java.util.Calendar.HOUR_OF_DAY)
        binding.tvGreeting.text = when {
            hour < 12 -> "Good Morning 👋"
            hour < 17 -> "Good Afternoon 👋"
            else      -> "Good Evening 👋"
        }
        binding.tvUserName.text = "Dr. Ahmed Ali"
    }

    private fun setupClickListeners() {

        // ✅ Menu opens as PopupMenu on avatar click
        binding.imgAvatar.setOnClickListener { view ->
            showPopupMenu(view)
        }

        binding.actionBook.setOnClickListener {
            Toast.makeText(this, "Book Appointment", Toast.LENGTH_SHORT).show()
        }

        binding.actionEmergency.setOnClickListener {
            showEmergencyDialog()
        }

        binding.actionMedicine.setOnClickListener {
            startActivity(Intent(this, TraitementActivity::class.java))
        }

        binding.actionRecords.setOnClickListener {
            Toast.makeText(this, "Records", Toast.LENGTH_SHORT).show()
        }

        binding.tvSeeAll.setOnClickListener {
            Toast.makeText(this, "All Doctors", Toast.LENGTH_SHORT).show()
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_today -> true
                R.id.nav_progress -> {
                    Toast.makeText(this, "Progrès", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_support -> {
                    Toast.makeText(this, "Soutien", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_traitement -> {
                    startActivity(Intent(this, TraitementActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    // ✅ PopupMenu - no Toolbar needed
    private fun showPopupMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.menuInflater.inflate(R.menu.main_menu, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_profile -> {
                    Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_appointments -> {
                    Toast.makeText(this, "Appointments", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_medicine -> {
                    startActivity(Intent(this, TraitementActivity::class.java))
                    true
                }
                R.id.menu_notifications -> {
                    Toast.makeText(this, "Notifications", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_settings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_logout -> {
                    showLogoutDialog()
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Logout") { _, _ ->
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEmergencyDialog() {
        AlertDialog.Builder(this)
            .setTitle("🚨 Emergency")
            .setMessage("Do you want to call emergency services?")
            .setPositiveButton("Call Now") { _, _ ->
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = android.net.Uri.parse("tel:15")
                }
                startActivity(intent)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}