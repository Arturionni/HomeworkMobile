package com.example.homeworkmobile.views.personalArea

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction

import com.example.homeworkmobile.R
import com.example.homeworkmobile.model.data.Account
import com.example.homeworkmobile.views.personalArea.tabsMainMenu.MainTabFragment
import kotlinx.android.synthetic.main.fragment_transfer.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

private const val ARG_PARAM1 = "account"

class TransferFragment : Fragment(), CoroutineScope {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transfer, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        transferButton.setOnClickListener {
            if (transferAccountNumberEditText.text.toString() != "" && transferValueEditText.text.toString() != ""){
                launch {
                    val value: PersonalAreaActivity = activity as PersonalAreaActivity
                    val valueField: Double =  transferValueEditText.text.toString().toDouble()
                    val accountNumberField: String =  transferAccountNumberEditText.text.toString()
                    val receiverAccount: Account? = value.mAccountViewModel.getAccount(accountNumberField)
                    if (receiverAccount != null) {
                        if (valueField <= account?.accountBalance!!) {
                            value.mAccountViewModel.replenishAccount(valueField, receiverAccount)
                            value.mAccountViewModel.transferAccount(valueField, account!!)
                            val newFragment: Fragment =
                                MainTabFragment()
                            val transaction: FragmentTransaction =
                                fragmentManager!!.beginTransaction()
                            transaction.replace(R.id.containerMain, newFragment);
                            transaction.commit();
                            val toast = Toast.makeText(
                                activity?.applicationContext,
                                "Перевод прошел успешно!",
                                Toast.LENGTH_SHORT
                            )
                            toast.setGravity(Gravity.TOP, 0, 0)
                            toast.show()
                        }
                        else {
                            val toast = Toast.makeText(activity?.applicationContext, "Недостаточно средств для перевода!", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.TOP, 0, 0)
                            toast.show()
                        }
                    }
                    else {
                        val toast = Toast.makeText(activity?.applicationContext, "Такого счета не существует!", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP, 0, 0)
                        toast.show()
                    }

                }
            }
        }
    }
}
