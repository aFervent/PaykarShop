package com.example.paykarshop.bottomfragment.profile.history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.Adapter.CatalogChildAdapter
import com.example.paykarshop.Adapter.HistoryMainAdapter
import com.example.paykarshop.R
import com.example.paykarshop.Repository.ChildDetailsRepository
import com.example.paykarshop.models.historyModel.HistoryModelItem
import com.example.paykarshop.network.RetrofitService
import com.example.paykarshop.viewModel.CatalogChildViewModel
import com.example.paykarshop.viewmodelFactory.ChildViewModelFactory
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

    lateinit var viewModel: HistoryViewModel

    private val retrofitService = RetrofitService.getInstance()

    val adapter = HistoryMainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        viewModel = ViewModelProvider(this, HistoryViewModelFactory(HistoryRepository(retrofitService))).get(HistoryViewModel::class.java)


        history_rv.adapter = adapter

        viewModel.historyModel.observe(this, Observer {
            adapter.setCatalogList(it)
        })

        viewModel.historyViewModel(1)


        adapter.setonItemClickListener(object : HistoryMainAdapter.onItemClickListener{
            override fun onItemClick(position: HistoryModelItem) {
                Toast.makeText(this@HistoryActivity, "your id is ${position.id}", Toast.LENGTH_SHORT).show()

                intent.putExtra("number_of_order", position.id)
                intent.putExtra("date_status", position.date_status)
                intent.putExtra("canceled", position.canceled)
//                startActivity(Intent(this@HistoryActivity, HistoryDetailActivity::class.java))
            }


        })

    }
}