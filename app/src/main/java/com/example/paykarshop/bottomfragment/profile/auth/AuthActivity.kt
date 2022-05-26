package com.example.paykarshop.bottomfragment.profile.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.paykarshop.R
import com.example.paykarshop.bottomfragment.profile.VerificationMainActivity
import com.example.paykarshop.network.RetrofitService
import kotlinx.android.synthetic.main.activity_auth.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val name = name_auth.text.toString()
        val surname = lastname_auth.text.toString()
        val login = number_auth.text.toString()
        val pass = password_auth.text.toString()

        auth_button.setOnClickListener {

            RetrofitService.getInstance().registrationUser(name, surname, login, pass, "null")
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {

                        Log.d("AUTH", response.message())
                        Log.d("AUTH", response.code().toString())
                        if (response.isSuccessful) {
                            startActivity(
                                Intent(
                                    this@AuthActivity,
                                    VerificationMainActivity::class.java
                                )
                            )
                        }

                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {


                    }


                })

        }

    }
}