package com.liyujie.spencapsulation.connector;

import com.liyujie.spencapsulation.model.AErrorl;

public interface IDemoCallback {
    void onError(AErrorl aError);

    void onInitDone(Object object);
}
