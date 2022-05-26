package com.example.paykarshop.models.historyModel

data class BasketOrder(
    val DISCOUNT_PRICE: String,
    val NAME: String,
    val PRICE: String,
    val PRODUCT_ID: String,
    val QUANTITY: Double,
    val UNIT: String
)