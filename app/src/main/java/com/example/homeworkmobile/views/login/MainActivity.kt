package com.example.homeworkmobile.views.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.LifecycleOwner
import com.example.homeworkmobile.R
import com.example.homeworkmobile.viewmodel.UserViewModel
import com.example.homeworkmobile.viewmodel.UserViewModelFactory
import com.example.homeworkmobile.views.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@Suppress("DEPRECATION")
class MainActivity : BaseActivity(), CoroutineScope {

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private lateinit var lifecycleOwner: LifecycleOwner

    @Inject
    lateinit var mUserViewModelFactory: UserViewModelFactory
    lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        lifecycleOwner = this

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mUserViewModel = ViewModelProviders.of(this, mUserViewModelFactory).get(
            UserViewModel::class.java
        )

        if (savedInstanceState == null) {
            val newFragment: Fragment =
                LoginFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, newFragment)
            transaction.commit()
        }
    }
}