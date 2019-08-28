package com.example.dell.mvvm_bus_arrival.model

import com.example.dell.mvvm_bus_arrival.api.ItemList
import com.example.dell.mvvm_bus_arrival.api.MsgBody
import io.reactivex.Observable

interface Repository {
    fun getStationInfo(station: String): Observable<Pair<ItemList, MsgBody>>
}