package com.example.paykarshop.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paykarshop.MainActivity
import com.example.paykarshop.R
import kotlinx.android.synthetic.main.activity_order_checked.*

class OrderCheckedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_checked)

        BackHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}