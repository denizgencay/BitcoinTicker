package com.example.bitcointicker.ui.authentication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.bitcointicker.R
import com.example.bitcointicker.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        handleButtonActions()
        return binding.root
    }
    private fun login(){
        val email = binding.email.text
        val password = binding.password.text
        if ((email.isEmpty() && password.isEmpty()) || (email.isEmpty() || password.isEmpty())){
            Toast.makeText(activity, "Please fill all the blanks", Toast.LENGTH_SHORT).show()
        }else{
            loginViewModel.loginUser(email.toString(),password.toString())
            loginViewModel.result.observe(viewLifecycleOwner){
                //
            }
        }
    }

    private fun handleButtonActions(){
        binding.loginButton.setOnClickListener{
            login()
        }
        binding.signUpDirection.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}