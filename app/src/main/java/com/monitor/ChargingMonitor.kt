package com.monitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import java.util.concurrent.CopyOnWriteArrayList

/**
 * 充电状态监视器
 */
object ChargingMonitor {
    val TAG = ChargingMonitor::class.simpleName
    private val callbacks = CopyOnWriteArrayList<Callback>()
    private val intentFilter = IntentFilter().apply {
        addAction(Intent.ACTION_POWER_CONNECTED)
        addAction(Intent.ACTION_POWER_DISCONNECTED)
        priority = 1000
    }

    init {
        MyApp.application.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Log.i(TAG, "ChargingMonitor 有广播到了 action=${intent.action}!")
                if (intent.action == Intent.ACTION_POWER_CONNECTED) {
                    callbacks.forEach { callback ->
                        callback.onCharging(true)
                    }
                } else {
                    callbacks.forEach { callback ->
                        callback.onCharging(false)
                    }
                }
            }
        }, intentFilter)
        Log.i(TAG, "注册监听电源插拔成功!")
    }

    fun init() = Unit

    fun addCallback(callback: Callback) {
        callbacks.add(callback)
    }

    fun removeCallback(callback: Callback) {
        callbacks.remove(callback)
    }

    interface Callback {
        fun onCharging(isCharging: Boolean)
    }
}