package com.example.paykarshop.Adapter

import Child
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paykarshop.databinding.ChipsChildCategoryBinding
import com.squareup.picasso.Picasso

class CatalogChildAdapter: RecyclerView.Adapter<CatalogChildAdapter.MainCatalogChildAdapter>() {


    var catalogsChild = mutableListOf<Child>()
    private val picasso = Picasso.get()
    lateinit var context: Context

    fun setCatalogList(childCategory: List<Child>) {
        this.catalogsChild = childCategory.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCatalogChildAdapter {

        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        val binding = ChipsChildCategoryBinding.inflate(inflater, parent, false)
        return MainCatalogChildAdapter(binding)

    }

    override fun onBindViewHolder(holder: MainCatalogChildAdapter, position: Int) {
     val catalogChild = catalogsChild[position]
        holder.binding.nameOfProduct.text = catalogChild.name


    }

    override fun getItemCount(): Int {
        return  catalogsChild.size
    }


    class MainCatalogChildAdapter(var binding:  ChipsChildCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}