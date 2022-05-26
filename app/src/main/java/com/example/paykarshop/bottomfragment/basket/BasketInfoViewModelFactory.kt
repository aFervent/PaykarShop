package com.example.paykarshop.bottomfragment.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.Repository.ChildDetailsRepository
import com.example.paykarshop.viewModel.CatalogChildViewModel

class BasketInfoViewModelFactory constructor(private val repositoryCatalog: BasketInfoRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BasketInfoViewModel::class.java)) {
            BasketInfoViewModel(this.repositoryCatalog) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}