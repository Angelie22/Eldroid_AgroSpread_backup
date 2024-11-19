package com.agrospread.agrospread.agrospread.View.Adapters
import com.agrospread.agrospread.agrospread.Model.OfferData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agrospread.agrospread.R

class OfferAdapter(private val OfferList: List<OfferData>, private val context: Context) : RecyclerView.Adapter<OfferAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)
        val image: ImageView = itemView.findViewById(R.id.imageview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.offer_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recommendation = OfferList[position]
        holder.name.text = recommendation.name
        holder.description.text = recommendation.description
        holder.image.setImageResource(recommendation.image)
    }

    override fun getItemCount() = OfferList.size
}
