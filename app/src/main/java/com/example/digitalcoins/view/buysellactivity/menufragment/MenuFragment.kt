package com.example.digitalcoins.view.buysellactivity.menufragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.digitalcoins.R
import com.example.digitalcoins.view.buysellactivity.avragefragment.AverageFragment
import com.example.digitalcoins.view.buysellactivity.daraifragment.DaraiFragment
import com.example.digitalcoins.view.buysellactivity.listofpurchasedcurrencies.ListOfPurchasedCurrencies
import com.google.android.material.button.MaterialButton



class MenuFragment : Fragment() {

    lateinit var mBtn__sell: MaterialButton
    lateinit var mBtn__allList: MaterialButton
    lateinit var mBtn__myanginVAsod: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_menu, container, false)

        mBtn__sell = v.findViewById(R.id.mBtn__sell)
        //mBtn__allList = v.findViewById(R.id.mBtn__allList)
        mBtn__myanginVAsod = v.findViewById(R.id.mBtn__myanginVAsod)

        mBtn__sell.setOnClickListener {
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.mFrameLayout, ListOfPurchasedCurrencies())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        mBtn__allList.setOnClickListener {
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.mFrameLayout, DaraiFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        mBtn__myanginVAsod.setOnClickListener {
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.mFrameLayout, AverageFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        return v
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }



}