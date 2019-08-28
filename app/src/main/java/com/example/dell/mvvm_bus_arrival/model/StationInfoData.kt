package com.example.dell.mvvm_bus_arrival.model

data class StationInfoData (
    var arsId: String,
    var stId: String,
    var stationNm: String,
    var tmX: Double,
    var tmY: Double,
    var arrivalBus: ArrayList<String>,
    var busRouteId: ArrayList<String>
)