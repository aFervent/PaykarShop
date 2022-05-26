package com.example.paykarshop.bottomfragment.profile.history

import com.example.paykarshop.network.RetrofitService

class HistoryRepository constructor(private val retrofitService: RetrofitService) {

    fun getOrderHistory(userID: Int) = retrofitService.getOrderHistory(userID)
}