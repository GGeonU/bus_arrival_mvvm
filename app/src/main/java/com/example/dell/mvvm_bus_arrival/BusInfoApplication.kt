package com.example.dell.mvvm_bus_arrival

import android.app.Application
import com.example.dell.mvvm_bus_arrival.di.appModule
import org.koin.android.ext.android.startKoin

class BusInfoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, appModule)
    }
}