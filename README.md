Warehouse
=========
A prototype app-based warehouse management system.

Based on an Android App written in Kotlin (Jetpack Compose) and an API written in Typescript (Nodejs) using the express Framework.


Android App
===========

Configuration
-------------
Prerequisites
* Android device (Android 7.0 or higher)
* An internet connection
* A server hosting the API

In the Kotlin project files (the 'app' folder), the API's IP address and port needs to be pre-configured. It can be found in the retrofit service builder and has to be set to the IP and port of the the server that the API runs on.

The app is currently not available on any app store as it requires configuration so the only way to create it, is to use Android Studio. In Android Studio, open the project files of the Android app code. If necessary configure IP and port as specified above. Connect your Android device with activated developer mode (must be done in advance) to the computer that Android Studio is running on. Android Studio will recognize the device and offer the option to compile to device. Click on the button with your device's name on it. The app will then be installed on the device and is ready for use.


