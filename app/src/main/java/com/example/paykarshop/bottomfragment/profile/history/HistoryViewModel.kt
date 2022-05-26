package com.example.paykarshop.bottomfragment.profile.history

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paykarshop.bottomfragment.profile.LoginRepository
import com.example.paykarshop.models.historyModel.HistoryModel
import com.example.paykarshop.models.loginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel (private val  historyRepository: HistoryRepository): ViewModel()  {

    val historyModel = MutableLiveData<HistoryModel>()
    val errorMessage = MutableLiveData<String>()


    fun historyViewModel(userID: Int) {
        val response = historyRepository.getOrderHistory(userID)

        response.enqueue(object : Callback<HistoryModel>{
            override fun onResponse(call: Call<HistoryModel>, response: Response<HistoryModel>) {

                historyModel.postValue(response.body())

                Log.d("historyModel SUCC", response.body().toString())
            }

            override fun onFailure(call: Call<HistoryModel>, t: Throwable) {

            }

        })
    }
}