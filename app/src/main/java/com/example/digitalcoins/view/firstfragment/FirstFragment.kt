package com.example.digitalcoins.view.firstfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcoins.R
import com.example.digitalcoins.model.items.Item_Coin
import com.example.digitalcoins.model.items.coinsitems.BTC
import com.example.digitalcoins.model.items.coinsitems.RAW
import com.example.digitalcoins.model.items.coinsitems.USD
import com.example.digitalcoins.model.items.coinsitems.User
import com.example.digitalcoins.model.roomdb.MyAppDatabase
import com.example.digitalcoins.view.buysellactivity.Buy_Sell_Activity
import com.example.digitalcoins.viewmodel.factory.ViewModelFactory
import com.example.digitalcoins.viewmodel.firstfragmentviewmodel.FirstFragmentViewModel
import com.example.digitalcoins.viewmodel.firstfragmentviewmodel.firstfragmentadapter.FirstFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FirstFragment : Fragment()/*, LoadMore*/ {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    lateinit var firstFragmentViewModel: FirstFragmentViewModel

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: FirstFragmentAdapter
    lateinit var recyclerView_mainActivity: RecyclerView

    //lateinit var dialog: AlertDialog

    var listView: ArrayList<User> = ArrayList<User>()
    lateinit var disPlay: ArrayList<User>//for search view

    val arrayList_image: ArrayList<Int> = ArrayList<Int>()

    val coinName: ArrayList<String> = ArrayList<String>()
    val coinName_searchView: ArrayList<User> = ArrayList<User>()

    var listView000: ArrayList<User> = ArrayList<User>()
    var disPlay000: ArrayList<User> = ArrayList<User>()

    @Inject
    lateinit var myAppDatabase: MyAppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_first, container, false)
        recyclerView_mainActivity = v.findViewById(R.id.recyclerView_mainActivity)
        recyclerView_mainActivity.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView_mainActivity.layoutManager = layoutManager

        //set icon for coins
        arrayList_image.add(R.drawable.btc)
        arrayList_image.add(R.drawable.ethc)
        arrayList_image.add(R.drawable.busd)
        arrayList_image.add(R.drawable.xrp)
        arrayList_image.add(R.drawable.xlm)
        arrayList_image.add(R.drawable.ltc)
        arrayList_image.add(R.drawable.ethc)
        arrayList_image.add(R.drawable.doge)
        arrayList_image.add(R.drawable.eos)
        arrayList_image.add(R.drawable.busd)

        coinName.add("بیت کوین")
        coinName.add("اتریوم")
        coinName.add("بایننس کوین")
        coinName.add("ریپل")
        coinName.add("استلار")
        coinName.add("لایت کوین")
        coinName.add("اتریم کلاسیک")
        coinName.add("دوج کوین")
        coinName.add("اییاس")
        coinName.add("بایننس یو اس دی")

        //dialog = SpotsDialog.Builder().setCancelable(false).setContext(requireContext()).build()
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDataFromFirstFragmentViewModel()
        searchView()//for search view
        btn_floating_first.setOnClickListener {
            val i = Intent(requireContext(), Buy_Sell_Activity::class.java)
            startActivity(i)
        }
    }

    //----------------------------------------------------------------------------------------------
    fun getDataFromFirstFragmentViewModel() {
        btn_floating_first.visibility = View.GONE
        linearProgressIndicator_first.visibility = View.VISIBLE
        //dialog.show()
        firstFragmentViewModel =
            ViewModelProviders.of(this, vmFactory).get(FirstFragmentViewModel::class.java)
        firstFragmentViewModel.readFirstInformation()
        firstFragmentViewModel.getFirstInformation()
            .observe(viewLifecycleOwner, object : Observer<User> {
                override fun onChanged(t: User?) {

                    val _users = t!!.raw.btc.usd
                    val _users1 = t.raw.eth.usd
                    val _users2 = t.raw.bnb.usd
                    val _users3 = t.raw.xrp.usd
                    val _users4 = t.raw.xlm.usd
                    val _users5 = t.raw.ltc.usd
                    val _users6 = t.raw.etc.usd
                    val _users7 = t.raw.doeg.usd
                    val _users8 = t.raw.eos.usd
                    val _users9 = t.raw.busd.usd
                    //------------------------------------------------------------------------------
                    val mSymbol = _users.symbole
                    val mPrice = _users.price
                    val mChange24hour = _users.change24hour
                    val usd = USD(mSymbol, mPrice, mChange24hour)
                    val btc = BTC(usd)
                    val raw = RAW(btc)
                    listView.add(User(raw))
                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(1) == false){
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._addCoin(
                                Item_Coin(
                                    1,
                                    mPrice.toDouble(),
                                    mSymbol,
                                    "بیت کوین",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }

                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(1) == true) {
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._updateCoin_(
                                Item_Coin(
                                    1,
                                    mPrice,
                                    mSymbol,
                                    "بیت کوین",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }
                    //------------------------------------------------------------------------------
                    val mSymbol1 = _users1.symbole
                    val mPrice1 = _users1.price
                    val mChange24hour1 = _users1.change24hour
                    val usd1 = USD(mSymbol1, mPrice1, mChange24hour1)
                    val btc1 = BTC(usd1)
                    val raw1 = RAW(btc1)
                    listView.add(User(raw1))
                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(2) == false){
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._addCoin(
                                Item_Coin(
                                    2,
                                    mPrice.toDouble(),
                                    mSymbol,
                                    "اتریم",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }

                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(2) == true) {
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._updateCoin_(
                                Item_Coin(
                                    2,
                                    mPrice,
                                    mSymbol,
                                    "اتریم",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }
                    //------------------------------------------------------------------------------
                    val mSymbol2 = _users2.symbole
                    val mPrice2 = _users2.price
                    val mChange24hour2 = _users2.change24hour
                    val usd2 = USD(mSymbol2, mPrice2, mChange24hour2)
                    val btc2 = BTC(usd2)
                    val raw2 = RAW(btc2)
                    listView.add(User(raw2))

                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(3) == false){
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._addCoin(
                                Item_Coin(
                                    3,
                                    mPrice.toDouble(),
                                    mSymbol,
                                    "بایننس کوین",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }

                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(3) == true) {
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._updateCoin_(
                                Item_Coin(
                                    3,
                                    mPrice,
                                    mSymbol,
                                    "بایننس کوین",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }
                    //------------------------------------------------------------------------------
                    val mSymbol3 = _users3.symbole
                    val mPrice3 = _users3.price
                    val mChange24hour3 = _users3.change24hour
                    val usd3 = USD(mSymbol3, mPrice3, mChange24hour3)
                    val btc3 = BTC(usd3)
                    val raw3 = RAW(btc3)
                    listView.add(User(raw3))
                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(4) == false){
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._addCoin(
                                Item_Coin(
                                    4,
                                    mPrice.toDouble(),
                                    mSymbol,
                                    "ریپل",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }

                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(4) == true) {
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._updateCoin_(
                                Item_Coin(
                                    4,
                                    mPrice,
                                    mSymbol,
                                    "ریپل",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }
                    //------------------------------------------------------------------------------
                    val mSymbol4 = _users4.symbole
                    val mPrice4 = _users4.price
                    val mChange24hour4 = _users4.change24hour
                    val usd4 = USD(mSymbol4, mPrice4, mChange24hour4)
                    val btc4 = BTC(usd4)
                    val raw4 = RAW(btc4)
                    listView.add(User(raw4))
                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(5) == false){
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._addCoin(
                                Item_Coin(
                                    5,
                                    mPrice.toDouble(),
                                    mSymbol,
                                    "استلار",
                                    mChange24hour!!.toDouble()
                                )
                            )

                    }

                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(5) == true) {
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._updateCoin_(
                                Item_Coin(
                                    5,
                                    mPrice,
                                    mSymbol,
                                    "استلار",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }
                    //------------------------------------------------------------------------------
                    val mSymbol5 = _users5.symbole
                    val mPrice5 = _users5.price
                    val mChange24hour5 = _users5.change24hour
                    val usd5 = USD(mSymbol5, mPrice5, mChange24hour5)
                    val btc5 = BTC(usd5)
                    val raw5 = RAW(btc5)
                    listView.add(User(raw5))
                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(6) == false){
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._addCoin(
                                Item_Coin(
                                    6,
                                    mPrice.toDouble(),
                                    mSymbol,
                                    "لایت کوین",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }

                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(6) == true) {
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._updateCoin_(
                                Item_Coin(
                                    6,
                                    mPrice,
                                    mSymbol,
                                    "لایت کوین",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }
                    //------------------------------------------------------------------------------
                    val mSymbol6 = _users6.symbole
                    val mPrice6 = _users6.price
                    val mChange24hour6 = _users6.change24hour
                    val usd6 = USD(mSymbol6, mPrice6, mChange24hour6)
                    val btc6 = BTC(usd6)
                    val raw6 = RAW(btc6)
                    listView.add(User(raw6))
                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(7) == false){
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._addCoin(
                                Item_Coin(
                                    7,
                                    mPrice.toDouble(),
                                    mSymbol,
                                    "اتریم کلاسیک",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }

                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(7) == true) {
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._updateCoin_(
                                Item_Coin(
                                    7,
                                    mPrice,
                                    mSymbol,
                                    "اتریم کلاسیک",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }
                    //------------------------------------------------------------------------------
                    val mSymbol7 = _users7.symbole
                    val mPrice7 = _users7.price
                    val mChange24hour7 = _users7.change24hour
                    val usd7 = USD(mSymbol7, mPrice7, mChange24hour7)
                    val btc7 = BTC(usd7)
                    val raw7 = RAW(btc7)
                    listView.add(User(raw7))
                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(8) == false){
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._addCoin(
                                Item_Coin(
                                    8,
                                    mPrice.toDouble(),
                                    mSymbol,
                                    "دوج کوین",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }

                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(8) == true) {
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._updateCoin_(
                                Item_Coin(
                                    8,
                                    mPrice,
                                    mSymbol,
                                    "دوج کوین",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }
                    //------------------------------------------------------------------------------
                    val mSymbol8 = _users8.symbole
                    val mPrice8 = _users8.price
                    val mChange24hour8 = _users8.change24hour
                    val usd8 = USD(mSymbol8, mPrice8, mChange24hour8)
                    val btc8 = BTC(usd8)
                    val raw8 = RAW(btc8)
                    listView.add(User(raw8))
                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(9) == false){
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._addCoin(
                                Item_Coin(
                                    9,
                                    mPrice.toDouble(),
                                    mSymbol,
                                    "اییاس",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }

                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(9) == true) {
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._updateCoin_(
                                Item_Coin(
                                    9,
                                    mPrice,
                                    mSymbol,
                                    "اییاس",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }
                    //------------------------------------------------------------------------------
                    val mSymbol9 = _users9.symbole
                    val mPrice9 = _users9.price
                    val mChange24hour9 = _users9.change24hour
                    val usd9 = USD(mSymbol9, mPrice9, mChange24hour9)
                    val btc9 = BTC(usd9)
                    val raw9 = RAW(btc9)
                    listView.add(User(raw9))
                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(10) == false){
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._addCoin(
                                Item_Coin(
                                    10,
                                    mPrice.toDouble(),
                                    mSymbol,
                                    "بایننس یو اس دی",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }

                    if (myAppDatabase.myDaoSaveCoinPrice().isProductExistCoin(10) == true) {
                        myAppDatabase.myDaoSaveCoinPrice()
                            ._updateCoin_(
                                Item_Coin(
                                    10,
                                    mPrice,
                                    mSymbol,
                                    "بایننس یو اس دی",
                                    mChange24hour!!.toDouble()
                                )
                            )
                    }
                    //------------------------------------------------------------------------------
                    disPlay000.add(t)//for search view

                    adapter =
                        FirstFragmentAdapter(requireContext(), listView, arrayList_image, coinName)
                    adapter.notifyDataSetChanged()
                    recyclerView_mainActivity.adapter = adapter

                    //dialog.dismiss()

                    linearProgressIndicator_first.visibility = View.GONE
                    //searchView_first.visibility = View.VISIBLE
                    btn_floating_first.visibility = View.VISIBLE

                }
            })
    }

    //----------------------------------------------------------------------------------------------
    fun searchView() {//for search view
        //listView = ArrayList<User>()
        //disPlay = ArrayList<User>()
        searchView_first.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(requireContext(), newText, Toast.LENGTH_SHORT).show()
                if (newText!!.isNotEmpty()) {
                    disPlay000.clear()
                    val mySearch = newText.toLowerCase(Locale.getDefault())
                    listView000.forEach {
                        if (it.raw.btc.usd.symbole.toLowerCase(Locale.getDefault())
                                .contains(mySearch)
                        ) {
                            disPlay000.add(it)
                        }
                    }
                    recyclerView_mainActivity.adapter!!.notifyDataSetChanged()
                } else {
                    disPlay000.clear()
                    disPlay000.addAll(listView000)
                    recyclerView_mainActivity.adapter?.notifyDataSetChanged()
                }
                return true
            }
        })
    }

/*    override fun onLoadMore() {
        if (listView.size <= 5 *//*Common.MAX_COIN_LOAD*//*)
            loadNext10Coin(listView.size)
        else
            Toast.makeText(
                requireContext(),
                "Data Max is" + 5*//*Common.MAX_COIN_LOAD*//*,
                Toast.LENGTH_SHORT
            ).show()
    }

    private fun loadNext10Coin(size: Int) {

    }

    private fun loadFirst10Coin(size: Int) {

    }*/
}