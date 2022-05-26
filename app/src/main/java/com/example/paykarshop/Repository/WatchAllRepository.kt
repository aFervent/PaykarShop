package com.example.paykarshop.Repository

import com.example.paykarshop.network.RetrofitService

class WatchAllRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllSlideMain(BlockId: Int, userId: Int) = retrofitService.getWatchAllCatalog(BlockId, userId)
}