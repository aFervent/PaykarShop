package com.example.paykarshop.Adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.paykarshop.R
import com.example.paykarshop.models.basketModel.ProductItme
import com.squareup.picasso.Picasso
//: RecyclerView.Adapter<BasketInfoAdapter.MainBasketInfoAdapter>()

class BasketInfoAdapter(): RecyclerView.Adapter<BasketInfoAdapter.ViewHolder>() {


    var basketModel = mutableListOf<ProductItme>()
    private val picasso = Picasso.get()
    lateinit var context: Context


    fun setCatalogList(childCategory: List<ProductItme>) {
        this.basketModel = childCategory.toMutableList()
        notifyDataSetChanged()
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.basket_info_item, parent, false)

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

        holder.favorite.visibility = View.GONE
        holder.basket.visibility = View.GONE

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
        val favorite: ImageView = itemView.findViewById(R.id.favorite_info)
        val basket: ImageView = itemView.findViewById(R.id.addToCart)
    }

}