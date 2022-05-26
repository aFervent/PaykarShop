package com.example.paykarshop.bottomfragment.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.paykarshop.MainActivity
import com.example.paykarshop.R
import com.example.paykarshop.network.RetrofitService
import kotlinx.android.synthetic.main.activity_verification.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerificationMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val loginNumber:String = intent.getStringExtra("numberLogin").toString()

        Log.d("VERIFICATION", loginNumber)

        RetrofitService.getInstance().getVerificationCode(loginNumber).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.code() == 200) {
                    btn_code.setOnClickListener {

                        startActivity(Intent(this@VerificationMainActivity, MainActivity::class.java))
                    }

                }
                Log.d("VERIFICATION", "SUCCSSESS")

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

        })




    }
}