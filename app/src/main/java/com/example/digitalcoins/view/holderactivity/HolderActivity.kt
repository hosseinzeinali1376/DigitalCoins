package com.example.digitalcoins.view.holderactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import com.example.digitalcoins.R
import com.example.digitalcoins.view.buysellactivity.sellfragment.SellFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sell.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HolderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holder)

        /*  val fragmentManager: FragmentManager = activity!!.supportFragmentManager
          val fragmentTransaction: FragmentTransaction = getFragmentManager()!!.beginTransaction()
          fragmentTransaction.add(R.id.mFrameLayout, SellFragment())
          fragmentTransaction.addToBackStack(null)
          fragmentTransaction.commit()*/

        val i = intent
        val _ID = i.getIntExtra("ID", 0)
        val _KIND = i.getStringExtra("KIND")
        val _AMOUNT = i.getStringExtra("AMOUNT")
        val _SUM = i.getStringExtra("SUM")
        val _DATE = i.getStringExtra("DATE")
        val _STATUS = i.getIntExtra("STATUS", -1)

        Log.d("HO3IN", _KIND.toString())

        val bundle = Bundle()
        bundle.putInt("_ID", _ID)
        bundle.putString("_KIND", _KIND)
        bundle.putString("_AMOUNT", _AMOUNT)
        bundle.putString("_SUM", _SUM)
        bundle.putString("_DATE", _DATE)
        bundle.putInt("_STATUS", _STATUS)

        val sellFragment = SellFragment()
        sellFragment.arguments = bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.holder_fragment, sellFragment)
        fragmentTransaction.commit()
    }

}