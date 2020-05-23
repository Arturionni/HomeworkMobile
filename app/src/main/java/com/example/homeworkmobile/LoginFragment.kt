package com.example.homeworkmobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Patterns
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.emailEditText
import kotlinx.android.synthetic.main.fragment_login.emailLayout
import kotlinx.android.synthetic.main.fragment_login.passwordEditText
import kotlinx.android.synthetic.main.fragment_login.passwordLayout
import kotlinx.android.synthetic.main.fragment_sign_up.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class LoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private val APP_PREFERENCES = "mysettings"
    private val APP_PREFERENCES_FIRSTNAME = "APP_PREFERENCES_FIRSTNAME"
    private val APP_PREFERENCES_EMAIL = "APP_PREFERENCES_EMAIL"
    private val APP_PREFERENCES_PASSWORD = "APP_PREFERENCES_PASSWORD"
    var mSettings: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        mSettings = this.activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val url = "http://localhost:56748/login"

        loginButton.setOnClickListener {
            emailLayout.error = null;
            passwordLayout.error = null;

            val email = emailEditText.text.toString();
            val password = passwordEditText.text.toString();

            if (!emailValidator(email)) emailLayout.error = "Неверный формат Email"
            if (!passwordValidator(password)) passwordLayout.error = "Минимум 4 символа"
            if (!mSettings?.contains(APP_PREFERENCES_EMAIL)!!){
                val toast = Toast.makeText(activity?.applicationContext, "Пользователя с таким email не существует", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            }
            if (emailValidator(email) && passwordValidator(password) && mSettings?.contains(APP_PREFERENCES_EMAIL)!!)
                if (mSettings!!.getString(APP_PREFERENCES_EMAIL, "") == email && mSettings!!.getString(APP_PREFERENCES_PASSWORD, "") == password){
                    val handler: Handler = Handler()
                    progressBar2.visibility = View.VISIBLE
                    Thread(Runnable {
                        try {
                            Thread.sleep(2000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                        handler.post(Runnable {
                            progressBar2.visibility = View.GONE
                            val userName = mSettings!!.getString(APP_PREFERENCES_FIRSTNAME, "");
                            val toast = Toast.makeText(activity?.applicationContext, "Добро пожаловать, $userName!", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.TOP, 0, 0)
                            toast.show()

                            val intent = Intent(this.activity, PersonalAreaActivity::class.java)
                            startActivity(intent)
                        })
                    }).start()
                }else{
                    val toast = Toast.makeText(activity?.applicationContext, "Неверный логин или пароль", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.TOP, 0, 0)
                    toast.show()
                }
        }

        signUpButton.setOnClickListener {
            val newFragment: Fragment = SignUpFragment()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()

            transaction.replace(R.id.container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }

    }

    private fun emailValidator(target: CharSequence?): Boolean {
        return if (target == null) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }
    private fun passwordValidator(Password: String): Boolean {
        return Password.length >= 4
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
