package com.example.digitalcoins.viewmodel.portfoviewmodel.portfoadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcoins.R
import com.example.digitalcoins.model.items.Item
import com.example.digitalcoins.model.items.coinsitems.User
import com.squareup.picasso.Picasso

class PortfoAdapter(val ctx: Context, val portfoList: MutableList<User>) :
    RecyclerView.Adapter<PortfoAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(ctx).inflate(R.layout.cardview_portfo, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val portfoList_position = portfoList.get(position)
     /*   Picasso.get().load(portfoList_position.imageurl).into(holder.image_portfo)
        holder.txtName_portfo.text = portfoList_position.name
        holder.txtLastName_portfo.text = portfoList_position.team
        holder.txtPrice_portfo.text= portfoList_position.createdby*/
    }

    override fun getItemCount(): Int {
        return portfoList.size
    }

    //----------------------------------------------------------------------------------------------
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image_portfo: ImageView
        var txtName_portfo: TextView
        var txtLastName_portfo: TextView
        var txtPrice_portfo: TextView

        init {
            image_portfo = itemView.findViewById(R.id.image_portfo)
            txtName_portfo = itemView.findViewById(R.id.txtName_portfo)
            txtLastName_portfo = itemView.findViewById(R.id.txtLastName_portfo)
            txtPrice_portfo = itemView.findViewById(R.id.txtPrice_portfo)
        }
    }
}