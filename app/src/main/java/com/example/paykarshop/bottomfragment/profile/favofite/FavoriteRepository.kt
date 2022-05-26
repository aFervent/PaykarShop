package com.example.paykarshop.bottomfragment.profile.favofite

import com.example.paykarshop.network.RetrofitService

class FavoriteRepository constructor(private val retrofitService: RetrofitService) {

    fun showFavorite(userId: Int) = retrofitService.showFavorite(userId)
}