package com.polije.androidexpertsubmission1.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.polije.androidexpertsubmission1.core.databinding.ListItemMenuBinding
import com.polije.androidexpertsubmission1.core.domain.model.Agent

class AgentAdapter : ListAdapter<Agent, AgentAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Agent) -> Unit)? = null

    inner class ListViewHolder(private var binding: ListItemMenuBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Agent) {
            Glide.with(itemView.context)
                .load(data.fullPortrait)
                .into(binding.ivItemImage)
            binding.tvItemTitle.text = data.displayName
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            ListItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Agent> =
            object : DiffUtil.ItemCallback<Agent>() {
                override fun areItemsTheSame(oldItem: Agent, newItem: Agent): Boolean {
                    return oldItem.uuid == newItem.uuid
                }

                override fun areContentsTheSame(oldItem: Agent, newItem: Agent): Boolean {
                    return oldItem == newItem
                }
            }
    }
}