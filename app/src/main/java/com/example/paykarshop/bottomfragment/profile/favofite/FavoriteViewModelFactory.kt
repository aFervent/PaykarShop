package com.example.paykarshop.bottomfragment.profile.favofite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.bottomfragment.basket.BasketInfoRepository
import com.example.paykarshop.bottomfragment.basket.BasketInfoViewModel

class FavoriteViewModelFactory constructor(private val favoriteRepository: FavoriteRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            FavoriteViewModel(this.favoriteRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}