package com.example.paykarshop.bottomfragment.catalog

import Products
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.paykarshop.Adapter.CatalogChildAdapter
import com.example.paykarshop.Adapter.ParentCatalogChildAdapter
import com.example.paykarshop.Adapter.ProductsChildAdapter
import com.example.paykarshop.R
import com.example.paykarshop.Repository.CatalogMainRepository
import com.example.paykarshop.Repository.ChildDetailsRepository
import com.example.paykarshop.databinding.ActivityMainBinding
import com.example.paykarshop.network.RetrofitService
import com.example.paykarshop.view.WatchAllCatalogActivity
import com.example.paykarshop.viewModel.CatalogChildViewModel
import com.example.paykarshop.viewModel.CatalogMainAdapter
import com.example.paykarshop.viewModel.CatalogMainViewModel
import com.example.paykarshop.viewmodelFactory.ChildViewModelFactory
import com.example.paykarshop.viewmodelFactory.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_catalog_child.*
import android.content.SharedPreferences

import android.preference.PreferenceManager




class CatalogChildActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    lateinit var viewModel: CatalogChildViewModel

    private val retrofitService = RetrofitService.getInstance()

    val adapter = CatalogChildAdapter()
    val parentCatalogChildAdapter = ParentCatalogChildAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog_child)

//        watch_all.setOnClickListener {
//
//            startActivity(Intent(this, WatchAllCatalogActivity::class.java))
//        }

        val id: String? = intent.getStringExtra("sectionId")


        Log.d("CATEGORYID", id.toString())

        viewModel = ViewModelProvider(this, ChildViewModelFactory(ChildDetailsRepository(retrofitService))).get(CatalogChildViewModel::class.java)

        recyclerviewChildChip.adapter = adapter
//        recyclerviewProductInfo.adapter = adapterProduct
        recyclerviewProductMain.adapter = parentCatalogChildAdapter

        viewModel.childCategoryList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setCatalogList(it.child)
            nameChildCatalog.text = it.sectionName
            parentCatalogChildAdapter.setCatalogList(it.child)

            Log.d("SECTIONMAINID", it.sectionId.toString())


        })

        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getChildCatalog(id)

        linear_back.setOnClickListener {
            onBackPressed()
        }

    }

}