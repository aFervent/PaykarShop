package com.example.paykarshop.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.Adapter.WatchAllAdapter
import com.example.paykarshop.R
import com.example.paykarshop.Repository.WatchAllRepository
import com.example.paykarshop.network.RetrofitService
import com.example.paykarshop.viewModel.WatchAllViewModel
import com.example.paykarshop.viewmodelFactory.WatchAllViewModelFactory
import kotlinx.android.synthetic.main.activity_watch_all_catalog.*
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_catalog_child.*
import kotlinx.android.synthetic.main.activity_watch_all_catalog.linear_back


class WatchAllCatalogActivity : AppCompatActivity() {


    lateinit var viewModel: WatchAllViewModel

    private val retrofitService = RetrofitService.getInstance()

    val adapter = WatchAllAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_all_catalog)

        linear_back.setOnClickListener {
            onBackPressed()
        }
        watchAllRv.adapter = adapter

        viewModel = ViewModelProvider(this, WatchAllViewModelFactory(WatchAllRepository(retrofitService))).get(WatchAllViewModel::class.java)


        viewModel.childCategoryList.observe(this, Observer {
            adapter.setWatchAll(it)
        })

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val data = prefs.getString("sectionIdMain", "")
        val id: Int = data!!.toInt()
        Log.d("SectionIDNew", data.toString())


            viewModel.getWatchAll(id, 1)

//        Log.d("REQ", viewModel.getWatchAll(data.toInt(), 1).toString())

    }
}