package com.example.paykarshop.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paykarshop.R
import com.example.paykarshop.databinding.SalatsInfoBinding
import com.example.paykarshop.models.ModelProfitableItem
import com.example.paykarshop.network.RetrofitService
import com.squareup.picasso.Picasso
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfitableGoodsAdapter : RecyclerView.Adapter<ProfitableGoodsAdapter.MainProductsChildAdapter>() {

    var catalogsChild = mutableListOf<ModelProfitableItem>()
    private val picasso = Picasso.get()
    lateinit var context: Context

    fun setProductList(childCategory: List<ModelProfitableItem>) {
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

        var addedFav : Boolean

        if (catalogsChild.contains(catalogChild)) {

            Glide.with(context).load(context.resources.getDrawable(R.drawable.ic_heart)).into(holder.binding.favoriteInfo)
            addedFav = false
        } else {
            Glide.with(context).load(context.resources.getDrawable(R.drawable.ic_baseline_favorite_24)).into(holder.binding.favoriteInfo)
        }


        val image = "https://paykar.shop" + catalogChild.detail_picture
        picasso.load(image).into(holder.binding.imageChild)
//        holder.binding.nameOfSalat.text = catalogChild.name
        holder.binding.PriceChildOfProducts.text = catalogChild.discountPrice.toString()

        holder.binding.addToCart.setOnClickListener {

            Log.d("respoonseesesesesse", "CLICK")
            Log.d("respoonseesesesesse", catalogChild.id)

            RetrofitService.retrofitService!!.addToBasket(catalogChild.id, 1).enqueue(object:
                Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Log.d("ResponseOfAddingSucc", response.body().toString())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                    Log.d("ResponseOfAddingErr", call.toString())
                }

            })
        }

        holder.binding.favoriteInfo.setOnClickListener {

            RetrofitService.getInstance().addWishList(catalogChild.id, 1, 1).enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {



                    if (response.isSuccessful) {
                        Toast.makeText(context, "Товар Успешно добавлен!", Toast.LENGTH_LONG).show()
                        addedFav = true
                        Glide.with(context).load(context.resources.getDrawable(R.drawable.ic_baseline_favorite_24)).into(holder.binding.favoriteInfo)
                    } else {
                        addedFav = false
                        Glide.with(context).load(context.resources.getDrawable(R.drawable.ic_heart)).into(holder.binding.favoriteInfo)
                    }



                    Log.d("Favorite", catalogChild.id)
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