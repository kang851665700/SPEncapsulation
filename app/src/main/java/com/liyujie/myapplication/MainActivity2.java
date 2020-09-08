package com.liyujie.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.liyujie.spencapsulation.connector.OnSerialPortDataCallback;
import com.liyujie.spencapsulation.ui.SerialPortActivity;
import com.liyujie.spencapsulation.utils.SerialPortUtils;

public class MainActivity2 extends SerialPortActivity implements OnSerialPortDataCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        byte[] bytes = new byte[1];
        sendData(bytes,2000,this);
    }

    @Override
    public void onDataReceived(byte[] bytes) {

    }

    @Override
    public void onFailure() {

    }
}