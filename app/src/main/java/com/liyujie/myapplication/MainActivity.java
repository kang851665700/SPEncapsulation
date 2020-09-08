package com.liyujie.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.liyujie.spencapsulation.connector.OnSerialPortDataCallback;
import com.liyujie.spencapsulation.utils.SerialPortUtils;

public class MainActivity extends AppCompatActivity implements OnSerialPortDataCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        byte[] bytes = new byte[1];
        SerialPortUtils.sendData(bytes,2000,this);
    }

    @Override
    public void onDataReceived(byte[] bytes) {
        //接收到消息干嘛自己看着办
    }

    @Override
    public void onFailure() {
        //超时干嘛自己看着办
    }
}