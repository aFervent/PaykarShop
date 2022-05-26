package com.example.paykarshop.viewModel

import ChildCategory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paykarshop.Repository.ChildDetailsRepository
import retrofit2.Call
import retrofit2.Response

class CatalogChildViewModel(private val repositoryChildCatalog: ChildDetailsRepository)  : ViewModel() {

    val childCategoryList = MutableLiveData<ChildCategory>()
    val errorMessage = MutableLiveData<String>()


    fun getChildCatalog(catalogId: String?) {
        val response = repositoryChildCatalog.getChildCatalog(catalogId)
        response.enqueue(object:
            retrofit2.Callback<ChildCategory> {
            override fun onResponse(
                call: Call<ChildCategory>,
                response: Response<ChildCategory>
            ) {

                childCategoryList.postValue(response.body())
            }

            override fun onFailure(call: Call<ChildCategory>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
        Log.d("RESPONSE", response.toString())
    }


}