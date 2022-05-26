package com.example.paykarshop.Repository

import com.example.paykarshop.network.RetrofitService

class CatalogMainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllCatalog() = retrofitService.getAllCatalog()
}