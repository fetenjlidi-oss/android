package com.example.myapplication1
import android.os.Bundle
import com.example.myapplication1.databinding.ActivityLoginBinding
import androidx.appcompat.app.AppCompatActivity  // ← add this at top
import android.util.Patterns
import android.content.Intent
class LoginActivity:   AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            navigateToTraitement()

        }

    }
    private fun validateInputs(): Boolean {
        var isValid = true
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()

        // Validate Email
        if (email.isEmpty()) {
            binding.emailLayout.error = "Email is required"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailLayout.error = "Enter a valid email"
            isValid = false
        } else {
            binding.emailLayout.error = null  // clear error
        }

        // Validate Password
        if (password.isEmpty()) {
            binding.passwordLayout.error = "Password is required"
            isValid = false
        } else if (password.length < 6) {
            binding.passwordLayout.error = "Min 6 characters"
            isValid = false
        } else {
            binding.passwordLayout.error = null
        }

        return isValid
    }  private fun navigateToTraitement() {
        startActivity(Intent(this, TraitementActivity::class.java))
        finish()
    }


}
