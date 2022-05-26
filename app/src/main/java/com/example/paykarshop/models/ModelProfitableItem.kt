package com.example.paykarshop.models

data class ModelProfitableItem(
    val base_unit: String,
    val basket_quan: Any,
    val detail_picture: String,
    val discount: Any,
    val discountPrice: Double,
    val hit: String,
    val id: String,
    val name: String,
    val price: String,
    val wishlist: Boolean
)