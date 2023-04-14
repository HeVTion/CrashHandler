package com.hvx.crashdemo.bean

import android.os.Build

/**
 * author : hewenxuan
 * date : 2023/04/13
 * desc :
 */
class ErrorBean {
    private val brand = Build.BRAND
    private val model = Build.MODEL
    private val app_name: String? = null
    private val package_name: String? = null
    private val app_version_name: String? = null
    private val app_version_code: String? = null
    private val sdk_version_name: String? = null
    private val sdk_version_code: String? = null
    private val android_id: String? = null
    private val mobile: String? = null
    private val isDebug: String? = null
    private val type: Throwable? = null
    private val cause: Any? = null
    private val localizedMessage: String? = null
    private val message: String? = null
    private val stackTrace: List<StackTraceBean>? = null
    private val date: String? = null

}