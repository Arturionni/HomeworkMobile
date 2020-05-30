package com.example.homeworkmobile.views.personalArea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.homeworkmobile.R
import com.example.homeworkmobile.model.data.Account
import com.example.homeworkmobile.views.personalArea.tabsMainMenu.MainTabFragment
import kotlinx.android.synthetic.main.fragment_replenish.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.math.round

private const val ARG_PARAM1 = "account"

class ReplenishFragment : Fragment(), CoroutineScope {
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var account: Account? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            account = it.getSerializable(ARG_PARAM1) as Account?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_replenish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        replenishButton.setOnClickListener {
            if (replenishValue.text.toString() != "")
            launch {
                val value: PersonalAreaActivity = activity as PersonalAreaActivity
                val valueField: Double? =  replenishValue.text.toString().toDouble()
                if (valueField != null){
                    value.mAccountViewModel.replenishAccount(valueField, account!!)
                    val newFragment: Fragment =
                        MainTabFragment()
                    val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.containerMain, newFragment);
                    transaction.commit();
                }

            }
        }
    }
}
