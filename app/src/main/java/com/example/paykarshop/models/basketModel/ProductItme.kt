package com.example.paykarshop.models.basketModel

data class ProductItme(
    val baseUnit: String,
    val basket_quan: String,
    val detailText: String,
    val discount: List<Any>,
    val discountPrice: Double,
    val hit: String,
    val id: String,
    val name: String,
    val picture: String,
    val price: String
)