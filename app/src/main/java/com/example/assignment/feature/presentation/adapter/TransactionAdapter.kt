package com.example.assignment.feature.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.ItemTransactionBinding
import com.example.assignment.feature.data.model.Transaction

class TransactionAdapter(private val list: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    inner class TransactionViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TransactionViewHolder(
            ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = list[position]
        holder.binding.tvAmount.text = "Amount: ${transaction.amount}"
        holder.binding.tvNarration.text = "Narration: ${transaction.narration}"
    }

    override fun getItemCount(): Int = list.size
}