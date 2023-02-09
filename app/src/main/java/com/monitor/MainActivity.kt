package com.monitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerChargingMonitor();
    }

    private fun registerChargingMonitor() {
        ChargingMonitor.addCallback(object : ChargingMonitor.Callback {

            override fun onCharging(isCharging: Boolean) {
                Log.i(MainActivity::class.simpleName, "isCharging -> $isCharging")
            }
        })
    }
}