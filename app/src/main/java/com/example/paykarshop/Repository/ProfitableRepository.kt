package com.example.paykarshop.Repository

import com.example.paykarshop.network.RetrofitService

class ProfitableRepository constructor(private val retrofitService: RetrofitService){


    fun getProfitableGoods() = retrofitService.getProfitableGoods()
}