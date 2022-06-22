package com.gonexwind.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gonexwind.core.ui.HotelAdapter
import com.gonexwind.favorite.databinding.FragmentFavoriteBinding
import com.gonexwind.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)

        if (activity != null) {
            val hotelAdapter = HotelAdapter()
            hotelAdapter.onItemClick = {
                val destination = FavoriteFragmentDirections
                    .actionNavigationFavoriteToDetailFragment(it)
                findNavController().navigate(destination)
            }

            viewModel.favoriteHotel.observe(viewLifecycleOwner) { hotels ->
                hotelAdapter.setData(hotels)
                if (hotels.isEmpty()) showEmpty(true) else showEmpty(false)
            }

            binding.recyclerView.adapter = hotelAdapter
        }
    }

    private fun showEmpty(isEmpty: Boolean) {
        if (isEmpty) {
            binding.imageView.visibility = View.VISIBLE
            binding.info.visibility = View.VISIBLE
        } else {
            binding.imageView.visibility = View.GONE
            binding.info.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}