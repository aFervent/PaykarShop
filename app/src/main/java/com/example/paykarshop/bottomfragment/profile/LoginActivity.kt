package com.example.paykarshop.bottomfragment.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.R
import com.example.paykarshop.bottomfragment.profile.auth.AuthActivity
import com.example.paykarshop.network.RetrofitService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginViewModel

    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        viewModel = ViewModelProvider(this, LoginViewModelFactory(LoginRepository(retrofitService))).get(LoginViewModel::class.java)


        btn_login.setOnClickListener {

            val number = login_edit_text.text.toString()

            viewModel.loginLiveData.observe(this, Observer {

                Log.d("NUMBER", it.login)
                Log.d("NAME", it.name.toString())
                Log.d("id", it.id)

                val settings = applicationContext.getSharedPreferences("paykarShop", 0)
                val editor = settings.edit()
                editor.putString("numberLogin", it.login)
                editor.putString("nameLogin", it.name.toString())
                editor.putString("id_login", it.id)
                editor.apply()


                val intent = Intent(this, VerificationMainActivity::class.java)
                intent.putExtra("numberLogin", login_edit_text.text.toString())
                startActivity(intent)

            })



            viewModel.chekUserLogin(number)

        }

        no_acc.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
        }




    }
}