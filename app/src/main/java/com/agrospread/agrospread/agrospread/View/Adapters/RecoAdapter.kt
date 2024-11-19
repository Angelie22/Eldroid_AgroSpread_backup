package com.agrospread.agrospread.agrospread.View.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.Model.RecoData

class RecoAdapter(private val movieData: List<RecoData>, private val context: Context) : RecyclerView.Adapter<RecoAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)
        val image: ImageView = itemView.findViewById(R.id.imageview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reco_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recommendation = movieData[position]
        holder.name.text = recommendation.name
        holder.description.text = recommendation.description
        holder.image.setImageResource(recommendation.image)
    }

    override fun getItemCount() = movieData.size
}
