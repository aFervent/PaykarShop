package com.example.paykarshop.bottomfragment.profile.favofite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.paykarshop.Adapter.BasketInfoAdapter
import com.example.paykarshop.R
import com.example.paykarshop.bottomfragment.basket.BasketInfoRepository
import com.example.paykarshop.bottomfragment.basket.BasketInfoViewModel
import com.example.paykarshop.bottomfragment.basket.BasketInfoViewModelFactory
import com.example.paykarshop.bottomfragment.profile.LoginActivity
import com.example.paykarshop.network.RetrofitService
import kotlinx.android.synthetic.main.activity_favorite.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteActivity : AppCompatActivity() {

    lateinit var viewModel: FavoriteViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = BasketInfoAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        viewModel = ViewModelProvider(this, FavoriteViewModelFactory(FavoriteRepository(retrofitService))).get(FavoriteViewModel::class.java)

        favorite_rv.adapter = adapter
        viewModel.childCategoryList.observe(this, Observer {

            Log.d("ITEMS", "$it")
            adapter.setCatalogList(it.productItmes)
        })

        viewModel.showFavorite(1)

        delete_all_favorite.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Вы уверены что хотите удалить все избранные товары?")

            builder.setPositiveButton("Да") { dialog, which ->
                retrofitService.DeleteAllWishList(1).enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {

                        if (response.code() == 200) {
                            favorite_rv.visibility = View.GONE
                        }


                        Log.d("Delete", response.message())
                        Log.d("Delete", response.code().toString())

                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {


                    }

                })
            }

            builder.setNegativeButton("Нет") { dialog, which ->

            }

            builder.show()

        }
    }
}