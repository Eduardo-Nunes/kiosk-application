package com.eduardonunes.mykioskapplication.extensions

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.provider.Settings
import com.eduardonunes.mykioskapplication.base.KioskActivity
import com.eduardonunes.mykioskapplication.receivers.MyDeviceAdminReceiver

fun KioskActivity.startKioskMode(onFinished: (Boolean) -> Unit) {
    if (checkDeviceOwnerApp()) setKioskPolicies(
        _adminComponentName,
        _devicePolicyManager,
        onFinished
    )
    else onFinished.invoke(false)
}

fun Context.checkDeviceOwnerApp(): Boolean {
    return _devicePolicyManager.isDeviceOwnerApp(packageName)
}

private fun Context.setKioskPolicies(
    mAdminComponentName: ComponentName,
    mDevicePolicyManager: DevicePolicyManager,
    onFinished: (Boolean) -> Unit
) {
    mDevicePolicyManager.setLockTaskPackages(mAdminComponentName, arrayOf(packageName))
    setAsDefaultApplication(mDevicePolicyManager to mAdminComponentName)
        .disableKeyguard()
        .setStayAwakeMode()
    onFinished.invoke(true)
}

private fun Context.setAsDefaultApplication(managers: PolicyManagerAndComponent): PolicyManagerAndComponent {
    val intentFilter = IntentFilter(Intent.ACTION_MAIN)
    intentFilter.addCategory(Intent.CATEGORY_HOME)
    intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
    managers.first.addPersistentPreferredActivity(
        managers.second,
        intentFilter, ComponentName(packageName, this.javaClass.name)
    )
    return managers
}

private fun PolicyManagerAndComponent.disableKeyguard(): PolicyManagerAndComponent {
    first.setKeyguardDisabled(second, true)
    return this
}

private fun PolicyManagerAndComponent.setStayAwakeMode(): PolicyManagerAndComponent {
    first.setGlobalSetting(
        second,
        Settings.Global.STAY_ON_WHILE_PLUGGED_IN,
        (BatteryManager.BATTERY_PLUGGED_AC or BatteryManager.BATTERY_PLUGGED_USB
                or BatteryManager.BATTERY_PLUGGED_WIRELESS).toString()
    )

    return this
}

typealias PolicyManagerAndComponent = Pair<DevicePolicyManager, ComponentName>

private val Context._adminComponentName: ComponentName
    get() = MyDeviceAdminReceiver.getComponentName(this)

private val Context._devicePolicyManager: DevicePolicyManager
    get() = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager