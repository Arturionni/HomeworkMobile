package com.example.homeworkmobile.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.android.volley.toolbox.Volley
import com.example.homeworkmobile.R
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.json.JSONObject
import java.util.regex.Matcher
import java.util.regex.Pattern


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val url = "http://10.0.2.2:56748/register"
const val APP_PREFERENCES = "mysettings"
const val APP_PREFERENCES_FIRSTNAME = "APP_PREFERENCES_FIRSTNAME"
const val APP_PREFERENCES_EMAIL = "APP_PREFERENCES_EMAIL"
const val APP_PREFERENCES_PASSWORD = "APP_PREFERENCES_PASSWORD"
var mSettings: SharedPreferences? = null

class SignUpFragment : Fragment() {

    private var param1: String? = null

    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        mSettings = this.activity?.getSharedPreferences(
            APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val queue = Volley.newRequestQueue(activity?.applicationContext)

        signUpBackButton.setOnClickListener {
            val newFragment: Fragment =
                LoginFragment()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        signUpSendButton.setOnClickListener {
            emailLayout.error = null;
            firstNameLayout.error = null;
            passwordLayout.error = null;
            passwordConfirmLayout.error = null;
            val email = emailEditText.text.toString();
            val password = passwordEditText.text.toString();
            val passwordConfirm = passwordConfirmEditText.text.toString();
            val firstName = firstNameEditText.text.toString();
            val params = JSONObject();
            params.put("email", email);
            params.put("password", password);
            params.put("firstName", firstName);

            if (!emailValidator(email)) emailLayout.error = "Неверный формат Email"
            if (!nameValidator(firstName)) firstNameLayout.error = "Имя содержит недопустимые символы"
            if (!passwordValidator(password)) passwordLayout.error = "Минимум 4 символа"
            if (!passwordValidator(passwordConfirm)) passwordConfirmLayout.error = "Минимум 4 символа"

            if (passwordConfirm != password)
                Toast.makeText(activity?.applicationContext, "Пароли не совпадают", Toast.LENGTH_SHORT).show()

            if (emailValidator(email) && nameValidator(firstName) && passwordValidator(password) && passwordValidator(passwordConfirm) && passwordConfirm == password){
                val handler: Handler = Handler()

                progressBar.visibility = View.VISIBLE
                Thread(Runnable {
                        try {
                            Thread.sleep(3000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    handler.post(Runnable {
                        progressBar.visibility = View.GONE
                        val toast = Toast.makeText(activity?.applicationContext, "Регистрация прошла успешно", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP, 0, 0)
                        toast.show()
                        val editor = mSettings?.edit()
                        editor?.putString(APP_PREFERENCES_FIRSTNAME, firstName)
                        editor?.putString(APP_PREFERENCES_EMAIL, email)
                        editor?.putString(APP_PREFERENCES_PASSWORD, password)
                        editor?.apply()
                        val newFragment: Fragment =
                            LoginFragment()
                        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
                        transaction.replace(R.id.container, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    })
                }).start()
            }
        }

    }
    private fun emailValidator(target: CharSequence?): Boolean {
        return if (target == null) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    private fun nameValidator(_Name: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val name = "^[a-zA-Zа-яА-Я\\\\s]+"
        pattern = Pattern.compile(name)
        matcher = pattern.matcher(_Name)
        return matcher.matches()
    }

    private fun passwordValidator(Password: String): Boolean {
        return Password.length >= 4
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
