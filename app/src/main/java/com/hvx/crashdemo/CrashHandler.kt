package com.hvx.crashdemo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Thread.UncaughtExceptionHandler
import kotlin.system.exitProcess


/**
 * author : hewenxuan
 * date : 2023/04/13
 * desc :
 */
class CrashHandler constructor(private val context: Context) : UncaughtExceptionHandler {

    init {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    fun init() {}

    override fun uncaughtException(p0: Thread, p1: Throwable) {
        val stackTrace = Log.getStackTraceString(p1)
        writeFile(stackTrace)
        saveString(stackTrace)
        startActivity()
        killApp()
    }

    private fun killApp() {
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(0)
    }

    private fun startActivity() {
        // 在这里进行异常处理
        val intent = Intent(context, DeveloperActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    @SuppressLint("ApplySharedPref")
    fun saveString(value: String?) {
        val editor = context.getSharedPreferences("data", Context.MODE_PRIVATE)!!.edit()
        editor.putString("error", value)
        editor.commit()
    }

    companion object {
        fun getString(context: Context): String? {
            return context.getSharedPreferences("data", Context.MODE_PRIVATE)!!
                .getString("error", "defaultValue")
        }

        // 读取文件方法
        fun readFile(context: Context): String? {
            // 获取应用程序专用目录
            val file = File(context.getExternalFilesDir(null), "CrashHandler")
            val sb = StringBuilder()
            try {
                // 创建文件输入流
                val fis = FileInputStream(file)
                // 创建输入流读取器
                val reader = BufferedReader(InputStreamReader(fis))
                // 读取文件内容
                var line = reader.readLine()
                while (line != null) {
                    sb.append(line)
                    line = reader.readLine()
                }
                // 关闭输入流和读取器
                reader.close()
                fis.close()
                // 输出成功信息
                Log.d("TAG", "文件读取成功")
            } catch (e: IOException) {
                // 输出错误信息
                Log.e("TAG", "文件读取失败", e)
            }
            return sb.toString()
        }

        fun reView(context: Context) {
            val intent: Intent? = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName())
            intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }

    fun writeFile(content: String) {
        // 获取应用程序专用目录
        val file: File = File(context.getExternalFilesDir(null), "CrashHandler")
        try {
            // 创建文件输出流
            val fos = FileOutputStream(file)
            // 将内容写入文件
            fos.write(content.toByteArray())
            // 关闭文件输出流
            fos.close()
            // 输出成功信息
            Log.d("TAG", "文件写入成功")
        } catch (e: IOException) {
            // 输出错误信息
            Log.e("TAG", "文件写入失败", e)
        }
    }

}