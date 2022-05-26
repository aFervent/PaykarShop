package com.example.paykarshop.models.basketModel

data class BasketModel(
    val fullPrice: Double,
    val productItmes: List<ProductItme>,
    val totalPrice: Double
)