package com.example.homeworkmobile.views.personalArea.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkmobile.R
import com.example.homeworkmobile.model.data.Account
import kotlin.math.round

class AccountsListAdapter(
    listArray: List<Account>?,
    fragment: Fragment
) : RecyclerView.Adapter<AccountsListAdapter.AccountViewHolder>() {

    var mAdapterCallback: AdapterCallback? = null
    var list = ArrayList<Account>()

    init {
        this.mAdapterCallback = fragment as AdapterCallback
        this.list = listArray as ArrayList<Account>
    }

    class AccountViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.fragment_account_card, parent, false)) {
        private var number: TextView? = null
        private var balance: TextView? = null
        init {
            number = itemView.findViewById(R.id.accountNumberCard)
            balance = itemView.findViewById(R.id.accountBalanceCard)
        }
        fun bind(account: Account) {
            number?.text = account.accountNumber
            balance?.text = ((round(account.accountBalance!! * 100) / 100).toString())
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AccountViewHolder {
        val view = LayoutInflater.from(parent.context)
        return AccountViewHolder(
            view,
            parent
        )
    }

    fun updateAccounts(accounts: List<Account>) {
        list = ArrayList(accounts)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account: Account = list[position]
        holder.itemView.setOnClickListener {
            mAdapterCallback?.onCardClick(account)
        }
        holder.bind(account)
    }
    override fun getItemCount() = list.size
}