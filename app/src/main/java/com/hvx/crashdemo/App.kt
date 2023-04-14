package com.hvx.crashdemo

import android.app.Application

/**
 * author : hewenxuan
 * date : 2023/04/13
 * desc :
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        CrashHandler(this).init()
    }
}