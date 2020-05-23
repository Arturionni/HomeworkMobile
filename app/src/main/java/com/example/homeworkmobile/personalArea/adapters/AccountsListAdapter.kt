package com.example.homeworkmobile.personalArea.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkmobile.personalArea.Account
import com.example.homeworkmobile.R

internal class AccountsListAdapter(
    private val list: List<Account>
) :
    RecyclerView.Adapter<AccountsListAdapter.AccountViewHolder>() {
    class AccountViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.fragment_account_card, parent, false)) {
        private var number: TextView? = null
        private var balance: TextView? = null
        init {
            number = itemView.findViewById(R.id.accountNumberCard)
            balance = itemView.findViewById(R.id.accountBalanceCard)
        }
        fun bind(account: Account) {
            number?.text = account.number
            balance?.text = account.balance.toString()
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

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account: Account = list[position]
        holder.bind(account)
    }
    override fun getItemCount() = list.size
}