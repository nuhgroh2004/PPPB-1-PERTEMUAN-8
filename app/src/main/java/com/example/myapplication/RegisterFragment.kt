package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    // Deklarasi interface untuk mengubah posisi ViewPager
    interface OnRegisterCompleteListener {
        fun onRegisterComplete()
    }

    private var listener: OnRegisterCompleteListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRegisterCompleteListener) {
            listener = context
        } else {
            throw ClassCastException("$context harus mengimplementasikan OnRegisterCompleteListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        // Set listener pada btn_register untuk memanggil handleRegisterButtonClick()
        binding.btnRegister.setOnClickListener {
            handleRegisterButtonClick()
        }

        return binding.root
    }

    private fun handleRegisterButtonClick() {
        with(binding) {
            // Simpan data ke SharedPreferences seperti biasa
            val username = etUsername.text.toString()
            val nim = etNim.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            val sharedPreferences = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putString("username", username)
                putString("nim", nim)
                putString("email", email)
                putString("password", password)
                apply()
            }

            // Mengosongkan semua EditText setelah menyimpan data
            etUsername.text.clear()
            etNim.text.clear()
            etEmail.text.clear()
            etPassword.text.clear()

            // Meminta perpindahan ke LoginFragment dengan mengubah posisi ViewPager
            listener?.onRegisterComplete()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Menghindari memory leak
    }
}
