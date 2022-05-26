package com.example.paykarshop.viewModel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.paykarshop.bottomfragment.catalog.CatalogChildActivity
import com.example.paykarshop.databinding.AdapterCategoryBinding
import com.example.paykarshop.models.ModelCatalog
import com.squareup.picasso.Picasso

class CatalogMainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    var catalogs = mutableListOf<ModelCatalog>()
    private val picasso = Picasso.get()
    lateinit var context: Context

    fun setCatalogList(movies: List<ModelCatalog>) {
        this.catalogs = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context

        val binding = AdapterCategoryBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val catalog = catalogs[position]
        holder.binding.sectionNameCategory.text = catalog.sectionName
        val image = "https://paykar.shop" + catalog.sectionPicture
        picasso.load(image).into(holder.binding.imageview)


        holder.binding.catalogLayout.setOnClickListener {
            val intent = Intent(context, CatalogChildActivity::class.java)
            intent.putExtra("sectionId", catalog.sectionId)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return catalogs.size
    }
}

class MainViewHolder(var binding: AdapterCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

}
