package com.monitor

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Process

object Utils {
    fun getProcessName(): String {
        runCatching {
            if (Build.VERSION.SDK_INT >= 28) {
                val name = Application.getProcessName()
                return if (name.isNullOrBlank()) getProcessNameAncient() else name
            }
            return getProcessNameAncient()
        }
        return ""
    }

    private fun getProcessNameAncient(): String {
        runCatching {
            val pid = Process.myPid()
            val am = MyApp.application.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            am.runningAppProcesses.forEach {
                if (it.pid == pid) return it.processName
            }
        }
        return ""
    }
}