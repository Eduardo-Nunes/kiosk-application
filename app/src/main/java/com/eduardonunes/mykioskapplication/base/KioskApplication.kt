package com.eduardonunes.mykioskapplication.base

import android.app.Application
import com.eduardonunes.mykioskapplication.extensions.addOnCreateKioskActivityCallback
import com.eduardonunes.mykioskapplication.extensions.findRootView
import com.eduardonunes.mykioskapplication.extensions.startKioskMode
import com.google.android.material.snackbar.Snackbar

class KioskApplication : Application() {

    private var activityLifecycleCallbacks: ActivityLifecycleCallbacks? = null

    override fun onCreate() {
        super.onCreate()
        activityLifecycleCallbacks = addOnCreateKioskActivityCallback(::onCreateActivity)
    }

    private fun onCreateActivity(activity: KioskActivity) {
        activity.startKioskMode { success ->
            if (success) {
                activityLifecycleCallbacks?.run(::unregisterActivityLifecycleCallbacks)
            } else {
                Snackbar.make(
                    activity.findRootView(),
                    "CONTACT ADMINISTRATOR",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}