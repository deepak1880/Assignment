package com.example.assignment.feature.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.ItemMidBinding
import com.example.assignment.feature.domain.model.MidGroup

class MidAdapter(private val midGroups: List<MidGroup>) :
    RecyclerView.Adapter<MidAdapter.MidViewHolder>() {

    inner class MidViewHolder(val binding: ItemMidBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var isExpanded = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MidViewHolder(
            ItemMidBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: MidViewHolder, position: Int) {
        val group = midGroups[position]
        holder.binding.tvMid.text = "MID: ${group.mid}"
        Log.e("group.Mid", "onBindViewHolder: $group")

        holder.binding.rvTid.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.binding.rvTid.adapter = TidAdapter(group.tidGroups)

        holder.binding.rvTid.visibility = if (holder.isExpanded) View.VISIBLE else View.GONE
        holder.binding.ivExpand.rotation = if (holder.isExpanded) 180f else 0f

        holder.binding.tvMid.setOnClickListener {
            holder.isExpanded = !holder.isExpanded
            notifyItemChanged(position)
        }
        holder.binding.ivExpand.setOnClickListener {
            holder.isExpanded = !holder.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = midGroups.size
}