package com.example.paykarshop.Repository

import com.example.paykarshop.network.RetrofitService

class ChildDetailsRepository(private val retrofitService: RetrofitService) {

    fun getChildCatalog(catalogId: String?) = retrofitService.getChildCatalog(catalogId)
}