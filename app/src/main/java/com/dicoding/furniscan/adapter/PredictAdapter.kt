package com.dicoding.furniscan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.furniscan.data.remote.DataPredict
import com.dicoding.furniscan.databinding.ItemCardBinding
import com.dicoding.furniscan.utils.formatRupiah

class PredictAdapter : ListAdapter<DataPredict, PredictAdapter.MyViewHolder>(DIFF_CALLBACK) {
    var onClick: ((DataPredict) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem!!, onClick)
    }

    class MyViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: DataPredict, onClick: ((DataPredict) -> Unit)?) {
            binding.title.text = currentItem.productName
            binding.description.text = currentItem.price.toString()
            val formattedPrice = formatRupiah(currentItem.price!!.toDouble())
            binding.description.text = formattedPrice

            binding.root.setOnClickListener {
                onClick?.invoke(currentItem)
            }

            Glide.with(binding.root)
                .load(currentItem.productImage)
//                .error(R.drawable.login_pic)
                .into(binding.imageView)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataPredict>() {
            override fun areItemsTheSame(oldItem: DataPredict, newItem: DataPredict): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataPredict,
                newItem: DataPredict
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}