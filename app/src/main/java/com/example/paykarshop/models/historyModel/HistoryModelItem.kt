package com.example.paykarshop.models.historyModel

data class HistoryModelItem(
    val basket_order: List<BasketOrder>,
    val basket_order_count: Int,
    val canceled: String,
    val comments: String,
    val date: String,
    val date_canceled: String,
    val date_status: String,
    val deducted: String,
    val discount_value: String,
    val id: String,
    val payed: String,
    val pirce: String,
    val pirce_delivery: String,
    val reason_canceled: String,
    val status_id: String,
    val sum_paid: String,
    val user_lastname: String,
    val user_name: String
)