package com.example.paykarshop.bottomfragment.profile.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paykarshop.R
import kotlinx.android.synthetic.main.activity_history_detail.*

class HistoryDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_detail)

      val number_of_order =   intent.getStringExtra("number_of_order")
      val date_status =   intent.getStringExtra("date_status")
      val canceled =   intent.getStringExtra("canceled")

        number_of_order_detail.text = "Заказ № " + number_of_order
        date_of_oformlen_detail.text = date_status
        status_text_detail.text = canceled
    }
}