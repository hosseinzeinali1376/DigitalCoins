package com.example.digitalcoins.view.buysellactivity

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.digitalcoins.R
import com.example.digitalcoins.view.buysellactivity.avragefragment.AverageFragment
import com.example.digitalcoins.view.buysellactivity.buyfragment.BuyFragment
import com.example.digitalcoins.view.buysellactivity.listofpurchasedcurrencies.ListOfPurchasedCurrencies
import com.example.digitalcoins.view.buysellactivity.menufragment.MenuFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class Buy_Sell_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy__sell)

        val tab_viewpager = findViewById<ViewPager>(R.id.viewPager)
        val tab_tablayout = findViewById<TabLayout>(R.id.tabLayout)
        setupViewPager(tab_viewpager)
        tab_tablayout.setupWithViewPager(tab_viewpager)

    }

    class ViewPagerAdapter : FragmentPagerAdapter {

        private  var fragmentList1: ArrayList<Fragment> = ArrayList()
        private  var fragmentTitleList1: ArrayList<String> = ArrayList()

        constructor(supportFragmentManager: FragmentManager)
                : super(supportFragmentManager)

        override fun getItem(position: Int): Fragment {
            return fragmentList1.get(position)
        }

        @Nullable
        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList1.get(position)
        }

        override fun getCount(): Int {
            return fragmentList1.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList1.add(fragment)
            fragmentTitleList1.add(title)
        }
    }

    private fun setupViewPager(viewpager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(BuyFragment(), "خرید")
        adapter.addFragment(ListOfPurchasedCurrencies(), "فروش")
        adapter.addFragment(AverageFragment(), "سود و زیان")
        //adapter.addFragment(DaraiFragment(), "دارایی")

        viewpager.setAdapter(adapter)
    }
}