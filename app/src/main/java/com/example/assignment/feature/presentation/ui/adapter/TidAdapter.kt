package com.example.assignment.feature.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.ItemTidBinding
import com.example.assignment.feature.domain.model.TidGroup

class TidAdapter(private val tidGroups: List<TidGroup>) :
    RecyclerView.Adapter<TidAdapter.TidViewHolder>() {

    inner class TidViewHolder(val binding: ItemTidBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var isExpanded = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TidViewHolder(
            ItemTidBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))

    override fun onBindViewHolder(holder: TidViewHolder, position: Int) {
        val group = tidGroups[position]
        holder.binding.tvTid.text = "TID: ${group.tid}"

        holder.binding.transactionRecycler.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.binding.transactionRecycler.adapter = TransactionAdapter(group.transactions)

        holder.binding.ivArrow.rotation = if (holder.isExpanded) 180f else 0f
        holder.binding.transactionRecycler.visibility = if (holder.isExpanded) View.VISIBLE else View.GONE

        holder.binding.tvTid.setOnClickListener {
            holder.isExpanded = !holder.isExpanded
            notifyItemChanged(position)
        }
        holder.binding.ivArrow.setOnClickListener {
            holder.isExpanded = !holder.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = tidGroups.size
}