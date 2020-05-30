package com.example.homeworkmobile.views.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.homeworkmobile.R
import com.example.homeworkmobile.model.data.User
import com.example.homeworkmobile.utils.emailValidator
import com.example.homeworkmobile.utils.nameValidator
import com.example.homeworkmobile.utils.passwordValidator
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SignUpFragment : Fragment(), CoroutineScope {

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
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        signUpBackButton.setOnClickListener {
            goLoginFragment()
        }
        signUpSendButton.setOnClickListener {
            val email = emailEditText.text.toString();
            val password = passwordEditText.text.toString();
            val passwordConfirm = passwordConfirmEditText.text.toString();
            val firstName = firstNameEditText.text.toString();

            validFields(email, firstName, password, passwordConfirm)

            val value: MainActivity = activity as MainActivity
            launch {
                val userExist = value.mUserViewModel.userExist(email)
                if (emailValidator(email) && nameValidator(firstName) && passwordValidator(password) && passwordValidator(passwordConfirm) && passwordConfirm == password){
                    if (!userExist) {
                        progressBar.visibility = View.VISIBLE
                        createUser(User(null, firstName, password, email,
                            isClient = true,
                            status = true
                        ))
                    } else {
                        val toast = Toast.makeText(activity?.applicationContext, "Пользователь с таким email уже существует!", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP, 0, 0)
                        toast.show()
                    }
                }
                else {
                    val toast = Toast.makeText(activity?.applicationContext, "Пароли не совпадают!", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.TOP, 0, 0)
                    toast.show()

                }

            }
        }
    }
    private fun validFields(email: String, firstName: String, password: String, passwordConfirm: String) {
        emailLayout.error = null;
        firstNameLayout.error = null;
        passwordLayout.error = null;
        passwordConfirmLayout.error = null;
        if (!emailValidator(email)) emailLayout.error = "Неверный формат Email"
        if (!nameValidator(firstName)) firstNameLayout.error = "Имя содержит недопустимые символы"
        if (!passwordValidator(password)) passwordLayout.error = "Минимум 4 символа"
        if (!passwordValidator(passwordConfirm)) passwordConfirmLayout.error = "Минимум 4 символа"
    }

    private fun goLoginFragment() {
        val newFragment: Fragment =
            LoginFragment()
        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        progressBar.visibility = View.GONE
    }
    private fun createUser(user: User) {
        launch {
            delay(3000)
            val value: MainActivity = activity as MainActivity
            value.mUserViewModel.addUserToDB(user)

            val toast = Toast.makeText(activity?.applicationContext, "Регистрация прошла успешно", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
            goLoginFragment()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
            }
    }
}
