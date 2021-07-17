package com.example.digitalcoins.viewmodel.buyandsellfragmentviewmodel.daraifragmentviewmodel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcoins.R
import com.example.digitalcoins.model.items.Item_Buy

class DaraiAdapter (val ctx: Context, val daraiList: MutableList<Item_Buy>) :
    RecyclerView.Adapter<DaraiAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(ctx).inflate(R.layout.cardview_darais, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val daraiList_position = daraiList.get(position)

        holder.txt_noearz_darai.text= daraiList_position.kind
        holder.txt_mablaghe_darai.text= daraiList_position.amount
        holder.txt_meghdare_darai.text= daraiList_position.sum
        holder.tarikh_darai.text= daraiList_position.date
    }

    override fun getItemCount(): Int {
        return daraiList.size
    }

    //----------------------------------------------------------------------------------------------
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_noearz_darai: TextView
        var txt_mablaghe_darai: TextView
        var txt_meghdare_darai: TextView
        var tarikh_darai: TextView

        init {

            txt_noearz_darai = itemView.findViewById(R.id.txt_noearz_darai)
            txt_mablaghe_darai = itemView.findViewById(R.id.txt_mablaghe_darai)
            txt_meghdare_darai = itemView.findViewById(R.id.txt_meghdare_darai)
            tarikh_darai = itemView.findViewById(R.id.tarikh_darai)
        }
    }

}