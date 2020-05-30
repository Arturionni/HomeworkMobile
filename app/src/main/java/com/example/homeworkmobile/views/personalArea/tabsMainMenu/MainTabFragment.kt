package com.example.homeworkmobile.views.personalArea.tabsMainMenu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkmobile.R
import com.example.homeworkmobile.model.data.Account
import com.example.homeworkmobile.views.personalArea.AccountDetailFragment
import com.example.homeworkmobile.views.personalArea.PersonalAreaActivity
import com.example.homeworkmobile.views.personalArea.adapters.AccountsListAdapter
import com.example.homeworkmobile.views.personalArea.adapters.AdapterCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_tab_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


@Suppress("UNCHECKED_CAST")
class MainTabFragment : Fragment(), CoroutineScope,
    AdapterCallback {

    private lateinit var recyclerView: RecyclerView
    private var viewAdapter =  AccountsListAdapter( ArrayList(), this)
    private lateinit var viewManager: RecyclerView.LayoutManager

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewManager = LinearLayoutManager(this.activity)

        launch {
            val value: PersonalAreaActivity = activity as PersonalAreaActivity
            value.mAccountViewModel.getAccountsFromDb(value.user.id!!).observe(value, Observer {
                viewAdapter.updateAccounts(it as List<Account>)
            })
        }

        fab.setOnClickListener {
            MaterialAlertDialogBuilder(this.activity)
                .setTitle(resources.getString(R.string.title))
                .setMessage(resources.getString(R.string.supporting_text))
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    launch {
                        val value: PersonalAreaActivity = activity as PersonalAreaActivity
                        value.mAccountViewModel.getAccountsFromDb(value.user.id!!).observe(value, Observer {
                            list -> (
                                list.forEach {Log.d("CoUNT", it.toString()) }
                                )
                        })
                        value.user.id?.let { value.mAccountViewModel.addAccountToDB(it) }
                        viewAdapter.notifyDataSetChanged()
                    }
                }
                .show()
        }

        recyclerView = this.activity?.findViewById<RecyclerView>(R.id.my_recycler_view)?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }!!
    }

    override fun onCardClick(account: Account) {
        val newFragment: Fragment =
            AccountDetailFragment()
        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        val bundle = Bundle()
        bundle.putSerializable("account", account)
        newFragment.arguments = bundle
        transaction.replace(R.id.containerMain, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
