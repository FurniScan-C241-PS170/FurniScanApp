package com.dicoding.furniscan.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.furniscan.adapter.FavoriteAdapter
import com.dicoding.furniscan.databinding.FragmentFavoriteBinding
import com.dicoding.furniscan.ui.FavoriteModelFactory


class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireActivity().application
        val factory = FavoriteModelFactory.getInstance(application)

        val viewModel = ViewModelProvider(this, factory).get(FavoriteViewModel::class.java)

        val adapter = FavoriteAdapter()
        val layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvFavorite.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFavorite.addItemDecoration(itemDecoration)

        viewModel.getAllData().observe(viewLifecycleOwner){list ->
            if (list != null){
                adapter.submitList(list)
                binding.rvFavorite.adapter = adapter
                binding.rvFavorite.visibility = View.VISIBLE
                binding.ivEmpty.visibility = View.GONE
                binding.tvEmpty.visibility = View.GONE
                println(list)
            }

            if (list.isEmpty()){
                binding.rvFavorite.visibility = View.GONE
                binding.ivEmpty.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.VISIBLE
            }
        }
    }


}