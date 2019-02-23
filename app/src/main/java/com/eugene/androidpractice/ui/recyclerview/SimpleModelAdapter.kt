package com.eugene.androidpractice.ui.recyclerview

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.eugene.androidpractice.AppExecutors
import com.eugene.androidpractice.R
import com.eugene.androidpractice.data.model.SimpleModel
import com.eugene.androidpractice.databinding.ListItemSampleBinding
import com.eugene.androidpractice.ui.base.DataBoundListAdapter

class SimpleModelAdapter(appExecutors: AppExecutors, private val callback: ((SimpleModel) -> Unit)?) :
    DataBoundListAdapter<SimpleModel, ListItemSampleBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<SimpleModel>() {
            override fun areItemsTheSame(oldItem: SimpleModel, newItem: SimpleModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SimpleModel, newItem: SimpleModel): Boolean {
                return oldItem.title == newItem.title
            }
        }
    ) {
    override fun createBinding(parent: ViewGroup): ListItemSampleBinding {
        val binding: ListItemSampleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_sample, parent, false
        )
        binding.root.setOnClickListener { binding.item?.let { item -> callback?.invoke(item) } }
        return binding
    }

    override fun bind(binding: ListItemSampleBinding, item: SimpleModel, position: Int) {
        binding.item = item
    }
}