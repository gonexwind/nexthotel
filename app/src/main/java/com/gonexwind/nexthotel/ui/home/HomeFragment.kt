package com.gonexwind.nexthotel.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gonexwind.core.data.Resource
import com.gonexwind.core.ui.HotelAdapter
import com.gonexwind.core.utils.Utils.toast
import com.gonexwind.nexthotel.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val hotelAdapter = HotelAdapter()
            hotelAdapter.onItemClick = {
                val destination = HomeFragmentDirections.actionNavigationHomeToDetailFragment(it)
                findNavController().navigate(destination)
            }

            viewModel.hotel.observe(viewLifecycleOwner) { hotels ->
                if (hotels != null) {
                    when (hotels) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            hotelAdapter.setData(hotels.data)
                            binding.apply {
                                progressBar.visibility = View.GONE
                                recyclerView.adapter = hotelAdapter
                            }
                        }
                        is Resource.Error -> {
                            binding.apply {
                                progressBar.visibility = View.GONE
                                lottieAnimationView.visibility = View.VISIBLE
                                info.visibility = View.VISIBLE
                            }
                            toast(requireContext(), hotels.message.toString())
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}