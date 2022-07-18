package com.example.bitcointicker.ui.authentication.sign_up

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
import com.example.bitcointicker.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        handleButtonActions()
        return binding.root
    }

    private fun signUp() {
        val email = binding.emailSignUp.text
        val password = binding.passwordSignUp.text
        if ((email.isEmpty() && password.isEmpty()) || (email.isEmpty() || password.isEmpty())){
            Toast.makeText(activity, "Please fill all the blanks", Toast.LENGTH_SHORT).show()
        }else{
            signUpViewModel.signUpUser(email.toString(), password.toString())
        }
    }

    private fun handleButtonActions(){
        binding.signUpButton.setOnClickListener{
            signUp()
            signUpViewModel.result.observe(viewLifecycleOwner){
                if (it){
                    view?.findNavController()?.navigate(R.id.action_signUpFragment_to_homeFragment)
                }
            }
        }
        binding.loginDirection.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }
    }

}