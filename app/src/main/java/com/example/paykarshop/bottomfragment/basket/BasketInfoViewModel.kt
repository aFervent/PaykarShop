package com.example.paykarshop.bottomfragment.basket

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paykarshop.Repository.ChildDetailsRepository
import com.example.paykarshop.models.basketModel.BasketModel
import com.example.paykarshop.models.basketModel.ProductItme
import retrofit2.Call
import retrofit2.Response

class BasketInfoViewModel(private val basketInfoRepository: BasketInfoRepository)  : ViewModel()  {

    val childCategoryList = MutableLiveData<BasketModel>()
    val errorMessage = MutableLiveData<String>()

    fun getBasketData() {
        val response = basketInfoRepository.getBasketData()
        response.enqueue(object:
            retrofit2.Callback<BasketModel> {
            override fun onResponse(
                call: Call<BasketModel>,
                response: Response<BasketModel>
            ) {

                childCategoryList.postValue(response.body())
                Log.d("Basket Succsess", "Succsess")
            }

            override fun onFailure(call: Call<BasketModel>, t: Throwable) {
                errorMessage.postValue(t.message)
                Log.d("Basket Error", response.toString())
            }

        })

    }
}