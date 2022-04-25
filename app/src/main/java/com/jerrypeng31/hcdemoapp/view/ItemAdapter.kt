package com.jerrypeng31.hcdemoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jerrypeng31.hcdemoapp.databinding.ItemBinding
import com.jerrypeng31.hcdemoapp.getItemData
import com.jerrypeng31.hcdemoapp.model.ItemData
import com.jerrypeng31.hcdemoapp.setItemData

class ItemAdapter(val itemClickListener: (id: Int, isEnable: Boolean) -> Unit) : ListAdapter<ItemData, ItemAdapter.ItemViewHolder> (DiffItemCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, clickListener())
    }

    private fun clickListener() : View.OnClickListener {
        return View.OnClickListener {
            val itemData = it.getItemData()
            itemData?.let {
               itemData -> itemClickListener(itemData.id, !itemData.isEnabled)
            }
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemData = currentList.get(position)
        holder.bind(itemData)
    }

    override fun submitList(list: List<ItemData>?) {
        super.submitList(list?.toMutableList())
    }

    class ItemViewHolder(private val viewBinding: ItemBinding, itemClickListener: View.OnClickListener) : RecyclerView.ViewHolder(viewBinding.root){
        init {
            viewBinding.root.setOnClickListener(itemClickListener)
        }

        fun bind(itemData: ItemData){
            val context = viewBinding.root.context
            val tv = viewBinding.tv
            val colorData = itemData.dataColorData

            viewBinding.root.setItemData(itemData)

            tv.text = itemData.id.toString()
            tv.isEnabled = itemData.isEnabled
            tv.setTextColor(ContextCompat.getColorStateList(context, colorData.resIdTextColor))
            tv.setBackgroundResource(colorData.resIdBgColor)
        }
    }

    class DiffItemCallback : DiffUtil.ItemCallback<ItemData>() {
        override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
            return oldItem == newItem
        }
    }
}
