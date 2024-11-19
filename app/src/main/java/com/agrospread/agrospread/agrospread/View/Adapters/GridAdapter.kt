package com.agrospread.agrospread.agrospread.View.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.agrospread.agrospread.R


class GridAdapter(var context: Context, var flowerName: Array<String>, var image: IntArray) :
    BaseAdapter() {
    var inflater: LayoutInflater? = null

    override fun getCount(): Int {
        return flowerName.size
    }

    override fun getItem(position: Int): Any {
        return flowerName[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (inflater == null) {
            inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = inflater!!.inflate(R.layout.list_item, null)
        }
        val listImage1 = convertView!!.findViewById<ImageView>(R.id.listImage1)
        listImage1.setImageResource(image[position])
        return convertView
    }
}

