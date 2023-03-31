package com.unity3d.player;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.widget.Toast;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;
import java.util.List;

public class testPlugin extends UnityPlayerActivity {

    String Tag = "UnityLog";

    public void GetTest1() {

        Log.d(Tag, "빌드하고 코드 넣기 가능");

        //연결된 장치에서 사용 가능한 모든 드라이버를 찾습니다.
        UsbManager manager = (UsbManager) UnityPlayer.currentActivity.getSystemService(UnityPlayerActivity.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            Log.d(Tag, "아무것도 없다");
            return;
        } else {
            Log.d(Tag, "뭐가 있다.");
        }

        // 사용 가능한 첫 번째 드라이버에 대한 연결을 엽니다.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            //UsbManager.requestPermission(driver.getDevice(), ..) handling here
            return;
        }

        UsbSerialPort port = driver.getPorts().get(0); //대부분의 장치에는 포트가 하나만 있습니다(포트 0).
        try {
            port.open(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            port.setParameters(115200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void GetTest2() {
        runOnUiThread(() -> Toast.makeText(UnityPlayer.currentActivity, "된다.", Toast.LENGTH_SHORT).show());
    }
}
