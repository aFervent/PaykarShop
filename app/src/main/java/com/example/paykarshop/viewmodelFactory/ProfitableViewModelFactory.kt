package com.example.paykarshop.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.Repository.ProfitableRepository
import com.example.paykarshop.Repository.SliderCatalogHomeRepository
import com.example.paykarshop.viewModel.ProfitableGoodsViewModel
import com.example.paykarshop.viewModel.SliderCatalogHomeViewModel

class ProfitableViewModelFactory constructor(private val repositoryCatalog: ProfitableRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProfitableGoodsViewModel::class.java)) {
            ProfitableGoodsViewModel(this.repositoryCatalog) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}