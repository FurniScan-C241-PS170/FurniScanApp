package com.dicoding.furniscan.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.furniscan.data.local.entity.FavoriteProduct
import com.dicoding.furniscan.data.remote.DataProduct
import com.dicoding.furniscan.databinding.ItemCardBinding
import com.dicoding.furniscan.ui.detail.DetailActivity
import com.dicoding.furniscan.utils.formatRupiah

class FavoriteAdapter: ListAdapter<FavoriteProduct, FavoriteAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder (private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : FavoriteProduct){
            Glide.with(itemView.context)
                .load(item.productImage)
                .into(binding.imageView)
            binding.title.text = item.productName
            binding.description.text = item.productPrice.toString()
            val formattedPrice = formatRupiah(item.productPrice!!.toDouble())
            binding.description.text = formattedPrice
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =ItemCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listUser = getItem(position)
        holder.bind(listUser)

        holder.itemView.setOnClickListener {
            val intent = Intent( holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, listUser.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteProduct>(){
            override fun areItemsTheSame(oldItem: FavoriteProduct, newItem: FavoriteProduct): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: FavoriteProduct, newItem: FavoriteProduct): Boolean {
                return oldItem == newItem
            }
        }
    }
}