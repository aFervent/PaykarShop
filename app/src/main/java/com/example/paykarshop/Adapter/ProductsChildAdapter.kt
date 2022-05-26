package com.example.paykarshop.Adapter

import Products
import android.R
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paykarshop.databinding.AdapterCategoryBinding
import com.example.paykarshop.databinding.ButtonBinding
import com.example.paykarshop.databinding.ChipsChildCategoryBinding
import com.example.paykarshop.databinding.SalatsInfoBinding
import com.example.paykarshop.network.RetrofitService
import com.squareup.picasso.Picasso
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsChildAdapter: RecyclerView.Adapter<ProductsChildAdapter.MainProductsChildAdapter>() {


    var catalogsChild = mutableListOf<Products>()
    private val picasso = Picasso.get()
    lateinit var context: Context

    fun setProductList(childCategory: List<Products>) {
        this.catalogsChild = childCategory.toMutableList()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainProductsChildAdapter {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        val binding = SalatsInfoBinding.inflate(inflater, parent, false)
        return MainProductsChildAdapter(binding)
    }

    override fun onBindViewHolder(holder: MainProductsChildAdapter, position: Int) {
        val catalogChild = catalogsChild[position]
        Log.d("catalogChild", "dfdfd")
        val image = "https://paykar.shop" + catalogChild.picture
        picasso.load(image).into(holder.binding.imageChild)
//        holder.binding.nameOfSalat.text = catalogChild.name
        holder.binding.PriceChildOfProducts.text = catalogChild.discountPrice.toString()

        holder.binding.addToCart.setOnClickListener {

            RetrofitService.retrofitService!!.addToBasket(catalogChild.id.toString(), 1).enqueue(object: Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Log.d("respoonseesesesesse", response.body().toString())

                    Toast.makeText(context, "Товар успешно добавлен в корзину", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                    Log.d("ResponseOfProfitableErr", call.toString())
                }

            })
        }

        holder.binding.favoriteInfo.setOnClickListener {

            RetrofitService.getInstance().addWishList(catalogChild.id.toString(), 1, 1).enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {



                    if (response.isSuccessful) {
                        Toast.makeText(context, "Товар Успешно добавлен!", Toast.LENGTH_LONG).show()
//                        addedFav = true
                        Glide.with(context).load(context.resources.getDrawable(com.example.paykarshop.R.drawable.ic_baseline_favorite_24)).into(holder.binding.favoriteInfo)
                    } else {
//                        addedFav = false
                        Glide.with(context).load(context.resources.getDrawable(com.example.paykarshop.R.drawable.ic_heart)).into(holder.binding.favoriteInfo)
                    }
                    Log.d("Favorite", response.code().toString())
                    Log.d("RESPONSE", response.message())


                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }

            })

        }
    }


    override fun getItemCount(): Int {
        return  catalogsChild.size
    }


    class MainProductsChildAdapter( var binding: SalatsInfoBinding): RecyclerView.ViewHolder(binding.root) {


    }
}