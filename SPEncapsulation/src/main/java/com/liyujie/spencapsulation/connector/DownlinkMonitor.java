package com.liyujie.spencapsulation.connector;

import com.liyujie.spencapsulation.model.AMessagel;
import com.liyujie.spencapsulation.model.ConnectStatel;

public interface DownlinkMonitor {
    void onNotify(String connectId, String topic, AMessagel aMessagel);

    boolean shouldHandle(String s, String s1);

    void onConnectStateChange(String s, String connectState);
}
