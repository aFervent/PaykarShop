package com.example.paykarshop.bottomfragment.profile.favofite

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paykarshop.bottomfragment.basket.BasketInfoRepository
import com.example.paykarshop.models.basketModel.BasketModel
import retrofit2.Call
import retrofit2.Response

class FavoriteViewModel (private val favoriteRepository: FavoriteRepository)  : ViewModel() {

    val childCategoryList = MutableLiveData<BasketModel>()
    val errorMessage = MutableLiveData<String>()

    fun showFavorite(userId: Int) {
        val response = favoriteRepository.showFavorite(userId)

        response.enqueue(object:
            retrofit2.Callback<BasketModel> {
            override fun onResponse(
                call: Call<BasketModel>,
                response: Response<BasketModel>
            ) {

                childCategoryList.postValue(response.body())
                Log.d("Favorite Succsess", "Succsess")
            }

            override fun onFailure(call: Call<BasketModel>, t: Throwable) {
                errorMessage.postValue(t.message)
                Log.d("Basket Error", response.toString())
            }

        })
    }
}