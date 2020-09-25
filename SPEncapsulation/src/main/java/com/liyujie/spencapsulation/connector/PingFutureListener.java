package com.liyujie.spencapsulation.connector;

import com.amosnail.networktools.ping.PingResultInfo;
import com.amosnail.networktools.ping.PingStatsInfo;

public interface PingFutureListener {
    void onResult(PingResultInfo resultInfo);

    void onFinished(PingStatsInfo statsInfo);

    void onError(Exception e);
}
