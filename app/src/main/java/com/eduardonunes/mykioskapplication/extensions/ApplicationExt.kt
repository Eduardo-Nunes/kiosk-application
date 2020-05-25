package com.eduardonunes.mykioskapplication.extensions

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.eduardonunes.mykioskapplication.base.KioskActivity

fun Application.addOnCreateKioskActivityCallback(
    onCreate: (KioskActivity) -> Unit
): Application.ActivityLifecycleCallbacks {
    return object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) =
            when (activity) {
                is KioskActivity -> onCreate.invoke(activity)
                else -> Unit
            }

        override fun onActivityDestroyed(activity: Activity) = Unit
        override fun onActivityStarted(activity: Activity) = Unit
        override fun onActivityResumed(activity: Activity) = Unit
        override fun onActivityPaused(activity: Activity) = Unit
        override fun onActivityStopped(activity: Activity) = Unit
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
    }.also { registerActivityLifecycleCallbacks(it) }
}