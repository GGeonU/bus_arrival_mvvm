package com.example.dell.mvvm_bus_arrival.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {


    @GET("stationinfo/getLowStationByName")
    fun getArsId(@Query("ServiceKey", encoded = true) ServiceKey: String, @Query("stSrch") stSrch: String)
        : Observable<ServiceResult>

    @GET("stationinfo/getRouteByStation")
    fun getRouteByStation(@Query("ServiceKey", encoded = true) ServiceKey: String, @Query("arsId") arsId: String)
            : Observable<ServiceResult>






}