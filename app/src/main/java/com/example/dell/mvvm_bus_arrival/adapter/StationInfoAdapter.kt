package com.example.dell.mvvm_bus_arrival.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dell.mvvm_bus_arrival.R
import com.example.dell.mvvm_bus_arrival.model.StationInfoData
import com.example.dell.mvvm_bus_arrival.view.MapViewActivity
import com.example.dell.mvvm_bus_arrival.view.ShowArrivalInfo
import kotlinx.android.synthetic.main.arrival_info_item.view.*

class StationInfoAdapter : RecyclerView.Adapter<StationInfoAdapter.ViewHolder>() {

    private lateinit var items: ArrayList<StationInfoData>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.station_info.text = "${items[position].stationNm} [${items[position].arsId}]"
        var arrivalBusNmText = "정차 버스: "
        for (i in 0..items[position].arrivalBus.size) {
            arrivalBusNmText += "${items[position].arrivalBus[i]}  "
            if (i == 5) {
                arrivalBusNmText += "..."
                break
            }
        }
        holder.arrival_bus.text = arrivalBusNmText

        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, ShowArrivalInfo::class.java)
            intent.putExtra("arsId", items[position].arsId)
            intent.putExtra("stId", items[position].stId)
            intent.putExtra("stationNm", items[position].stationNm)
            intent.putStringArrayListExtra("busRouteIdList", items[position].busRouteId)

            it.context.startActivity(intent)
        }

        holder.go_map_view.setOnClickListener {
            val intent = Intent(it.context, MapViewActivity::class.java)
            intent.putExtra("stationNm", items[position].stationNm)
            intent.putExtra("arsId", items[position].arsId)
            intent.putExtra("tmX", items[position].tmX)
            intent.putExtra("tmY", items[position].tmY)
            it.context.startActivity(intent)
        }


    }

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.arrival_info_item, parent, false)){

        val station_info = itemView.station_info
        val arrival_bus = itemView.arrival_bus
        val go_map_view = itemView.map_view_button

    }

    fun initList() {
        items = ArrayList()
    }

    fun addListItem(data: ArrayList<StationInfoData>) {
        items.addAll(data)
    }

    fun getItems() = items

}