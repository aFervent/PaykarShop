package com.example.paykarshop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paykarshop.bottomfragment.profile.actions.SaleDetailsActivity
import com.example.paykarshop.bottomfragment.profile.history.HistoryDetailActivity
import com.example.paykarshop.databinding.ChipsChildCategoryBinding
import com.example.paykarshop.databinding.HistoryMainItemsBinding
import com.example.paykarshop.models.historyModel.HistoryModel
import com.example.paykarshop.models.historyModel.HistoryModelItem
import com.squareup.picasso.Picasso

class HistoryMainAdapter: RecyclerView.Adapter<HistoryMainAdapter.MainHistoryAdapter>() {

    var historyOrders = mutableListOf<HistoryModelItem>()
    lateinit var context: Context

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick (position: HistoryModelItem)
    }

    fun setonItemClickListener(listener: onItemClickListener) {

        mListener = listener
    }

    fun setCatalogList(childCategory: List<HistoryModelItem>) {
        this.historyOrders = childCategory.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHistoryAdapter {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        val binding = HistoryMainItemsBinding.inflate(inflater, parent, false)
        return MainHistoryAdapter(binding, mListener)

    }

    override fun onBindViewHolder(holder: MainHistoryAdapter, position: Int) {
        val historyOrder = historyOrders[position]
        holder.binding.dateOfOformlen.text = historyOrder.date
        holder.binding.mainPriceOfOrder.text = historyOrder.pirce + " сомони"
        holder.binding.numberOfOrder.text = "Заказ № " + historyOrder.id
        holder.binding.statusText.text = historyOrder.status_id

       holder.itemView.setOnClickListener {
           mListener.onItemClick(historyOrder)
       }

    }

    override fun getItemCount(): Int {
        return  historyOrders.size

    }

    class MainHistoryAdapter(var binding: HistoryMainItemsBinding, val listener: onItemClickListener) : RecyclerView.ViewHolder(binding.root) {


    }
}