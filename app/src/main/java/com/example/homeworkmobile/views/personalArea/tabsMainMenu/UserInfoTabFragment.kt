package com.example.homeworkmobile.views.personalArea.tabsMainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homeworkmobile.R
import com.example.homeworkmobile.model.data.User
import kotlinx.android.synthetic.main.fragment_tab_user_info.*

private const val ARG_PARAM1 = "user"

class AccountTabFragment : Fragment() {
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable(ARG_PARAM1) as User?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_user_info, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userName.text = user?.firstName
        userEmail.text = user?.email
    }
}
