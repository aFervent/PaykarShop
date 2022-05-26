package com.example.paykarshop.bottomfragment.basket

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.paykarshop.Adapter.BasketInfoAdapter
import com.example.paykarshop.R
import com.example.paykarshop.network.RetrofitService
import com.example.paykarshop.view.OrderActivity
import kotlinx.android.synthetic.main.fragment_basket.*
import kotlinx.android.synthetic.main.fragment_catalog.*
import kotlin.math.log

class BasketFragment : Fragment() {


    lateinit var viewModel: BasketInfoViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = BasketInfoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        basketInfoRrV.layoutManager = GridLayoutManager(requireActivity(), 1)

        viewModel = ViewModelProvider(this, BasketInfoViewModelFactory(BasketInfoRepository(retrofitService))).get(BasketInfoViewModel::class.java)

        basketInfoRrV.adapter = adapter

        viewModel.childCategoryList.observe(requireActivity(), Observer {

            price_total.text = it.totalPrice.toString() + " сом"

            Log.d("ITEMS", "$it")
            adapter.setCatalogList(it.productItmes)
        })

        viewModel.getBasketData()

        buttonBasket.setOnClickListener {
            startActivity(Intent(requireActivity(), OrderActivity::class.java))
        }
    }


}