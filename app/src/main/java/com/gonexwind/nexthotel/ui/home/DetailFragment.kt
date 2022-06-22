package com.gonexwind.nexthotel.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.gonexwind.core.utils.Utils.toast
import com.gonexwind.nexthotel.R
import com.gonexwind.nexthotel.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hotel = DetailFragmentArgs.fromBundle(arguments as Bundle).hotel
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.detail_hotel)
            setDisplayHomeAsUpEnabled(true)
        }


        binding.apply {
            imageView.load(hotel.imageUrl)
            nameTextView.text = hotel.name
            cityTextView.text = hotel.city
            rateTextView.text = resources.getString(R.string.rateDetail, hotel.rate)
            reviewTextView.text = resources.getString(R.string.reviewsDetail, hotel.reviews)
            descTextView.text = hotel.description
            priceTextView.text = resources
                .getString(R.string.start_from) + " Rp. " + hotel.priceRange
            hotelStars.rating = hotel.stars.toFloat()

            var isFavorite = hotel.isFavorite
            setStatusFavorite(isFavorite)
            favoriteButton.setOnClickListener {
                if (isFavorite) toast(requireContext(), getString(R.string.added_favorite)) else
                    toast(requireContext(), getString(R.string.removed_favorite))
                isFavorite = !isFavorite
                viewModel.setFavoriteTourism(hotel, isFavorite)
                setStatusFavorite(isFavorite)
            }
        }
    }

    private fun setStatusFavorite(isFavorite: Boolean) {
        if (isFavorite) binding.favoriteButton.setImageDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_pink)
        ) else binding.favoriteButton.setImageDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_border)
        )
    }
}