package com.example.paykarshop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paykarshop.Repository.ProfitableRepository
import com.example.paykarshop.models.ModelProfitable
import com.example.paykarshop.models.ModelProfitableItem
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ProfitableGoodsViewModel constructor(private val repository: ProfitableRepository): ViewModel() {

    val profitList = MutableLiveData<ModelProfitable>()
    val errorMessage = MutableLiveData<String>()


    fun getAllProfitableGoods() {
        val response = repository.getProfitableGoods()
        response.enqueue(object: retrofit2.Callback<ModelProfitable> {
            override fun onResponse(
                call: Call<ModelProfitable>,
                response: Response<ModelProfitable>
            ) {

                profitList.postValue(response.body())

                Log.d("ResponseOfProfitable", response.body().toString())

            }

            override fun onFailure(call: Call<ModelProfitable>, t: Throwable) {

                Log.d("ResponseOfProfitableErr", response.toString())

            }

        })
    }
}