package com.example.dell.mvvm_bus_arrival.di

import android.util.Log
import com.example.dell.mvvm_bus_arrival.BuildConfig
import com.example.dell.mvvm_bus_arrival.utils.Utils
import com.example.dell.mvvm_bus_arrival.api.Api
import com.example.dell.mvvm_bus_arrival.model.NetworkRepository
import com.example.dell.mvvm_bus_arrival.model.Repository
import com.example.dell.mvvm_bus_arrival.viewmodel.ArsIdViewModelFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

val apiModules: Module = module {
    single {
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .connectionPool(ConnectionPool(5, 20, TimeUnit.SECONDS))
                    .addInterceptor(HttpLoggingInterceptor(
                        HttpLoggingInterceptor.Logger {
                            Log.i("TEST", it)
                        }).apply {
                        level = if (BuildConfig.DEBUG)
                            HttpLoggingInterceptor.Level.BODY
                        else
                            HttpLoggingInterceptor.Level.NONE
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .baseUrl("http://ws.bus.go.kr/api/rest/")
            .build()
            .create(Api::class.java)
    }
}

val getArsIdModules: Module = module {
    factory {
        NetworkRepository(get()) as Repository
    }

    factory {
        Utils()
    }

    factory {
        ArsIdViewModelFactory(get(), get())
    }

}

val appModule = listOf(apiModules, getArsIdModules)