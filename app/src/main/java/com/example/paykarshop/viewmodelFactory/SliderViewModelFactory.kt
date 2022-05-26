package com.example.paykarshop.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.Repository.CatalogMainRepository
import com.example.paykarshop.Repository.SliderCatalogHomeRepository
import com.example.paykarshop.viewModel.CatalogMainViewModel
import com.example.paykarshop.viewModel.SliderCatalogHomeViewModel

class SliderViewModelFactory constructor(private val repositoryCatalog: SliderCatalogHomeRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SliderCatalogHomeViewModel::class.java)) {
            SliderCatalogHomeViewModel(this.repositoryCatalog) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}