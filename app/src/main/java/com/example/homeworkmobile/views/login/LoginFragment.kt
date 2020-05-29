package com.example.homeworkmobile.views.login

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.homeworkmobile.R
import com.example.homeworkmobile.utils.Constants
import com.example.homeworkmobile.utils.emailValidator
import com.example.homeworkmobile.utils.passwordValidator
import com.example.homeworkmobile.views.personalArea.PersonalAreaActivity
import kotlinx.android.synthetic.main.fragment_login.*
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
            if (email != "" && password != "" && emailValidator(email) && passwordValidator(password))
            launch{
                val value: MainActivity = activity as MainActivity
                value.mUserViewModel.getUserFromDb(email).observe(value, Observer { user -> (
                    if (user != null ){
                        if (user.password == password){
                            val intent = Intent(activity, PersonalAreaActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            val b = Bundle()
                            b.putSerializable("user", user)
                            intent.putExtras(b)
                            startActivity(intent)
                        }
                        else{
                            val toast = Toast.makeText(activity?.applicationContext, "Неверный пароль!", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.TOP, 0, 0)
                            toast.show()
                        }
                    }
                    else
                    {
                        val toast = Toast.makeText(activity?.applicationContext, "Пользователя с таким email не существует!", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP, 0, 0)
                        toast.show()
                    }
                )
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
