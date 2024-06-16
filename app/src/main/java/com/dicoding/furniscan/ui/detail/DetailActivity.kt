package com.dicoding.furniscan.ui.detail

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.furniscan.R
import com.dicoding.furniscan.Result
import com.dicoding.furniscan.data.local.entity.FavoriteProduct
import com.dicoding.furniscan.data.remote.DataProduct
import com.dicoding.furniscan.databinding.ActivityDetailBinding
import com.dicoding.furniscan.ui.FavoriteModelFactory
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private val favoriteProduct = FavoriteProduct()
    private var favorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.show()
        supportActionBar?.title = "Detail Product"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.llExpandableItem.layoutTransition?.enableTransitionType(LayoutTransition.CHANGING)
        binding.expandable.setOnClickListener {
            val v = if(binding.tvExpandableItem.visibility == View.GONE) View.VISIBLE else View.GONE
            binding.tvExpandableItem.visibility = v
        }

        var selectedProduct = DataProduct()

        if (intent.hasExtra("selected_product")){
            selectedProduct = intent?.getSerializableExtra("selected_product") as DataProduct
            binding.tvTitle.text = selectedProduct?.productName
            val formattedPrice = formatPrice(selectedProduct?.price!!.toDouble())
            binding.tvRating.text = selectedProduct.rating!!.toString()
            binding.tvPrice.text = formattedPrice
            Glide.with(this@DetailActivity)
                .load(selectedProduct.productImage!![0])
                .into(binding.ivItem)
            binding.tvContentItem.text = selectedProduct.description
            val expandableText = """
            Material        : ${selectedProduct.material}
            Fabric           : ${selectedProduct.fabric}
            Dimension    : ${selectedProduct.dimension}
        """.trimIndent()
            binding.tvExpandableItem.text = expandableText
        }


        val factory: FavoriteModelFactory =
            FavoriteModelFactory.getInstance(
                application,
            )
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        favoriteProduct.productId = selectedProduct.productId
        favoriteProduct.id = selectedProduct.productId
        favoriteProduct.productName = selectedProduct.productName
        favoriteProduct.productPrice = selectedProduct.price
        favoriteProduct.productImage = selectedProduct.productImage?.get(0)

        if (intent.hasExtra(EXTRA_DATA)) {
            val productId = intent.getIntExtra(EXTRA_DATA, 0)
            if (productId != null) {
                viewModel.getDetailProduct(productId.toInt()).observe(this) {result ->
                    when (result) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val item = result.data
                            binding.tvTitle.text = item.productName
                            val formattedPrice = formatPrice(item.price!!.toDouble())
                            binding.tvPrice.text = formattedPrice
                            binding.tvContentItem.text = item.description
                            binding.tvRating.text = item.rating!!.toString()
                            Glide.with(this@DetailActivity)
                                .load(item.productImage!![0])
                                .into(binding.ivItem)
                            val expandableText = """
                            Material        : ${item.material}
                            Fabric           : ${item.fabric}
                            Dimension    : ${item.dimension}
                        """.trimIndent()
                            binding.tvExpandableItem.text = expandableText

                            viewModel.getFavoriteProductById(item.productId).observe(this) {
                                if (it != null) {
                                    binding.ivFavorite.setImageResource(R.drawable.ic_fav)
                                    favorite = true
                                } else {
                                    binding.ivFavorite.setImageResource(R.drawable.ic_unfav)
                                    favorite = false
                                }
                            }

                            favoriteProduct.productId = item.productId
                            favoriteProduct.id = item.productId
                            favoriteProduct.productName = item.productName
                            favoriteProduct.productPrice = item.price
                            favoriteProduct.productImage = item.productImage?.get(0)

//                            binding.ivFavorite.setOnClickListener {
//                                if (favorite) {
//                                    viewModel.delete(favoriteProduct)
//                                    binding.ivFavorite.setImageResource(R.drawable.ic_unfav)
//                                    favorite = false
//                                } else {
//                                    viewModel.insert(favoriteProduct)
//                                    binding.ivFavorite.setImageResource(R.drawable.ic_fav)
//                                    favorite = true
//                                }
//                            }
                        }

                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                this,
                                "Error: ${result.error}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {}
                    }

                }
            }

        }

        viewModel.getFavoriteProductById(selectedProduct.productId).observe(this) {
            if (it != null) {
                binding.ivFavorite.setImageResource(R.drawable.ic_fav)
                favorite = true
            } else {
                binding.ivFavorite.setImageResource(R.drawable.ic_unfav)
                favorite = false
            }
        }

        binding.ivFavorite.setOnClickListener {
            if (favorite) {
                viewModel.delete(favoriteProduct)
                binding.ivFavorite.setImageResource(R.drawable.ic_unfav)
                favorite = false
            } else {
//                favoriteProduct.productId = selectedProduct.productId
                viewModel.insert(favoriteProduct)
                binding.ivFavorite.setImageResource(R.drawable.ic_fav)
                favorite = true
            }
        }


    }

    fun formatPrice(price: Double): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(price)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}