package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityDasbordBinding

class DasbordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDasbordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasbordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari SharedPreferences
        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "Default Username")
        val nim = sharedPreferences.getString("nim", "Default NIM")

        // Tampilkan username dan nim di UI
        binding.tvUsername.text = username
        binding.tvNim.text = nim

        // Set listener untuk tombol logout
        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        // (Opsional) Hapus data dari SharedPreferences jika ingin menghapus data login
        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        // Kembali ke MainActivity dan set ViewPager ke LoginFragment
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("targetFragment", 1) // 1 untuk posisi LoginFragment
        startActivity(intent)
        finish() // Menutup DashboardActivity
    }
}
