package com.example.homeworkmobile.views.personalArea

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.example.homeworkmobile.R
import com.example.homeworkmobile.model.data.User
import com.example.homeworkmobile.viewmodel.AccountViewModel
import com.example.homeworkmobile.viewmodel.AccountViewModelFactory
import com.example.homeworkmobile.viewmodel.UserViewModel
import com.example.homeworkmobile.viewmodel.UserViewModelFactory
import com.example.homeworkmobile.views.BaseActivity
import com.example.homeworkmobile.views.personalArea.tabsMainMenu.AccountTabFragment
import com.example.homeworkmobile.views.personalArea.tabsMainMenu.MainTabFragment
import com.example.homeworkmobile.views.personalArea.tabsMainMenu.TransfersTabFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_personal_area.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class PersonalAreaActivity : BaseActivity(), CoroutineScope {
    lateinit var user: User

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    private lateinit var lifecycleOwner: LifecycleOwner

    @Inject
    lateinit var mAccountViewModelFactory: AccountViewModelFactory
    lateinit var mAccountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        lifecycleOwner = this

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_area)
        mAccountViewModel = ViewModelProviders.of(this, mAccountViewModelFactory).get(
            AccountViewModel::class.java
        )
        user = intent.getSerializableExtra("user") as User

        Log.d("USER", user.toString())
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            val newFragment: Fragment =
                MainTabFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.containerMain, newFragment)
            transaction.commit()
        }

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    val newFragment: Fragment =
                        MainTabFragment()
                    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.containerMain, newFragment)
                    transaction.commit()
                    true
                }
                R.id.page_2 -> {
                    val newFragment: Fragment =
                        TransfersTabFragment()
                    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.containerMain, newFragment)
                    transaction.commit()
                    true
                }
                R.id.page_3 -> {
                    val newFragment: Fragment =
                        AccountTabFragment()
                    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.containerMain, newFragment)
                    transaction.commit()
                    true
                }
                else -> false
            }
        }


    }
}

