package com.example.paykarshop.bottomfragment.catalog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.R
import com.example.paykarshop.Repository.CatalogMainRepository
import com.example.paykarshop.databinding.ActivityMainBinding
import com.example.paykarshop.network.RetrofitService
import com.example.paykarshop.viewModel.CatalogMainAdapter
import com.example.paykarshop.viewModel.CatalogMainViewModel
import com.example.paykarshop.viewmodelFactory.MyViewModelFactory
import kotlinx.android.synthetic.main.fragment_catalog.*
import androidx.recyclerview.widget.GridLayoutManager




class CatalogFragment : Fragment() {

    private val TAG = "MainActivity"

    lateinit var viewModel: CatalogMainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = CatalogMainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview.setLayoutManager(GridLayoutManager(requireActivity(), 2))
        viewModel = ViewModelProvider(this, MyViewModelFactory(CatalogMainRepository(retrofitService))).get(CatalogMainViewModel::class.java)

        recyclerview.adapter = adapter

        viewModel.movieList.observe(requireActivity(), Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setCatalogList(it)
        })

        viewModel.errorMessage.observe(requireActivity(), Observer {

        })
        viewModel.getAllMovies()

    }
}