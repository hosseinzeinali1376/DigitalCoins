package com.example.digitalcoins.viewmodel.buyandsellfragmentviewmodel.listofpurchasedcurrenciesviewmodel.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcoins.R
import com.example.digitalcoins.model.items.Item_Buy
import com.example.digitalcoins.view.holderactivity.HolderActivity


class AdapterPurchasedCurrencies constructor(
    val ctx: Context,
    val list_purchased: MutableList<Item_Buy>
    /* val itemClick: ItemClick*/
) :
    RecyclerView.Adapter<AdapterPurchasedCurrencies.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(ctx).inflate(R.layout.cardview_purchased, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list_purchased_position = list_purchased.get(position)

        val ststus_ = list_purchased_position.status
        holder.txt_noearz_purchased.text = list_purchased_position.kind
        holder.txt_mablaghe_purchased.text = list_purchased_position.amount
        holder.txt_meghdare_purchased.text = list_purchased_position.sum
        holder.tarikh_purchased.text = list_purchased_position.date
        holder.itemView.setOnClickListener {
            //itemClick.onItemClick(list_purchased_position)

            val i = Intent(ctx, HolderActivity::class.java)
            i.putExtra("ID", list_purchased_position.id)
            i.putExtra("KIND", list_purchased_position.kind)
            i.putExtra("AMOUNT", list_purchased_position.amount)
            i.putExtra("SUM", list_purchased_position.sum)
            i.putExtra("DATE", list_purchased_position.date)
            i.putExtra("STATUS", ststus_)
            ctx.startActivity(i)
        }
    }


    override fun getItemCount(): Int {
        return list_purchased.size
    }

    //----------------------------------------------------------------------------------------------
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_noearz_purchased: TextView
        var txt_mablaghe_purchased: TextView
        var txt_meghdare_purchased: TextView
        var tarikh_purchased: TextView

        init {
            txt_noearz_purchased = itemView.findViewById(R.id.txt_noearz_purchased)
            txt_mablaghe_purchased = itemView.findViewById(R.id.txt_mablaghe_purchased)
            txt_meghdare_purchased = itemView.findViewById(R.id.txt_meghdare_purchased)
            tarikh_purchased = itemView.findViewById(R.id.tarikh_purchased)

        }

    }

    public interface ItemClick {
        public fun onItemClick(item_buy: Item_Buy)
    }

}