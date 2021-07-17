package com.example.digitalcoins.view.buysellactivity.daraifragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcoins.R
import com.example.digitalcoins.model.roomdb.MyAppDatabase
import com.example.digitalcoins.viewmodel.buyandsellfragmentviewmodel.daraifragmentviewmodel.adapter.DaraiAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DaraiFragment : Fragment() {

    @Inject
    lateinit var myAppDatabase: MyAppDatabase

    lateinit var layoutManager_darai: LinearLayoutManager
    lateinit var adapter_darai: DaraiAdapter
    lateinit var recyclerView_darai: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_darai, container, false)
        recyclerView_darai = v.findViewById(R.id.recyclerview_daraiFragment)
        recyclerView_darai.setHasFixedSize(true)
        layoutManager_darai = LinearLayoutManager(requireContext())
        recyclerView_darai.layoutManager = layoutManager_darai
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getInformationDaraiViewModel()
    }

    //----------------------------------------------------------------------------------------------
    fun getInformationDaraiViewModel() {
        val data_darai = myAppDatabase.myDao()._getInformation()
        adapter_darai = DaraiAdapter(requireContext(), data_darai as MutableList)
        adapter_darai.notifyDataSetChanged()
        recyclerView_darai.adapter = adapter_darai
    }
}