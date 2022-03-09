package com.example.taxi.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taxi.MainActivity
import com.example.taxi.R
import com.example.taxi.databinding.FragmentHomeBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private val callback = OnMapReadyCallback { googleMap ->
        val myTaxi = LatLng(41.29377817619886, 69.24617069997424)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myTaxi, 15f))
        googleMap.uiSettings.apply {
            isCompassEnabled = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        setUpListener()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setUpListener() {
        binding?.menuIconView?.setOnClickListener {
            (context as MainActivity).navigationDrawer()
        }
    }
}