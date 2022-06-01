# Maldefender

Maldefender is an open source app which lets export the network traffic of your device. \
The app simulates a VPN to achieve non-root capture but, contrary to a VPN, the traffic is processed locally into the device.

The application developed was named ‘MalDefender’. When the application is first downloaded and opened,
permission to set up the VPN connection is requested from the user , which is necessary to enable the
traffic data collection as discussed previously. After giving permission, the user is met with a simple main interface
of the application , where there’s a button, Target App, to enable capturing the traffic for a specific
application, as opposed to capturing all traffic, and another button, Start Capture, to initiate the scanning of an
application.

<p align="center">
<img src="[https://raw.githubusercontent.com/Alotaibi1217/Maldefender-Android/master/Screenshots/Screenshot_3.png]" width="190" />
</p>

## Building

1. On Windows, installgitforwindows
2. Clone this repo
3. Inside the repo dir, run `git submodule update --init`. The `submodules` directory should get populated.
4. Open the project in Android Studio, install the appropriate SDK and the NDK
5. Build the app

