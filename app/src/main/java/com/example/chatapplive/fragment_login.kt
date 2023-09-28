package com.example.chatapplive

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatapplive.databinding.FragmentLoginBinding
import com.example.chatapplive.factory.UserViewModalFactory
import com.example.chatapplive.viewModels.userViewModel
import javax.inject.Inject


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

//    abcd, not working this way for some reason
//    lateinit var userViewModel: userViewModel

    @Inject
    lateinit var userViewModelFactory: UserViewModalFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        (requireContext().applicationContext as MyApplication).appComponent.inject(this@LoginFragment)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userViewModel =
            ViewModelProvider(this, userViewModelFactory).get(userViewModel::class.java)


        userViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            if (it.message.length > 0) {
                with(binding) {
                    editTextText.text = it.message
                    editTextText.visibility = View.VISIBLE
                }
            } else {
                with(binding) {
                    editTextText.visibility = View.GONE
                }
            }
        })

//        abcd why viewLifeCycle and not this?
        userViewModel.user.observe(viewLifecycleOwner, Observer {
            if (it.email != null && it.email != "") {
                val email = it.email
                with(binding) {
                    usernameEditText.isEnabled = false
                    passwordEditText.visibility = View.GONE
                    otpEditText.visibility = View.VISIBLE
                    timer.visibility = View.VISIBLE
                    loginButton.text = "Confirm OTP"
                    loginButton.setOnClickListener {
                        userViewModel.verifyOtp(binding.otpEditText.text.toString(), email)
                    }
                }
                object : CountDownTimer(120000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        val sec = millisUntilFinished / 1000
                        binding.timer.text = sec.toString()
                    }

                    override fun onFinish() {
                        with(binding) {
                            timer.text = "Login Failed!"
                            editTextText.visibility = View.GONE
                            usernameEditText.isEnabled = true
                            passwordEditText.visibility = View.VISIBLE
                            otpEditText.visibility = View.GONE
                            timer.visibility = View.GONE
                            loginButton.text = "Login"
                            loginButton.setOnClickListener {
                                userViewModel.loginUser(
                                    binding.usernameEditText.text.toString(),
                                    binding.passwordEditText.text.toString()
                                )
                            }
                        }

                    }
                }.start()
            }
            if (it.username != null && it.username != "") {
//                Save to room and go to another activity. No actually, lets replace this fragment with another fragment with list of all the friends of the user, and then we can proceed to make the individual chat page. So I guess this part can be done in the main activity instead
            }
        })

//        lifecycleScope.launch {
//            userViewModel.getCurrentUser()
//        }

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            userViewModel.loginUser(username, password)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}