package com.agrospread.agrospread.agrospread.View.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.agrospread.agrospread.R

class BankAccountsAdapter(private val context: Context, private val accounts: List<BankAccount>) : BaseAdapter() {

    data class BankAccount(val iconResId: Int, val name: String)

    override fun getCount(): Int = accounts.size

    override fun getItem(position: Int): Any = accounts[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_bank_accounts, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val account = accounts[position]
        holder.icon.setImageResource(account.iconResId)
        holder.name.text = account.name

        return view
    }

    private class ViewHolder(view: View) {
        val icon: ImageView = view.findViewById(R.id.card_icon)
        val name: TextView = view.findViewById(R.id.card_name)
    }
}