package com.example.paykarshop.bottomfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.Adapter.ProfitableGoodsAdapter
import com.example.paykarshop.Adapter.SliderAdapter
import com.example.paykarshop.R
import com.example.paykarshop.Repository.ChildDetailsRepository
import com.example.paykarshop.Repository.ProfitableRepository
import com.example.paykarshop.Repository.SliderCatalogHomeRepository
import com.example.paykarshop.network.RetrofitService
import com.example.paykarshop.viewModel.CatalogChildViewModel
import com.example.paykarshop.viewModel.ProfitableGoodsViewModel
import com.example.paykarshop.viewModel.SliderCatalogHomeViewModel
import com.example.paykarshop.viewmodelFactory.ChildViewModelFactory
import com.example.paykarshop.viewmodelFactory.ProfitableViewModelFactory
import com.example.paykarshop.viewmodelFactory.SliderViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val TAG = "MainActivity"

    lateinit var viewModel: SliderCatalogHomeViewModel
    lateinit var viewmodelProfitableGoodsViewModel: ProfitableGoodsViewModel

    private val retrofitService = RetrofitService.getInstance()

    private lateinit var salesAdapter: SliderAdapter

    val adapter = ProfitableGoodsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        salesAdapter = SliderAdapter()

        viewModel = ViewModelProvider(this, SliderViewModelFactory(SliderCatalogHomeRepository(retrofitService))).get(SliderCatalogHomeViewModel::class.java)

        viewmodelProfitableGoodsViewModel = ViewModelProvider(this, ProfitableViewModelFactory(
            ProfitableRepository(retrofitService)
        )).get(ProfitableGoodsViewModel::class.java)

        imageScroll.adapter = salesAdapter
        SalesRecycle.adapter = salesAdapter

        viewModel.sliderList.observe(requireActivity(), Observer {
            Log.d(TAG, "onCreate: $it")

            salesAdapter.setCatalogList(it)

        })
        viewModel.getAllSliderList()




        recyclerviewProfitable.adapter = adapter

        choiceRecycle.adapter = adapter

        viewmodelProfitableGoodsViewModel.profitList.observe(requireActivity(), Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setProductList(it)

        })
        viewmodelProfitableGoodsViewModel.getAllProfitableGoods()

    }


}