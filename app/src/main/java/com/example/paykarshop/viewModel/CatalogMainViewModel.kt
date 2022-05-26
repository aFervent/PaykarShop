package com.example.paykarshop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paykarshop.Repository.CatalogMainRepository
import com.example.paykarshop.models.ModelCatalog
import retrofit2.Call
import retrofit2.Response

class CatalogMainViewModel constructor(private val repositoryCatalog: CatalogMainRepository)  : ViewModel() {

    val movieList = MutableLiveData<List<ModelCatalog>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {

        val response = repositoryCatalog.getAllCatalog()
        response.enqueue(object:
            retrofit2.Callback<List<ModelCatalog>> {
            override fun onResponse(
                call: Call<List<ModelCatalog>>,
                response: Response<List<ModelCatalog>>
            ) {

                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<ModelCatalog>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}