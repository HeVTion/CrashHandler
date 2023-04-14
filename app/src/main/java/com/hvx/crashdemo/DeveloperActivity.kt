package com.hvx.crashdemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.hvx.crashdemo.databinding.ActivityDeveloperBinding


class DeveloperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDeveloperBinding = ActivityDeveloperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.errorInfo.text = CrashHandler.readFile(this)
        binding.errorInfo.movementMethod = ScrollingMovementMethod.getInstance();

        binding.button2.setOnClickListener {
            CrashHandler.reView(it.context)
            finish()
        }
    }


}