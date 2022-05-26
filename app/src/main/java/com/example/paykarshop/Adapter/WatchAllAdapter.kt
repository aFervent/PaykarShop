package com.example.paykarshop.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paykarshop.R
import com.example.paykarshop.models.WatchAllModelItem
import com.example.paykarshop.models.basketModel.ProductItme
import com.example.paykarshop.network.RetrofitService
import com.squareup.picasso.Picasso
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WatchAllAdapter : RecyclerView.Adapter<WatchAllAdapter.ViewHolder>() {


    var basketModel = mutableListOf<WatchAllModelItem>()
    private val picasso = Picasso.get()
    lateinit var context: Context


    fun setWatchAll(childCategory: List<WatchAllModelItem>) {
        this.basketModel = childCategory.toMutableList()
        notifyDataSetChanged()
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.basket_info_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = basketModel[position]

        // sets the image to the imageview from our itemHolder class
        val image = "https://paykar.shop" + ItemsViewModel.picture
        picasso.load(image).into(holder.imageChild)

        // sets the text to the textview from our itemHolder class
        holder.nameOfSalat.text = ItemsViewModel.name
        holder.PriceChildOfProducts.text = ItemsViewModel.price

        holder.addToCart.setOnClickListener {

            RetrofitService.retrofitService!!.addToBasket(ItemsViewModel.id.toString(), 1).enqueue(object:
                Callback<ResponseBody> {
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

        holder.favorite_info.setOnClickListener {

            RetrofitService.getInstance().addWishList(ItemsViewModel.id, 1, 1).enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {



                    if (response.isSuccessful) {
                        Toast.makeText(context, "Товар Успешно добавлен!", Toast.LENGTH_LONG).show()
//                        addedFav = true
                        Glide.with(context).load(context.resources.getDrawable(com.example.paykarshop.R.drawable.ic_baseline_favorite_24)).into(holder.favorite_info)
                    } else {
//                        addedFav = false
                        Glide.with(context).load(context.resources.getDrawable(com.example.paykarshop.R.drawable.ic_heart)).into(holder.favorite_info)
                    }
                    Log.d("Favorite", response.code().toString())
                    Log.d("RESPONSE", response.message())


                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }

            })

        }



    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return basketModel.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageChild: ImageView = itemView.findViewById(R.id.imageChild)
        val nameOfSalat: TextView = itemView.findViewById(R.id.nameOfSalat)
        val PriceChildOfProducts: TextView = itemView.findViewById(R.id.PriceChildOfProducts)
        val addToCart: ImageView = itemView.findViewById(R.id.addToCart)
        val favorite_info: ImageView = itemView.findViewById(R.id.favorite_info)
    }

}