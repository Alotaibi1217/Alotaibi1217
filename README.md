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
<img src="https://raw.githubusercontent.com/Alotaibi1217/Maldefender-Android/master/Screenshots/Screenshot_3.png"  /></p>

If the Target App button is off and the user chooses to start the scan, a popup dialogue shows up for the user to
confirm a scan of the traffic of all applications on the device . On the other hand, when the Target App is
enabled, another screen shows where the user can choose any one of the downloaded applications on her/his phone, and once an app is chosen and the user presses the Start Capture button, a popup dialogue shows up for
the user to confirm the application they want to capture its traffic.

<p align="center">
<img src="https://raw.githubusercontent.com/Alotaibi1217/Maldefender-Android/master/Screenshots/Screenshot_6.png"  /></p>

When the user confirms by pressing ok, the scan starts, and the user is taken back to the home screen, where a
timer shows indicating how long the traffic capture has been going. The traffic is captured for a duratsion
of 15 seconds, which is typically within this period where a malware application would send malicious traffic.
After the capturing period is over, the traffic is saved as a PCAP file in the device, and the user is prompted with
another popup to choose that PCAP file to send over to the server.

<p align="center">
<img src="https://raw.githubusercontent.com/Alotaibi1217/Maldefender-Android/master/Screenshots/Screenshot_7.png"  /></p>

Finally, once the ML model in the server does its part, the result is sent back to the device, and the user
receives a notification of the result, and the result also shows on the home screen of the application.

<p align="center">
<img src="https://raw.githubusercontent.com/Alotaibi1217/Maldefender-Android/master/Screenshots/Screenshot_8.png"  /></p>

Moreover, as an additional security feature, if the user tries to send another type of file by mistake or for
malicious reasons, that is not a PCAP file, the server will send back a result of “invalid file type”, as seen in. The server checks the file type using the file’s magic bytes; which are the first 8 bytes of any file that define its
type, as it’s more reliable than using the extension of the file. If an invalid file type is detected, the server also
deletes the file from its memory.

<p align="center">
<img src="https://raw.githubusercontent.com/Alotaibi1217/Maldefender-Android/master/Screenshots/Screenshot_9.png"  /></p>






## Building

1. On Windows, installgitforwindows
2. Clone this repo
3. Inside the repo dir, run `git submodule update --init`. The `submodules` directory should get populated.
4. Open the project in Android Studio, install the appropriate SDK and the NDK
5. Build the app

