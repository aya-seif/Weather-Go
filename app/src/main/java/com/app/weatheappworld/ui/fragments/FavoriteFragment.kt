package com.app.weatheappworld.ui.fragments

import Constants.KEY
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.weatheappworld.databinding.FragmentFavoriteBinding
import com.app.weatheappworld.remote.SearchWeather
import com.app.weatheappworld.ui.fragments.adapter.SearchWeatherAdapter
import com.app.weatheappworld.ui.fragments.viewmodels.HomeViewModel
import com.app.weatheappworld.utils.ResourceWeather


class FavoriteFragment : Fragment(), SearchWeatherAdapter.ItemClickList {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var itemsList: ArrayList<SearchWeather>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.addTextChangedListener {
            binding.etSearch.let {
                try {
                    val text = it.text.toString()
                    if (text.isNotEmpty()) {

                        viewModel.getSearch(KEY, "k", "ar", text)
                    }

                } catch (e: Exception) {
                    Toast.makeText(requireActivity(), e.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }

            }

        }

        itemsList = ArrayList()

        viewModel.liveDataSearch.observe(viewLifecycleOwner,
            {
                when (it.status) {
                    ResourceWeather.Status.LOADING -> {

                    }
                    ResourceWeather.Status.SUCCESS -> {
                        Log.d("successWeather", it.data.toString())
                        itemsList.add(it.data!!)
                        binding.rvSearch.adapter =
                            SearchWeatherAdapter(requireActivity(), this, itemsList)

                    }
                    ResourceWeather.Status.ERROR -> {
                        Log.d("successWeather", it.message.toString())
                    }
                }
            })
    }

    override fun location(lon: Double, lat: Double) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToFragmentHome("$lat", "$lon")
        findNavController().navigate(action)
    }
}