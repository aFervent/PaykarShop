package com.example.paykarshop.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.Repository.CatalogMainRepository
import com.example.paykarshop.viewModel.CatalogMainViewModel

class MyViewModelFactory constructor(private val repositoryCatalog: CatalogMainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CatalogMainViewModel::class.java)) {
            CatalogMainViewModel(this.repositoryCatalog) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}