package com.example.dell.mvvm_bus_arrival.model

import com.example.dell.mvvm_bus_arrival.api.Api
import com.example.dell.mvvm_bus_arrival.api.ItemList
import com.example.dell.mvvm_bus_arrival.api.MsgBody
import com.example.dell.mvvm_bus_arrival.api.ServiceResult
import io.reactivex.Observable

class NetworkRepository(private val api: Api) : Repository {
    private val key =
        "2ysbgQFrENYJHhR8eRUwPTG6A19dAGm%2BqRXK%2FRaKcaC4Ml%2FHvbpa2i7N3dwrkhnkJD2iQbSPNbBLZyVFq5tBBw%3D%3D"

    override fun getStationInfo(station: String): Observable<Pair<ItemList, MsgBody>> {
        return api.getArsId(key, station)
            .map { stationByNameItem -> stationByNameItem.msgBody!!.itemList }
            .flatMap { Observable.fromIterable(it) }
            .flatMap { itemList ->
                itemList.arsId?.let { it1 ->
                    api.getRouteByStation(key, it1).map { v -> Pair(itemList, v.msgBody!!) }
                }
            }

    }
}