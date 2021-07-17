package com.example.digitalcoins.view.buysellactivity.avragefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.digitalcoins.R
import com.example.digitalcoins.model.roomdb.MyAppDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_average.*
import kotlinx.android.synthetic.main.fragment_average.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.ArrayList
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class AverageFragment : Fragment() {

    @Inject
    lateinit var myAppDatabase: MyAppDatabase
    lateinit var autoCompleteTextView_average: AutoCompleteTextView

    var __kind: String = ""
    var __coinName: String = ""
    var __coinPrice: Double = 0.0
    lateinit var __sum: String
    lateinit var __amount: String

    var price_coin : Double = 0.0

    var mSum: Double = 0.0
    var mAmount: Double = 0.0
    var amount__: Double = 0.0
    var sum__: Double = 0.0

    var mArrayList = ArrayList<Double>()
    var mArrayList2 = ArrayList<Double>()

    var siz_amount: Int = -1
    var siz_sum: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_average, container, false)
        autoCompleteTextView_average = v.findViewById(R.id.autoCompleteTextView_average)
        val my_Coins = resources.getStringArray(R.array.coins)
        val adapter_coin_buy = ArrayAdapter(requireContext(), R.layout.dropdown_item, my_Coins)
        v.autoCompleteTextView_average.setAdapter(adapter_coin_buy)
        v.btn_save_average.setOnClickListener {
            getInformationDaraiViewModel()
            edit_average_answer.visibility = View.VISIBLE
            edit_average_sod.visibility = View.VISIBLE
        }
        return v
    }

    fun getInformationDaraiViewModel() {
        val kind = autoCompleteTextView_average.text.toString()
        if (kind.isNotEmpty()) {
            val info = myAppDatabase.myDao().selectWithName(kind)
            for (i in info) {
                __kind = i.kind.toString()
                __sum = i.amount.toString()
                __amount = i.sum.toString()

                Toast.makeText(requireContext(), __kind + __sum + __amount, Toast.LENGTH_SHORT)
                    .show()
            }
            if (kind.equals(__kind)) {
                val info_tow = myAppDatabase.myDao().selectWithName(kind)
                for (i in info_tow) {
                    amount__ += i.amount!!.toDouble()
                    sum__ += i.sum!!.toDouble()

                    mArrayList.add(amount__)
                    siz_amount = mArrayList.size
                    mArrayList2.add(sum__)
                    siz_sum = mArrayList2.size

                    Toast.makeText(
                        requireContext(),
                        "amount " + mAmount + "sum " + mSum,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                mAmount += amount__
                mSum += sum__

                Log.d("HosseinZeinaliNesaz", "Amount==========>>>" + mAmount.toString())
                Log.d("HosseinZeinaliNesaz", "Sum==========>>>" + mSum.toString())
                Log.d("HosseinZeinaliNesaz", "Size_Amount==========>>>" + siz_amount.toString())
                Log.d("HosseinZeinaliNesaz", "Size_Sum==========>>>" + siz_sum.toString())

                val mAnswer = mSum + mAmount / siz_amount
                val mAnswer_sod = price_coin - mAnswer * siz_amount

                edit_average_answer.setText("")
                edit_average_sod.setText("")

                edit_average_answer.setText(mAnswer.toString())
                edit_average_sod.setText(mAnswer_sod.toString())

                Toast.makeText(requireContext(), "yes", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "داده ای یافت نشد.", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(requireContext(), "لطفا نوع ارز را مشخص کنید.", Toast.LENGTH_SHORT)
                .show()
        }
        val all_coins = myAppDatabase.myDaoSaveCoinPrice()._getCoin()
        for (i in all_coins) {
            __coinName = i.name
            __coinPrice = i.price
        }

        if (kind.equals(__coinName)) {
           val getPriceCoin =  myAppDatabase.myDaoSaveCoinPrice().selectWithName(kind)
            for (ii in getPriceCoin){
                price_coin = ii.price
            }
        }
    }
}