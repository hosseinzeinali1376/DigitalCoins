package com.example.digitalcoins.view.buysellactivity.buyfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.digitalcoins.R
import com.example.digitalcoins.model.items.Item
import com.example.digitalcoins.model.items.Item_Buy
import com.example.digitalcoins.model.items.coinsitems.User
import com.example.digitalcoins.model.roomdb.MyAppDatabase
import com.example.digitalcoins.viewmodel.buyandsellfragmentviewmodel.buyfragmentviewmodel.BuyFragmentViewModel
import com.example.digitalcoins.viewmodel.factory.ViewModelFactory
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_buy.*
import kotlinx.android.synthetic.main.fragment_buy.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class BuyFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    lateinit var buyFragmentViewModel: BuyFragmentViewModel

    lateinit var autoCompleteTextView_buy: AutoCompleteTextView
    lateinit var datepicker_buy: DatePicker
    lateinit var btn_save_calender_buy: MaterialButton
    lateinit var btn_cancel_calender_buy: MaterialButton
    lateinit var btn_date_buy: MaterialButton
    lateinit var img_background_buy: ImageView
    lateinit var edit_mablagh_buy: TextInputEditText
    lateinit var edit_meghdar_buy: TextInputEditText
    lateinit var txt_mablagh_buy: TextInputLayout
    lateinit var txt_meghdar_buy: TextInputLayout
    var getDate: String? = null
    var sum: String? = null
    var amount: String? = null

    val TAG = "HO3EIN_ZEINALI_NESAZ"


    @Inject
    lateinit var myAppDatabase: MyAppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_buy, container, false)
        autoCompleteTextView_buy = v.findViewById(R.id.autoCompleteTextView_buy)
        val my_Coins = resources.getStringArray(R.array.coins)
        val adapter_coin_buy = ArrayAdapter(requireContext(), R.layout.dropdown_item, my_Coins)
        v.autoCompleteTextView_buy.setAdapter(adapter_coin_buy)
        btn_cancel_calender_buy = v.findViewById(R.id.btn_cancel_calender_buy)
        btn_save_calender_buy = v.findViewById(R.id.btn_save_calender_buy)
        btn_date_buy = v.findViewById(R.id.btn_date_buy)
        img_background_buy = v.findViewById(R.id.img_background_buy)
        edit_mablagh_buy = v.findViewById(R.id.edit_mablagh_buy)
        edit_meghdar_buy = v.findViewById(R.id.edit_meghdar_buy)
        txt_mablagh_buy = v.findViewById(R.id.txt_mablagh_buy)
        txt_meghdar_buy = v.findViewById(R.id.txt_meghdar_buy)
        myAppDatabase.myDao()._getInformation()
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //getDataFromBuyFragmentViewModel()
        btn_save_buy.setOnClickListener {
            add_information_buy()
        }
        btn_date_buy.setOnClickListener {
            datePicker_buy.visibility = View.VISIBLE
            btn_cancel_calender_buy.visibility = View.VISIBLE
            btn_save_calender_buy.visibility = View.VISIBLE
            img_background_buy.visibility = View.VISIBLE
            datePicker_buy()
        }
        btn_cancel_calender_buy.setOnClickListener {
            datePicker_buy.visibility = View.GONE
            btn_cancel_calender_buy.visibility = View.GONE
            btn_save_calender_buy.visibility = View.GONE
            img_background_buy.visibility = View.GONE
        }
        btn_save_calender_buy.setOnClickListener {
            if (getDate != null) {
                Toast.makeText(requireContext(), getDate, Toast.LENGTH_SHORT).show()
                btn_date_buy.text = getDate
                datePicker_buy.visibility = View.GONE
                btn_cancel_calender_buy.visibility = View.GONE
                btn_save_calender_buy.visibility = View.GONE
                img_background_buy.visibility = View.GONE
            } else {
                Toast.makeText(
                    requireContext(),
                    "لطفا تاریخ مد نظر خود را مشخص کنید.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        myAppDatabase.myDao()._getInformation()
    }

    override fun onStart() {
        super.onStart()
        myAppDatabase.myDao()._getInformation()
    }

    override fun onResume() {
        super.onResume()
        myAppDatabase.myDao()._getInformation()
    }

    //----------------------------------------------------------------------------------------------
    fun datePicker_buy() {
        val today = Calendar.getInstance()
        datePicker_buy.init(
            today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { view, year, month, day ->
            val month = month + 1
            getDate = "$year/$month/$day"
        }
    }


    //----------------------------------------------------------------------------------------------
    fun add_information_buy() {
        val sum = edit_mablagh_buy.text.toString()
        val amount = edit_meghdar_buy.text.toString()
        val date = getDate.toString()
        val kind = autoCompleteTextView_buy.text.toString()
        edit_mablagh_buy.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 12) {
                txt_mablagh_buy.error = "بیشتر از 12 کاراکتر است."
            }/*else if (text!!.length < 12){
                txt_mablagh_buy.error = null
            }*/
        }

        edit_meghdar_buy.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 12) {
                txt_meghdar_buy.error = "بیشتر از 12 کاراکتر است."
            }
        }

        if (sum.isNotEmpty()) {
            if (amount.isNotEmpty()) {
                if (date.isNotEmpty()) {
                    if (kind.isNotEmpty()) {
                        val item_buy =
                            Item_Buy(sum, amount, date, kind, 1)
                        //mainActivity.myAppDatabase?.myDao()?._addInformation(item_buy)

                        myAppDatabase.myDao()._addInformation(item_buy)
                        Toast.makeText(requireContext(), "ارز خریداری شد.", Toast.LENGTH_SHORT)
                            .show()

                        /*else {
                            myAppDatabase.myDao()._updateInformation(item_buy)
                            Toast.makeText(requireContext(), "update is ok", Toast.LENGTH_SHORT).show()
                        }*/
                        Log.d(TAG, sum + amount + date + kind)
                        Toast.makeText(
                            requireContext(),
                            sum + " " + amount + " " + date + " " + kind,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "لطفا تمام اطلاعات لازم را پر کنید.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "لطفا تمام اطلاعات لازم را پر کنید.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "لطفا تمام اطلاعات لازم را پر کنید.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                requireContext(),
                "لطفا تمام اطلاعات لازم را پر کنید.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    //----------------------------------------------------------------------------------------------
    fun getDataFromBuyFragmentViewModel() {
        buyFragmentViewModel =
            ViewModelProviders.of(this, vmFactory).get(BuyFragmentViewModel::class.java)
        buyFragmentViewModel.readBuyInformation()
        buyFragmentViewModel.getBuyInformation()
            .observe(viewLifecycleOwner, object : Observer<User> {
                override fun onChanged(t: User?) {
                }
            })
    }

}