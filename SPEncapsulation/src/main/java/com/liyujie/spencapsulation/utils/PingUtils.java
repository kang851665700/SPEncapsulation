package com.liyujie.spencapsulation.utils;

import com.aliyun.alink.linksdk.tools.AError;
import com.amosnail.networktools.ping.Ping;
import com.amosnail.networktools.ping.PingResultInfo;
import com.amosnail.networktools.ping.PingStatsInfo;
import com.liyujie.spencapsulation.connector.PingFutureListener;

import java.net.UnknownHostException;

public class PingUtils {

    public static void Ping(Ping.PingFutureListener pingFutureListener){
        Ping ping = new Ping.PingBuilder().setAddress("www.baidu.com").setPingTimes(1).setFutureListener(pingFutureListener).setPingIntervalTime(0L) .build();
        try {
            ping.doPing();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            pingFutureListener.onError(e);
        }
    }
}
