package com.example.digitalcoins.view.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.digitalcoins.R
import com.example.digitalcoins.model.roomdb.MyAppDatabase
import com.example.digitalcoins.view.buysellactivity.daraifragment.DaraiFragment
import com.example.digitalcoins.view.firstfragment.FirstFragment
import com.example.digitalcoins.view.thirdfragment.ThirdFragment
import com.example.digitalcoins.view.tradesfragment.TradesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var tradesFragment: TradesFragment
    lateinit var bottomNavigationView: BottomNavigationView
    var myAppDatabase: MyAppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val firstFragment = FirstFragment()
        openFragment(firstFragment, "First")

        /* myAppDatabase = Room.databaseBuilder(
             applicationContext,
             MyAppDatabase::class.java, "item_information_buy"
         ).allowMainThreadQueries().build()*/

    }

    val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.invest -> {
                    val firstFragment = FirstFragment()
                    openFragment(firstFragment, "First")
                    return@OnNavigationItemSelectedListener true
                }

                R.id.track -> {
                    val tradesFragment = TradesFragment()
                    openFragment(tradesFragment, "Second")
                    return@OnNavigationItemSelectedListener true
                }

                R.id.daraie -> {
                    val daraiFragment = DaraiFragment()
                    openFragment(daraiFragment, "darai")
                    return@OnNavigationItemSelectedListener true
                }

                R.id.news -> {
                    val thirdFragment = ThirdFragment()
                    openFragment(thirdFragment, "Third")
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    fun openFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment, tag)
        //transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
    }

}