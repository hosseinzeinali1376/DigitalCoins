package com.example.digitalcoins.view.buysellactivity.sellfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.digitalcoins.R
import com.example.digitalcoins.model.items.Item_Buy
import com.example.digitalcoins.model.roomdb.MyAppDatabase
import com.example.digitalcoins.view.holderactivity.HolderActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sell.*
import kotlinx.android.synthetic.main.fragment_sell.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SellFragment : Fragment() {

    lateinit var autoCompleteTextView_sell: AutoCompleteTextView
    lateinit var datePicker_sell: DatePicker
    lateinit var btn_date_sell: MaterialButton
    lateinit var btn_save_calender_sell: MaterialButton
    lateinit var btn_cancel_calender_sell: MaterialButton
    lateinit var img_background_sell: ImageView
    lateinit var edit_mablagh_sell: TextInputEditText
    lateinit var edit_meghdar_sell: TextInputEditText
    lateinit var txt_meghdar_sell: TextInputLayout
    lateinit var txt_mablagh_sell: TextInputLayout
    var getDate: String? = null
    val TAG = "HO3EIN_ZEINALI_NESAZ"

    var mId: Int = 0
    var mStatus: Int = 0
    lateinit var mSum: String
    lateinit var mKind: String
    lateinit var mAmount: String
    lateinit var mDate: String

    lateinit var holderActivity: HolderActivity

    @Inject
    lateinit var myAppDatabase: MyAppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_sell, container, false)
        holderActivity = HolderActivity()
        btn_date_sell = v.findViewById(R.id.btn_date_sell)
        autoCompleteTextView_sell = v.findViewById(R.id.autoCompleteTextView_sell)
        val myCoins = resources.getStringArray(R.array.coins)
        val adapter_coin = ArrayAdapter(requireContext(), R.layout.dropdown_item, myCoins)
        v.autoCompleteTextView_sell.setAdapter(adapter_coin)
        datePicker_sell = v.findViewById(R.id.datePicker_sell)
        btn_cancel_calender_sell = v.findViewById(R.id.btn_cancel_calender_sell)
        btn_save_calender_sell = v.findViewById(R.id.btn_save_calender_sell)
        img_background_sell = v.findViewById(R.id.img_background_sell)
        edit_mablagh_sell = v.findViewById(R.id.edit_mablagh_sell)
        edit_meghdar_sell = v.findViewById(R.id.edit_meghdar_sell)


        if (arguments != null) {
            val _arguments = arguments
            mKind = _arguments!!.getString("_KIND").toString()
            mId = _arguments.getInt("_ID")
            mAmount = _arguments.getString("_AMOUNT").toString()
            mSum = _arguments.getString("_SUM").toString()
            mDate = _arguments.getString("_DATE").toString()
            //mStatus = _arguments.getString("_STATUS")!!.toInt()

            edit_mablagh_sell.setText(mAmount)
            edit_meghdar_sell.setText(mSum)
            btn_date_sell.setText(mDate)
            autoCompleteTextView_sell.setText(mKind)

        } else {
            Toast.makeText(
                requireContext(),
                "ooOOOps!!",
                Toast.LENGTH_SHORT
            ).show()
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_save_sell.setOnClickListener {
            myAppDatabase.myDao()
                ._deleteInformation(Item_Buy(mId, mSum, mAmount, mDate, mKind))

            Toast.makeText(
                requireContext(),
                "ارز شما فروخته شد.",
                Toast.LENGTH_SHORT
            ).show()
        }
        btn_date_sell.setOnClickListener {
            datePicker_sell.visibility = View.VISIBLE
            btn_cancel_calender_sell.visibility = View.VISIBLE
            btn_save_calender_sell.visibility = View.VISIBLE
            img_background_sell.visibility = View.VISIBLE
            datePicker_sell()
        }
        btn_cancel_calender_sell.setOnClickListener {
            datePicker_sell.visibility = View.GONE
            btn_cancel_calender_sell.visibility = View.GONE
            btn_save_calender_sell.visibility = View.GONE
            img_background_sell.visibility = View.GONE
        }
        btn_save_calender_sell.setOnClickListener {
            if (getDate != null) {
                Toast.makeText(requireContext(), getDate, Toast.LENGTH_SHORT).show()
                btn_date_sell.text = getDate
                datePicker_sell.visibility = View.GONE
                btn_cancel_calender_sell.visibility = View.GONE
                btn_save_calender_sell.visibility = View.GONE
                img_background_sell.visibility = View.GONE
            } else {
                Toast.makeText(
                    requireContext(),
                    "لطفا تاریخ مد نظر خود را مشخص کنید.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    //----------------------------------------------------------------------------------------------
    fun datePicker_sell() {
        val today = Calendar.getInstance()
        datePicker_sell.init(
            today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { view, year, month, day ->
            val month = month + 1
            getDate = "$year/$month/$day"
        }
    }

    //----------------------------------------------------------------------------------------------

}