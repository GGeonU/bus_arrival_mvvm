package com.example.dell.mvvm_bus_arrival.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dell.mvvm_bus_arrival.R

class ShowArrivalInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_arrival_info)

        val intent = intent
        val arsId = intent.getStringExtra("arsId")
        val stId = intent.getStringExtra("stId")
        val stationNm = intent.getStringExtra("stationNm")
        val busRouteIdList = intent.getStringArrayListExtra("busRouteIdList")




    }
}
