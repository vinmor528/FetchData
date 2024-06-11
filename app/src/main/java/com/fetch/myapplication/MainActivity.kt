package com.fetch.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.fetch.myapplication.adapter.CandidateListAdapter
import com.fetch.myapplication.databinding.ActivityMainBinding
import com.fetch.myapplication.entities.LoadStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private val adapter = CandidateListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter

        // Observe ViewModel data
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it == LoadStatus.LOADING
            binding.recyclerView.isVisible = it == LoadStatus.SUCCESS
        }
        viewModel.candidateList.observe(this) {
            adapter.submitList(it)
        }

        viewModel.loadData()
    }
}