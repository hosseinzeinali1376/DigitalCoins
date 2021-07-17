package com.example.digitalcoins.view.buysellactivity.listofpurchasedcurrencies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcoins.R
import com.example.digitalcoins.model.roomdb.MyAppDatabase
import com.example.digitalcoins.viewmodel.buyandsellfragmentviewmodel.listofpurchasedcurrenciesviewmodel.adapter.AdapterPurchasedCurrencies
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ListOfPurchasedCurrencies : Fragment()/*, AdapterPurchasedCurrencies.ItemClick*/ {

    @Inject
    lateinit var myAppDatabase: MyAppDatabase

    lateinit var layoutManager_PurchasedCurrencies: LinearLayoutManager
    lateinit var adapter_PurchasedCurrencies: AdapterPurchasedCurrencies
    lateinit var recyclerView_PurchasedCurrencies: RecyclerView

    val TAG = "ListOfPurchasedCurrencies"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_list_of_purchased_currencies, container, false)
        recyclerView_PurchasedCurrencies = v.findViewById(R.id.recyclerView_purchasedCurrencies)
        recyclerView_PurchasedCurrencies.setHasFixedSize(true)
        layoutManager_PurchasedCurrencies = LinearLayoutManager(requireContext())
        recyclerView_PurchasedCurrencies.layoutManager = layoutManager_PurchasedCurrencies
        getInformationPurchasedCurrencies()
        Log.d("TAG", "onCreateView................")
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getInformationPurchasedCurrencies()
        Log.d("TAG", "onActivityCreated................")
    }

    //----------------------------------------------------------------------------------------------
    fun getInformationPurchasedCurrencies() {
        val data_PurchasedCurrencies = myAppDatabase.myDao()._getInformation()
        for (data in data_PurchasedCurrencies) {
        }
        Log.d("TAG", "it is ok......")
        adapter_PurchasedCurrencies =
            AdapterPurchasedCurrencies(
                requireContext(),
                data_PurchasedCurrencies as MutableList
                /* this*/
            )
        adapter_PurchasedCurrencies.notifyDataSetChanged()
        recyclerView_PurchasedCurrencies.adapter = adapter_PurchasedCurrencies
    }

/*    override fun onItemClick(item_buy: Item_Buy) {
        val i = Intent(requireContext(), HolderActivity::class.java)
        startActivity(i)
    }*/
}