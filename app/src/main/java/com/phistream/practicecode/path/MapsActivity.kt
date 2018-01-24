package com.phistream.practicecode.path

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.phistream.practicecode.R
import com.google.android.gms.maps.model.CameraPosition
import android.location.Geocoder
import android.widget.Toast
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mList: ArrayList<LatLng>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        mList = ArrayList<LatLng>()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        getAddress(sydney)
        mList.add(sydney)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        val cameraPosition = CameraPosition.Builder()
                .target(sydney)
                .bearing(45f)
                .tilt(90f)
                .zoom(googleMap.cameraPosition.zoom)
                .build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),3000/*ANIMATE_SPEED_TUEN*/,object :GoogleMap.CancelableCallback{
            override fun onFinish() {

            }

            override fun onCancel() {
            }
        })
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.setOnMapClickListener(object : GoogleMap.OnMapClickListener {
            override fun onMapClick(latLng: LatLng) {
                mMap.addMarker(MarkerOptions().position(latLng).title("clicked on" + latLng))
                mList.add(latLng)
                Log.d("previousLoc",""+mList[mList.size-1])
                mMap.addPolyline(PolylineOptions().add(mList[mList.size-2],latLng).width(5F).color(Color.BLUE).geodesic(true))
            }

        })
    }
    fun getAddress( latLng: LatLng){
        var geocoder = Geocoder(this, Locale.getDefault())

       var addresses = geocoder.getFromLocation(-34.0, 151.0, 1) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        val address = addresses.get(0).getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        val city = addresses.get(0).getLocality()
        val state = addresses.get(0).getAdminArea()
        val country = addresses.get(0).getCountryName()
        val postalCode = addresses.get(0).getPostalCode()
        val knownName = addresses.get(0).getFeatureName() // Only if available else return NUL
        Toast.makeText(applicationContext,address+": "+city+": "+country, Toast.LENGTH_SHORT).show()
    }
}
