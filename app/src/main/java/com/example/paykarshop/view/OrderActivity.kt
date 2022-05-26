package com.example.paykarshop.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.paykarshop.R
import com.example.paykarshop.models.basketModel.CommentModel
import com.example.paykarshop.network.RetrofitService
import kotlinx.android.synthetic.main.activity_order.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderActivity : AppCompatActivity() {

    lateinit var commentModel: CommentModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)


        commentModel = CommentModel(1, yardInfo.text.toString(), homeInfo.text.toString(), podInfo.text.toString(),
            apartmentInfo.text.toString())

        offerOrder.setOnClickListener {

            RetrofitService.getInstance().infoAboutAdress(commentModel).enqueue(object: Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Log.d("ORDER", "SUCCSEESS")

                    Log.d("ORDER Head", response.headers().toString())
                    Log.d("ORDER Response", response.toString())

                    if(response.isSuccessful) {
                        startActivity(Intent(this@OrderActivity, OrderCheckedActivity::class.java))
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("ORDER", "ERROR")
                }

            })
        }
    }
}