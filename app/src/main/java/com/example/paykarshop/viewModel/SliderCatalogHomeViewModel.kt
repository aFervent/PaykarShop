package com.example.paykarshop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paykarshop.Repository.CatalogMainRepository
import com.example.paykarshop.Repository.SliderCatalogHomeRepository
import com.example.paykarshop.models.ModelCatalog
import com.example.paykarshop.models.ModelSliderHome
import com.example.paykarshop.models.ModelSliderHomeItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SliderCatalogHomeViewModel constructor(private val repositoryCatalog: SliderCatalogHomeRepository)  : ViewModel() {

    val sliderList = MutableLiveData<ModelSliderHome>()
    val errorMessage = MutableLiveData<String>()

    fun getAllSliderList() {
        val response = repositoryCatalog.getAllSlideMain()
       response.enqueue(object: Callback<ModelSliderHome> {
           override fun onResponse(
               call: Call<ModelSliderHome>,
               response: Response<ModelSliderHome>
           ) {

               sliderList.postValue(response.body())

               Log.d("Resspoonsesese", response.body().toString())

           }

           override fun onFailure(call: Call<ModelSliderHome>, t: Throwable) {
               errorMessage.postValue(t.message)

               Log.d("Resspasdada", response.toString())
           }

       })
    }
}