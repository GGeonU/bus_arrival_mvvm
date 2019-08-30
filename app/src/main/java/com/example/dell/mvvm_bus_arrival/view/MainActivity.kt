package com.example.dell.mvvm_bus_arrival.view


import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dell.mvvm_bus_arrival.R
import com.example.dell.mvvm_bus_arrival.adapter.StationInfoAdapter
import com.example.dell.mvvm_bus_arrival.viewmodel.ArsIdViewModel
import com.example.dell.mvvm_bus_arrival.viewmodel.ArsIdViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import org.koin.android.ext.android.inject
import java.security.MessageDigest

class MainActivity : BaseActivity() {

    private lateinit var arsIdViewModel: ArsIdViewModel
    private val arsIdViewModelFactory: ArsIdViewModelFactory by inject()
    private val adapter = StationInfoAdapter()

    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(tool_bar_layout)
        tool_bar.toolbar_title.text = "정류장 검색"

        arsIdViewModel = ViewModelProviders.of(this, arsIdViewModelFactory).get(ArsIdViewModel::class.java)
        adapter.initList()

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        arsIdViewModel.busArrivalInfo.observe(this, Observer { info ->
            adapter.getItems().clear()
            adapter.addListItem(info)
            adapter.notifyDataSetChanged()
        })

        search_button.setOnClickListener {
            arsIdViewModel.stationInfoList.clear()
            arsIdViewModel.getStationInfo(search_text.text.toString())
        }
    }

    override fun onPause() {
        Log.d("pause", "onPause()")
        super.onPause()
    }
}
