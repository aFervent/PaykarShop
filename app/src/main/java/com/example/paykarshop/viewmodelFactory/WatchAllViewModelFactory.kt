package com.example.paykarshop.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.Repository.ChildDetailsRepository
import com.example.paykarshop.Repository.WatchAllRepository
import com.example.paykarshop.viewModel.CatalogChildViewModel
import com.example.paykarshop.viewModel.WatchAllViewModel

class WatchAllViewModelFactory constructor(private val repositoryCatalog: WatchAllRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WatchAllViewModel::class.java)) {
            WatchAllViewModel(this.repositoryCatalog) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}