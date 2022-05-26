package com.example.paykarshop.Repository

import com.example.paykarshop.network.RetrofitService

class SliderCatalogHomeRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllSlideMain() = retrofitService.getAllSliderCatalog()

    fun GetPromoInfo() = retrofitService.GetPromoInfo()

}