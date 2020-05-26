# kiosk-application
An android source project to show how to create a application in kiosk mode for Android Device with API 23 or newer

# Setup
First you need to setup your adb in $PATH and install fastlane.

After create your project, goto the manifest file and paste the same App, MainActivity and receiver configurations from this source.

Then create your device_admin_receiver.xml in xml folder inside the yout res package,
Create your MyDeviceAdminReceiver and paste all ActivityExt, ApplicationExt, ContextExt, KioskActivity, and KioskApplication files.

Extend the KioskActivity in your MainActivity, and the KioskApplication in your Application file and name your apllication in manifest file.

Then, goto the FastFile and AppFile inside fastlane package, and rename the package from `com.eduardonunes.mykioskapplication` with yout own app package.

After this setup you can goto your terminal and run fastlane install_app_owner_debug to setup the kiosk device admin profile enabled and open your kisok app.

# Tweaks
You may use `showNavigationBar(Boolean)`, `setWindowsImmersiveFocus()`, `enterFullscreen()`, `exitFullscreen()`, and `setAwaysFullScreenMode()` methods to customize your kisok mode navigation in each Activity by your own way :), i recomended use it in `onResume()` method.
