package com.vipulasri.pelm.ui.weather.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vipulasri.pelm.databinding.BottomSheetCitiesBinding
import com.vipulasri.pelm.ui.weather.WeatherVM
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Vipul Asri on 30/01/22.
 */

@AndroidEntryPoint
class CitiesBottomSheet : BottomSheetDialogFragment() {

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    private val binding: BottomSheetCitiesBinding
        get() = _binding as BottomSheetCitiesBinding

    private val viewModel: WeatherVM by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    private var cityAdapter: CityAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetCitiesBinding.inflate(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityAdapter = CityAdapter().apply {
            submitList(viewModel.getCities())
            onCityClick = { city ->
                viewModel.setSelectedCity(city)
                dismiss()
            }
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cityAdapter
        }
    }

    override fun onDestroy() {
        cityAdapter = null
        _binding = null
        super.onDestroy()
    }

    companion object {
        fun show(fragmentManager: FragmentManager) {
            val bottomSheet = CitiesBottomSheet()
            bottomSheet.show(fragmentManager, "[CITIES_BOTTOM_SHEET]")
        }
    }

}