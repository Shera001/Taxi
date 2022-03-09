package com.example.taxi.presentation.trip_detail

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.taxi.R
import com.example.taxi.databinding.FragmentTripDetailBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TripDetailFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentTripDetailBinding? = null
    private val binding get() = _binding!!

    private var googleMap: GoogleMap? = null

    private var list: List<LatLng> = ArrayList(6)

    private val viewModel: TripDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTripDetailBinding.inflate(layoutInflater, container, false)
        binding.mapView.apply {
            onCreate(savedInstanceState?.getBundle(MAP_VIEW_BUNDLE_KEY))
            getMapAsync(this@TripDetailFragment)
        }

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.roadList.collect { items ->
                list = items
                drawPolyline()
            }
        }

        setUpListener()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY) ?: Bundle()
        outState.putBundle(
            MAP_VIEW_BUNDLE_KEY,
            mapViewBundle
        )
        binding.mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onDestroyView() {
        binding.mapView.onDestroy()
        _binding = null
        super.onDestroyView()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    private fun setUpListener() {
        binding.backToTripImage.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_tripDetailFragment_to_nav_trip)
        }
    }

    private fun drawPolyline() {
        val latLng = list[((list.size/2) - 1)]
//        val bottomBoundary = latLng.latitude - .1
//        val leftBoundary = latLng.longitude - .1
//        val topBoundary = latLng.latitude + .1
//        val rightBoundary = latLng.longitude + .1
//
//        val mapBoundary = LatLngBounds(
//            LatLng(bottomBoundary, leftBoundary),
//            LatLng(topBoundary, rightBoundary)
//        )
        googleMap?.apply {
            moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))
            addMarker(
                MarkerOptions()
                    .flat(true)
                    .position(list[0])
                    .icon(fromVectorToBitmap(R.drawable.ic_start, Color.parseColor("#FF453A")))
                    .anchor(0.6F, 0.6F)
            )
            addPolyline(
                PolylineOptions().apply {
                    width(15f)
                    color(Color.parseColor("#4A7DFF"))
                    jointType(JointType.ROUND)
                    startCap(ButtCap())
                    endCap(ButtCap())
                    addAll(list)
                }
            )
            addMarker(
                MarkerOptions()
                    .position(list[(list.size - 1)])
                    .icon(fromVectorToBitmap(R.drawable.ic_end_point, Color.parseColor("#4A7DFF")))
                    .anchor(0.5F, 0.6F)
            )
        }
    }

    private fun fromVectorToBitmap(id: Int, color: Int): BitmapDescriptor {
        val vectorDrawable: Drawable = ResourcesCompat.getDrawable(resources, id, null)
            ?: return BitmapDescriptorFactory.defaultMarker()

        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        DrawableCompat.setTint(vectorDrawable, color)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    companion object {
        const val MAP_VIEW_BUNDLE_KEY = "map_view_bundle_key"
    }
}