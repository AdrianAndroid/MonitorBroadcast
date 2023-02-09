package com.monitor

import android.app.Application
import android.content.Context

class MyApp : Application() {
    companion object {
        lateinit var application: Application
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        application = this
    }

    override fun onCreate() {
        super.onCreate()
        ChargingMonitor.init()
    }
}