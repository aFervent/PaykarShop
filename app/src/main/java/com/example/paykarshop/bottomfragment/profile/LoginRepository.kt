package com.example.paykarshop.bottomfragment.profile

import com.example.paykarshop.network.RetrofitService

class LoginRepository constructor(private val retrofitService: RetrofitService) {


    fun getUserLogin(login: String) = retrofitService.checkUser(login)
}