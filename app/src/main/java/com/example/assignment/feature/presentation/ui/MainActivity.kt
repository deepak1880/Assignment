package com.example.assignment.feature.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.R
import com.example.assignment.feature.presentation.ui.adapter.MidAdapter
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.feature.data.datasource.AndroidResourceDataSource
import com.example.assignment.feature.data.repository.TransactionRepositoryImpl
import com.example.assignment.feature.domain.usecase.GetTransactionsUseCase
import com.example.assignment.feature.presentation.viewmodel.TransactionViewModel
import com.example.assignment.feature.presentation.viewmodel.TransactionViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSource = AndroidResourceDataSource(this)
        val repoImpl = TransactionRepositoryImpl(dataSource, R.raw.data)
        val getTransactionsUseCase = GetTransactionsUseCase(repoImpl)
        val factory = TransactionViewModelFactory(getTransactionsUseCase)
        viewModel = ViewModelProvider(this, factory)[TransactionViewModel::class.java]

        binding.rvMid.layoutManager = LinearLayoutManager(this)

        viewModel.midGroups.observe(this) { midGroups ->
            binding.rvMid.adapter = MidAdapter(midGroups)
        }

        viewModel.loadTransactions()
    }
}