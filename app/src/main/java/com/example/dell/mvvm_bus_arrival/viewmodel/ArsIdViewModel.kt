package com.example.dell.mvvm_bus_arrival.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dell.mvvm_bus_arrival.utils.Utils
import com.example.dell.mvvm_bus_arrival.model.Repository
import com.example.dell.mvvm_bus_arrival.model.StationInfoData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArsIdViewModel(private val repository: Repository, private val utils: Utils) : DisposableViewModel() {

    private val _busArrivalInfo = MutableLiveData<ArrayList<StationInfoData>>()

    val result = MutableLiveData<Boolean>()

    val busArrivalInfo: LiveData<ArrayList<StationInfoData>> get() = _busArrivalInfo

    var busRouteNm = ArrayList<String>()
    var stationInfoList = ArrayList<StationInfoData>()
    var routeIdList = ArrayList<String>()
    var tmX: Double = 0.0
    var tmY: Double = 0.0

    fun getStationInfo(stStch: String) {
        addDisposable(repository.getStationInfo(stStch)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                _busArrivalInfo.value = stationInfoList
            }
            .subscribe({ pair ->
                result.value = true

                pair.second.itemList!!.forEach {
                    if (it.busRouteType != 2) {
                        val regexResult = utils.regex.findAll(it.busRouteNm!!)
                        it.busRouteNm = utils.getRegex(regexResult)
                        Log.d("stationInfoList", it.busRouteNm)
                    }
                    busRouteNm.add(it.busRouteNm!!)
                    routeIdList.add(it.busRouteId!!)
                }

                stationInfoList.add(
                    StationInfoData(
                        pair.first.arsId!!,
                        pair.first.stId!!,
                        pair.first.stNm!!,
                        pair.first.tmX!!,
                        pair.first.tmY!!,
                        busRouteNm,
                        routeIdList
                    )
                )
            }, {
                Log.d("failed", it.message!!)
            })
        )
    }


}