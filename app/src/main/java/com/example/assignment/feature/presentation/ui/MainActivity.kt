package com.example.assignment.feature.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.feature.presentation.adapter.MidAdapter
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.feature.data.repository.TransactionRepository
import com.example.assignment.feature.presentation.viewmodel.TransactionViewModel
import com.example.assignment.feature.presentation.viewmodel.TransactionViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = TransactionRepository(this)
        val factory = TransactionViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[TransactionViewModel::class.java]

        viewModel.loadTransactions()

        viewModel.midGroups.observe(this) { midGroups ->
            binding.rvMid.layoutManager = LinearLayoutManager(this)
            binding.rvMid.adapter = MidAdapter(midGroups)
        }

    }
}