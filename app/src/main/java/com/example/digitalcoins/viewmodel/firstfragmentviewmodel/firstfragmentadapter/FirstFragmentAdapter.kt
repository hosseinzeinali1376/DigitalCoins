package com.example.digitalcoins.viewmodel.firstfragmentviewmodel.firstfragmentadapter

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Color.parseColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcoins.R
import com.example.digitalcoins.model.items.Item
import com.example.digitalcoins.model.items.coinsitems.User
import com.example.digitalcoins.view.loadmore.LoadMore
import com.squareup.picasso.Picasso
import java.util.ArrayList

class FirstFragmentAdapter(
  /*  recyclerView: RecyclerView,*/
    internal val context: Context,
    var coinList: ArrayList<User>,
    val listImage: ArrayList<Int>,
    val coinName: ArrayList<String>
) :
    RecyclerView.Adapter<FirstFragmentAdapter.MyViewHolder>() {


    var loadMore: LoadMore? = null
    var isLoading: Boolean = false
    lateinit var activity: Activity
    var visibleThreshold: Int = 5
    var lasteVisibleItem: Int = 0
    var totalItemCount: Int = 0

/*    init {
        val linearLayout = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = linearLayout.findLastVisibleItemPosition()
                if (!isLoading && totalItemCount <= lasteVisibleItem + visibleThreshold) {
                    if (loadMore != null) {
                        loadMore!!.onLoadMore()
                        isLoading = true
                    }
                }
            }
        })
    }*/

     /* fun setLoadMore(loadMore: LoadMore) {
          this.loadMore = loadMore
      }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.cardview_mainactivity, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val coinsModel = coinList[position].raw.btc.usd

        Picasso.get().load(listImage[position]).into(holder.image_main)
        //Picasso.get().load(movieList[position].imageurl).into(holder.image_main)
        holder.txt_name_main.text = coinName[position]
        holder.txt_price_main.text = coinList[position].raw.btc.usd.price.toString()
        holder.txt_darsad.text = coinList[position].raw.btc.usd.change24hour.toString()
        holder.txt_midel_name_main.text = coinList[position].raw.btc.usd.symbole

        //set Color
        holder.txt_darsad.setTextColor(
            if (coinsModel.change24hour.toString().contains("-"))
                Color.parseColor("#FF0000")
            else
                Color.parseColor("#32CD32")
        )

    }

    fun setLoaded(){
        isLoading = false
    }

    fun updateData(coinList: ArrayList<User>){
        this.coinList = coinList
        notifyDataSetChanged()
    }

    //----------------------------------------------------------------------------------------------
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image_main: ImageView
        var txt_name_main: TextView
        var txt_price_main: TextView
        var txt_darsad: TextView
        var txt_midel_name_main: TextView
        //var progressBar: ProgressBar
        //var btn_add_firstfragment: Button

        init {
            image_main = itemView.findViewById(R.id.image_main)
            txt_name_main = itemView.findViewById(R.id.txt_name_main)
            txt_price_main = itemView.findViewById(R.id.txt_price_main)
            txt_darsad = itemView.findViewById(R.id.txt_darsad)
            txt_midel_name_main = itemView.findViewById(R.id.txt_midel_name_main)
            //progressBar = itemView.findViewById(R.id.progressBar)
            //btn_add_firstfragment = itemView.findViewById(R.id.btn_add_firstfragment)
        }
    }
}
