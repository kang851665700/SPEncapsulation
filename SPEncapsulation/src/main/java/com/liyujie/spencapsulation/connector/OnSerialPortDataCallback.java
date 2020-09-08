package com.liyujie.spencapsulation.connector;

public interface OnSerialPortDataCallback {
    /**
     * 成功数据接收
     *
     * @param bytes 接收到的数据
     */
    public abstract void onDataReceived(byte[] bytes);

    public abstract void onFailure();
}
