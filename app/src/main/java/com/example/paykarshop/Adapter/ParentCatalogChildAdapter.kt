package com.example.paykarshop.Adapter

import Child
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paykarshop.bottomfragment.catalog.CatalogChildActivity
import com.example.paykarshop.databinding.CatalogChildItemBinding
import com.example.paykarshop.databinding.ChipsChildCategoryBinding
import com.example.paykarshop.databinding.SalatsInfoBinding
import com.example.paykarshop.view.WatchAllCatalogActivity
import com.squareup.picasso.Picasso
import android.R



class ParentCatalogChildAdapter: RecyclerView.Adapter<ParentCatalogChildAdapter.MainCatalogChildAdapter>()  {

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
        val binding = CatalogChildItemBinding.inflate(inflater, parent, false)
        return MainCatalogChildAdapter(binding)

    }

    override fun onBindViewHolder(holder: MainCatalogChildAdapter, position: Int) {
        val catalogChild = catalogsChild[position]
        holder.binding.nameOfProductChild.text = catalogChild.name

        val productsChildAdapter = ProductsChildAdapter()

        holder.binding.recyclerviewProductInfo.adapter = productsChildAdapter
        productsChildAdapter.setProductList(catalogChild.products)

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putString("sectionIdMain", catalogChild.id.toString()) //InputString: from the EditText
        editor.commit()
        Log.d("WATCHALl", catalogChild.id.toString())
        holder.binding.watchAll.setOnClickListener {


            val intent = Intent(context, WatchAllCatalogActivity::class.java)
            context.startActivity(intent)

        }


    }


    override fun getItemCount(): Int {
        return  catalogsChild.size
    }

    class MainCatalogChildAdapter(var binding: CatalogChildItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}