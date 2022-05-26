package com.example.paykarshop.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.Repository.ChildDetailsRepository
import com.example.paykarshop.viewModel.CatalogChildViewModel

class ChildViewModelFactory constructor(private val repositoryCatalog: ChildDetailsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CatalogChildViewModel::class.java)) {
            CatalogChildViewModel(this.repositoryCatalog) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}