package com.fetch.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fetch.myapplication.databinding.RowCandidateBinding
import com.fetch.myapplication.entities.Candidate

class CandidateListAdapter: ListAdapter<Candidate, CandidateListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowCandidateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    object DiffCallback: DiffUtil.ItemCallback<Candidate>() {
        override fun areItemsTheSame(oldItem: Candidate, newItem: Candidate): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Candidate, newItem: Candidate): Boolean {
            return oldItem.name == newItem.name
        }

    }

    class ViewHolder(
        private val binding: RowCandidateBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Candidate) {
            binding.listId.text = item.listId.toString()
            binding.name.text = item.name
            binding.border.isVisible = item.isLastItem
        }
    }

}