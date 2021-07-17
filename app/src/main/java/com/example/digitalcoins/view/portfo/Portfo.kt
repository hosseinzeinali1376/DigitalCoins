package com.example.digitalcoins.view.portfo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcoins.R
import com.example.digitalcoins.model.items.Item
import com.example.digitalcoins.model.items.coinsitems.User
import com.example.digitalcoins.viewmodel.factory.ViewModelFactory
import com.example.digitalcoins.viewmodel.portfoviewmodel.PortfoViewModel
import com.example.digitalcoins.viewmodel.portfoviewmodel.portfoadapter.PortfoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_portfo.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class Portfo : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    lateinit var portfoViewModel: PortfoViewModel

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: PortfoAdapter
    lateinit var recyclerView_portfo: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfo)
        recyclerView_portfo = findViewById(R.id.recyclerView_portfo)
        recyclerView_portfo.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView_portfo.layoutManager = layoutManager
        //getInformation()
    }

    //----------------------------------------------------------------------------------------------
    fun getInformation() {
        linearProgressIndicator_portfo.visibility = View.VISIBLE
        portfoViewModel = ViewModelProviders.of(this, vmFactory).get(PortfoViewModel::class.java)
        portfoViewModel.setPortfoMutableLiveData()
        portfoViewModel.getProtfoLiveData().observe(this, object : Observer<User> {
            override fun onChanged(t: User?) {
            /*    val data = t as MutableList<User>
                adapter = PortfoAdapter(baseContext, data)
                adapter.notifyDataSetChanged()
                recyclerView_portfo.adapter = adapter
                linearProgressIndicator_portfo.visibility = View.GONE*/
            }
        })
    }
}