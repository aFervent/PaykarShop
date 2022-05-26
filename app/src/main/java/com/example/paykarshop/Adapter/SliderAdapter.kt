package com.example.paykarshop.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.paykarshop.databinding.RowSalesBinding
import com.example.paykarshop.models.ModelSliderHomeItem
import com.squareup.picasso.Picasso

class SliderAdapter: RecyclerView.Adapter<SliderAdapter.MainCatalogChildAdapter>(){

    private val picasso = Picasso.get()
    var catalogsChild = mutableListOf<ModelSliderHomeItem>()

    fun setCatalogList(childCategory: List<ModelSliderHomeItem>) {
        this.catalogsChild = childCategory.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCatalogChildAdapter {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RowSalesBinding.inflate(inflater, parent, false)
        return MainCatalogChildAdapter(binding)

    }

    override fun onBindViewHolder(holder: MainCatalogChildAdapter, position: Int) {
        val catalogChild = catalogsChild[position]
        val image = "https://paykar.shop" + catalogChild.detail_picture
        picasso.load(image).into(holder.binding.imageviewSlider)


    }

    override fun getItemCount(): Int {
        return  catalogsChild.size
    }


    class MainCatalogChildAdapter(var binding:  RowSalesBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}