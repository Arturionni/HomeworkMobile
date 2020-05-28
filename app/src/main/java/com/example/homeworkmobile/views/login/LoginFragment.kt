package com.example.homeworkmobile.views.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.homeworkmobile.R
import com.example.homeworkmobile.model.data.User
import com.example.homeworkmobile.utils.emailValidator
import com.example.homeworkmobile.utils.passwordValidator
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.emailEditText
import kotlinx.android.synthetic.main.fragment_login.emailLayout
import kotlinx.android.synthetic.main.fragment_login.passwordEditText
import kotlinx.android.synthetic.main.fragment_login.passwordLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LoginFragment : Fragment(), CoroutineScope {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginButton.setOnClickListener {
            emailLayout.error = null;
            passwordLayout.error = null;

            val email = emailEditText.text.toString();
            val password = passwordEditText.text.toString();

            if (!emailValidator(email)) emailLayout.error = "Неверный формат Email"
            if (!passwordValidator(password)) passwordLayout.error = "Минимум 4 символа"

            launch{
                val value: MainActivity = activity as MainActivity
                value.mUserViewModel.getUserFromDb().observe(value, Observer { mappingList ->
                    mappingList.forEach{Log.d("TAG" , it.id.toString() + " " + it.firstName)}
                })

            }
        }

        signUpButton.setOnClickListener {
            val newFragment: Fragment =
                SignUpFragment()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()

            transaction.replace(R.id.container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
            }
    }
}
