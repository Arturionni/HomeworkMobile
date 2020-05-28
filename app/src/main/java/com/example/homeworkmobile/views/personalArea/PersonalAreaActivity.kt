package com.example.homeworkmobile.views.personalArea

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.homeworkmobile.R
import com.example.homeworkmobile.views.personalArea.tabsMainMenu.AccountTabFragment
import com.example.homeworkmobile.views.personalArea.tabsMainMenu.MainTabFragment
import com.example.homeworkmobile.views.personalArea.tabsMainMenu.TransfersTabFragment
import kotlinx.android.synthetic.main.activity_personal_area.*


class PersonalAreaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_area)
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

