package com.example.paykarshop.bottomfragment.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paykarshop.models.basketModel.BasketModel
import com.example.paykarshop.models.loginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val  loginRepository: LoginRepository): ViewModel() {

    val loginLiveData = MutableLiveData<loginModel>()
    val errorMessage = MutableLiveData<String>()


    fun chekUserLogin(login: String) {
        val response = loginRepository.getUserLogin(login)
        response.enqueue(object : Callback<loginModel> {
            override fun onResponse(call: Call<loginModel>, response: Response<loginModel>) {

                loginLiveData.postValue(response.body())

                Log.d("LOGIN Succsess", response.body().toString())

            }

            override fun onFailure(call: Call<loginModel>, t: Throwable) {

                errorMessage.postValue(t.message)
                Log.d("LOGIN Error", response.toString())
            }

        })
    }
}