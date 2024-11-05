package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set listener pada btn_login di sini
        binding.btnLogin.setOnClickListener {
            handleLoginButtonClick()
        }
    }

    private fun handleLoginButtonClick() {
        with(binding) {
            // Ambil input dari EditText
            val inputEmail = etEmail.text.toString()
            val inputPassword = etPassword.text.toString()

            // Ambil data yang tersimpan di SharedPreferences
            val sharedPreferences = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
            val savedEmail = sharedPreferences.getString("email", "")
            val savedPassword = sharedPreferences.getString("password", "")

            // Validasi email dan password
            if (inputEmail == savedEmail && inputPassword == savedPassword) {
                // Login berhasil, arahkan ke DashboardActivity
                val intent = Intent(requireContext(), DasbordActivity::class.java)
                startActivity(intent)
            } else {
                // Login gagal, tampilkan pesan
                Toast.makeText(requireContext(), "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
