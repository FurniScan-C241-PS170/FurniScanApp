package com.dicoding.furniscan.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.furniscan.ui.Profile.ProfileFragment
import com.dicoding.furniscan.R
import com.dicoding.furniscan.adapter.ProductAdapter
import com.dicoding.furniscan.databinding.FragmentHomeBinding
import com.dicoding.furniscan.ui.HomeModelFactory
import com.dicoding.furniscan.ui.detail.DetailActivity
import com.dicoding.furniscan.Result
import com.dicoding.furniscan.ui.category.CategoryFragment


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFurniture.layoutManager = GridLayoutManager(context, 2)

        binding.profile.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainLayout, ProfileFragment())
                .commit()
        }


        binding.catChair.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainLayout, CategoryFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.catTable.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainLayout, CategoryFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.catSofa.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainLayout, CategoryFragment())
                .addToBackStack(null)
                .commit()
        }

        val factory: HomeModelFactory =
            HomeModelFactory.getInstance(
                requireActivity()
            )
        viewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]

        setupAction()
    }

    private fun setupAction() {
        val adapterProduct = ProductAdapter()
        binding.rvFurniture.apply {
            adapter = adapterProduct
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }

        adapterProduct.onClick = { product ->
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            val outState = Bundle()
            outState.putSerializable("selected_product", product)
            intent.putExtras(outState)
            startActivity(intent)
        }
        viewModel.getProduct().observe(requireActivity()) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE

                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val item = result.data
                    adapterProduct.submitList(item.data)

                }

                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvFurniture.visibility = View.GONE
                    binding.stateError.visibility = View.VISIBLE
                    binding.stateTitle.visibility = View.VISIBLE
                    binding.stateMessage.visibility = View.VISIBLE
                }

                else -> {}
            }
        }
    }
}