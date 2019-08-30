package com.example.dell.mvvm_bus_arrival.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dell.mvvm_bus_arrival.R
import kotlinx.android.synthetic.main.activity_map_view.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapViewActivity : AppCompatActivity() {

    private lateinit var stationNm: String
    private lateinit var arsId: String
    private var tmX: Double = 0.0
    private var tmY: Double = 0.0

    private lateinit var mapView: MapView
    private lateinit var mapPoint: MapPoint

    private lateinit var marker: MapPOIItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)

        init()
        setMapView()
        setMarker()
    }

    fun init(){
        stationNm = intent.getStringExtra("stationNm")!!
        arsId = intent.getStringExtra("arsId")
        tmX = intent.getDoubleExtra("tmX", 0.0)
        tmY = intent.getDoubleExtra("tmY", 0.0)
    }

    fun setMapView(){
        mapView = MapView(this)
        mapView.setDaumMapApiKey("2174202acffdff3e0e0cabefcefef521")
        mapPoint = MapPoint.mapPointWithGeoCoord(tmY, tmX)
        mapView.setMapCenterPoint(mapPoint, true)
        mapView.setZoomLevel(2, true)
        map_view.addView(mapView)
    }

    fun setMarker(){
        marker = MapPOIItem()
        marker.itemName = "$stationNm[$arsId]"
        marker.mapPoint = mapPoint
        marker.markerType = MapPOIItem.MarkerType.BluePin
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        mapView.addPOIItem(marker)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
