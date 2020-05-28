package com.example.homeworkmobile.views.personalArea.tabsMainMenu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkmobile.R
import com.example.homeworkmobile.views.personalArea.Account
import com.example.homeworkmobile.views.personalArea.adapters.AccountsListAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_tab_main.*
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

var accounts:MutableList<Account> = mutableListOf(
)
class MainTabFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab.setOnClickListener {
            MaterialAlertDialogBuilder(this.activity)
                .setTitle(resources.getString(R.string.title))
                .setMessage(resources.getString(R.string.supporting_text))
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    val accountNumber = (0..1000000000).random() + 4000000000
                    accounts.add(
                        Account(
                            accountNumber.toString(),
                            0
                        )
                    )
                    viewAdapter.notifyDataSetChanged()
                    Log.d("TAG", accounts.toString())
                }
                .show()
        }
        viewManager = LinearLayoutManager(this.activity)
        viewAdapter =
            AccountsListAdapter(
                accounts
            )

        recyclerView = this.activity?.findViewById<RecyclerView>(R.id.my_recycler_view)?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }!!
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainTabFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
