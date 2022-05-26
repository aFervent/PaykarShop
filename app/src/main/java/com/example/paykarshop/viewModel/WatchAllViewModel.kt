package com.example.paykarshop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paykarshop.Repository.ChildDetailsRepository
import com.example.paykarshop.Repository.WatchAllRepository
import com.example.paykarshop.models.WatchAllModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WatchAllViewModel (private val watchAllRepository: WatchAllRepository)  : ViewModel() {

    val childCategoryList = MutableLiveData<WatchAllModel>()
    val errorMessage = MutableLiveData<String>()


    fun getWatchAll(BlockId: Int, userId: Int) {
        val response = watchAllRepository.getAllSlideMain(BlockId, userId)
        response.enqueue(object: Callback<WatchAllModel> {
            override fun onResponse(call: Call<WatchAllModel>, response: Response<WatchAllModel>) {

                childCategoryList.postValue(response.body())

                Log.d("WATCHALL", "SUCCSESSSSS")
            }

            override fun onFailure(call: Call<WatchAllModel>, t: Throwable) {

                errorMessage.postValue(t.message)
            }

        })
    }
}