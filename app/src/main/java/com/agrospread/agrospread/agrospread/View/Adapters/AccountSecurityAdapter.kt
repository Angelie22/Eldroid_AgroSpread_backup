package com.agrospread.agrospread.agrospread.View.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.agrospread.agrospread.R

class AccountSecurityAdapter(private val context: Context, private val items: List<String>) : BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_account_security, parent, false)
        val title = view.findViewById<TextView>(R.id.title)
        title.text = items[position]
        return view
    }
}