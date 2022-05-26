package com.example.paykarshop.bottomfragment.basket

import com.example.paykarshop.network.RetrofitService

class BasketInfoRepository constructor(private val retrofitService: RetrofitService) {

    fun getBasketData() = retrofitService.getBasketData()
}