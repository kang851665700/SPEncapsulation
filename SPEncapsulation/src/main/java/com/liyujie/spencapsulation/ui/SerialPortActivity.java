package com.liyujie.spencapsulation.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dyhdyh.support.countdowntimer.CountDownTimerSupport;
import com.dyhdyh.support.countdowntimer.OnCountDownTimerListener;
import com.kongqw.serialportlibrary.SerialPortManager;
import com.kongqw.serialportlibrary.listener.OnOpenSerialPortListener;
import com.kongqw.serialportlibrary.listener.OnSerialPortDataListener;
import com.liyujie.spencapsulation.connector.OnSerialPortDataCallback;

import java.io.File;

/**
 * activity继承方法
 */
public class SerialPortActivity extends AppCompatActivity {


    private SerialPortManager mSerialPortManager;
    private CountDownTimerSupport mTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /**
     * 串口打开 发送数据
     * @param bytes 传给串口的数据
     * @param millisInFuture 超时时间
     * @param onSerialPortDataCallback 串口回调
     */
    public void sendData(byte[] bytes,long millisInFuture,final OnSerialPortDataCallback onSerialPortDataCallback){
        mSerialPortManager = new SerialPortManager();
        mTimer = new CountDownTimerSupport(millisInFuture, 1000);
        mSerialPortManager.setOnOpenSerialPortListener(new OnOpenSerialPortListener() {
            @Override
            public void onSuccess(File file) {
                Log.d("SerialPortUtils",String.format("串口 [%s] 打开成功", file.getPath()));
            }

            @Override
            public void onFail(File file, Status status) {
                switch (status) {
                    case NO_READ_WRITE_PERMISSION:
                        Log.d("SerialPortUtils","没有读写权限");
                        break;
                    case OPEN_FAIL:
                    default:
                        Log.d("SerialPortUtils","串口打开失败");
                        break;
                }
            }
        });
        mSerialPortManager.setOnSerialPortDataListener(new OnSerialPortDataListener() {
            @Override
            public void onDataReceived(byte[] bytes) {
                if(null != onSerialPortDataCallback){
                    onSerialPortDataCallback.onDataReceived(bytes);
                }

            }

            @Override
            public void onDataSent(byte[] bytes) {
                Log.d("SerialPortUtils","正在开门...");

            }
        }).openSerialPort(new File("/dev/ttyS0"), 9600);
        mTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                if(null != onSerialPortDataCallback){
                    onSerialPortDataCallback.onFailure();
                }
            }
        });

        if(null != bytes && bytes.length > 9){
            if(null != mTimer){
                mTimer.start();
            }
            byte[] sendContentBytes = new byte[6];
            sendContentBytes[0] = bytes[9]; //设备地址
            sendContentBytes[1] = 0;//命令
            sendContentBytes[2] = 1; //桢序列
            sendContentBytes[3] = 1;////数据长度
            sendContentBytes[4] = bytes[9]; //数据在和
            sendContentBytes[5] =(byte)( 0 - sendContentBytes[0] - sendContentBytes[1] - sendContentBytes[2] - sendContentBytes[3] - sendContentBytes[4]); //校验
            boolean sendBytes = mSerialPortManager.sendBytes(sendContentBytes);
            Log.d("SerialPortUtils",sendBytes ? "发送成功" : "发送失败");
        }else {
            Log.d("SerialPortUtils",bytes.length +"多少长度");
        }
    }



    @Override
    protected void onDestroy() {
        if(null != mTimer){
            mTimer.reset();
            mTimer = null;
        }
        if (null != mSerialPortManager) {
            mSerialPortManager.closeSerialPort();
            mSerialPortManager = null;
        }
        super.onDestroy();
    }
}
