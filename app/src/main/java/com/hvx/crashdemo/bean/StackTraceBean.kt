package com.hvx.crashdemo.bean

/**
 * author : hewenxuan
 * date : 2023/04/13
 * desc :
 */
class StackTraceBean {
    var className: String? = null
    var fileName: String? = null
    var lineNumber = 0
    var methodName: String? = null
    var isNativeMethod = false

    override fun toString(): String {
        return "{" +
                "\"className\":" + isNull(className) + "," +
                "\"fileName\":" + isNull(fileName) + "," +
                "\"lineNumber\":" + isNull(lineNumber) + "," +
                "\"methodName\":" + isNull(methodName) + "," +
                "\"nativeMethod\":" + isNull(isNativeMethod) +
                "}"
    }

    fun isNull(o: Any?): String {
        return if (o == null) "\"\" " else "\"" + o + "\""
    }
}