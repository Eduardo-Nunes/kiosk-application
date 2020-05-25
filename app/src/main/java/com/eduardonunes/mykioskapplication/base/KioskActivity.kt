package com.eduardonunes.mykioskapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eduardonunes.mykioskapplication.extensions.checkDeviceOwnerApp

abstract class KioskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (checkDeviceOwnerApp()) startLockTask()
    }
}