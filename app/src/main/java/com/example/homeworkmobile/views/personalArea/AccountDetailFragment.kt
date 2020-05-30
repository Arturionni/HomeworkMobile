package com.example.homeworkmobile.views.personalArea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.homeworkmobile.R
import com.example.homeworkmobile.model.data.Account
import com.example.homeworkmobile.views.personalArea.tabsMainMenu.MainTabFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_account_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AccountDetailFragment : Fragment(), CoroutineScope {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private var job: Job = Job()
    private var account: Account? = null
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = this.arguments

        if (bundle != null) {
            account = bundle.getSerializable("account") as Account?
        }
        return inflater.inflate(R.layout.fragment_account_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        accountBalanceDetail.text = account?.accountBalance.toString()
        accountNumberDetail.text = account?.accountNumber
        dateCreatedDetail.text = account?.dateCreated

        closeAccountButton.setOnClickListener {
            MaterialAlertDialogBuilder(this.activity)
                .setTitle("Закрыть счет")
                .setMessage("Вы точно хотите закрыть счет?")
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    launch {
                        val value: PersonalAreaActivity = activity as PersonalAreaActivity
                        value.mAccountViewModel.closeAccount(account?.id!!)
                        val newFragment: Fragment =
                            MainTabFragment()
                        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
                        transaction.replace(R.id.containerMain, newFragment);
                        transaction.commit();
                    }
                }
                .show()
        }
    }
}
