package com.example.paykarshop.network

import ChildCategory
import com.example.paykarshop.models.*
import com.example.paykarshop.models.basketModel.BasketModel
import com.example.paykarshop.models.basketModel.CommentModel
import com.example.paykarshop.models.basketModel.ProductItme
import com.example.paykarshop.models.historyModel.HistoryModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitService {
    @GET("bitrix/api/catalog/catalog.php")
    fun getAllCatalog(): Call<List<ModelCatalog>>

    @GET("bitrix/api/catalog/category.php")
    fun getChildCatalog(@Query("sectionId") id: String?): Call<ChildCategory>

    @GET("bitrix/api/products.php")
    fun getWatchAllCatalog(
        @Query("BlockId") BlockId: Int,
        @Query("userId") userId: Int
    ): Call<WatchAllModel>


    @GET("bitrix/api/home/baseslider.php")
    fun getAllSliderCatalog(): Call<ModelSliderHome>

    @GET("bitrix/api/home/profitably.php?userId=1")
    fun getProfitableGoods(): Call<ModelProfitable>

    @GET("bitrix/api/basket/addbasketbyproductid.php?userId=1")
    fun addToBasket(@Query("productId") productId: String,
    @Query("quantity") quantity: Int,
    ): Call<ResponseBody>


    @GET("bitrix/api/basket/basket.php?userId=1")
    fun getBasketData():Call<BasketModel>


    @POST("bitrix/api/order/order.php")
    fun infoAboutAdress(@Body body: CommentModel): Call<ResponseBody>

    @FormUrlEncoded
    @POST("https://paykar.shop/bitrix/api/registration.php")
    fun registrationUser(@Field("Fname") Fname: String,
    @Field("Lname") Lname: String,
    @Field("Login") Login: String,
    @Field("Password") Password: String,
    @Field("ConfPassword") ConfPassword: String): Call<ResponseBody>

    @POST("https://paykar.shop/bitrix/api/login.php?")
    fun checkUser(@Query("login") login: String):Call<loginModel>

    @GET("https://paykar.shop/smsapi/index.php?")
    fun getVerificationCode(@Query("MobilePhone") mobilePhone: String): Call<ResponseBody>

    @GET("https://paykar.shop/bitrix/api/order/getOrderList.php?")
    fun getOrderHistory(@Query("userID") userID: Int ): Call<HistoryModel>

    @GET("https://paykar.shop/bitrix/api/basket/addwishlistbyproductid.php")
    fun addWishList(@Query("productId") productId: String,
    @Query("quantity") quantity: Int,
    @Query("userId") userId: Int): Call<ResponseBody>

    @GET("https://paykar.shop/bitrix/api/basket/wishlist.php?")
    fun showFavorite(@Query("userId") userId: Int): Call<BasketModel>

    @GET("https://paykar.shop/bitrix/api/basket/deleteallwishlist.php")
    fun DeleteAllWishList(@Query("userId") userId: Int): Call<ResponseBody>

    @GET("https://paykar.shop/bitrix/api/home/promo.php")
    fun GetPromoInfo(): Call<ResponseBody>


    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://paykar.shop/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}