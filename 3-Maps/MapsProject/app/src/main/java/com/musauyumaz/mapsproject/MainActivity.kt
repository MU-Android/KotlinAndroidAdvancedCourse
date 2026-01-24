package com.musauyumaz.mapsproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MainActivity : AppCompatActivity() {
    private lateinit var mapView: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Configuration.getInstance().userAgentValue = packageName
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        mapView.setMultiTouchControls(true)

        val startPoint = GeoPoint(38.7312, 35.4787)
        mapView.controller.setZoom(13.0)
        mapView.controller.setCenter(startPoint)

        val marker = Marker(mapView)
        marker.position = startPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = "Kayseri"
        marker.snippet = "Burası Kayseri Merkez"
        mapView.overlays.add(marker)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}