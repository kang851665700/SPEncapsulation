package com.liyujie.spencapsulation.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.api.IoTApiClientConfig;
import com.aliyun.alink.linkkit.api.ILinkKitConnectListener;
import com.aliyun.alink.linkkit.api.IoTMqttClientConfig;
import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linkkit.api.LinkKitInitParams;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tools.AError;

import java.util.HashMap;
import java.util.Map;

public class InitManagerUtils {

    /**
     * MQTT初始化
     * @param mContext 上下文
     * @param productKey  产品类型
     * @param deviceName  设备名称
     * @param deviceSecret 设备密钥
     * @param Address mqtt的地址
     * @param iConnectNotifyListener 连接的回调
     * @param iLinkKitConnectListener 初始化的回调
     */
    public static void Initauthentication(final Context mContext,
                                          String productKey,
                                          String deviceName,
                                          String deviceSecret,
                                          String Address,
                                          IConnectNotifyListener iConnectNotifyListener, final ILinkKitConnectListener iLinkKitConnectListener) {
        // 构造三元组信息对象
        DeviceInfo deviceInfo = new DeviceInfo();
        // 产品类型
        deviceInfo.productKey = productKey;
        // 设备名称
        deviceInfo.deviceName = deviceName;
        // 设备密钥
        deviceInfo.deviceSecret = deviceSecret;
        // 产品密钥
        deviceInfo.productSecret = "";
        //  全局默认域名
        IoTApiClientConfig userData = new IoTApiClientConfig();
        // 设备的一些初始化属性，可以根据云端的注册的属性来设置。
        /**
         * 物模型初始化的初始值
         * 如果这里什么属性都不填，物模型就没有当前设备相关属性的初始值。
         * 用户调用物模型上报接口之后，物模型会有相关数据缓存。
         * 用户根据时间情况设置
         */

        Map<String, ValueWrapper> propertyValues = new HashMap<>();
        // 示例
//        propertyValues.put("LightSwitch", new ValueWrapper.BooleanValueWrapper(0));
        LinkKitInitParams params = new LinkKitInitParams();
        params.deviceInfo = deviceInfo;
        params.propertyValues = propertyValues;
        params.connectConfig = userData;

        /**
         * 慎用：如不清楚是否要设置，可以不设置该参数
         * Mqtt 相关参数设置
         * 域名、产品密钥、认证安全模式等；
         */
        IoTMqttClientConfig clientConfig = new IoTMqttClientConfig(productKey, deviceName,deviceSecret);
        // 对应 receiveOfflineMsg = !cleanSession, 默认不接受离线消息 false
        clientConfig.receiveOfflineMsg = true;//cleanSession=0 接受离线消息
        clientConfig.receiveOfflineMsg = false;//cleanSession=1 不接受离线消息
        clientConfig.channelHost =productKey + Address;//线上
        // 设置 mqtt 请求域名，默认"{pk}.iot-as-mqtt.cn-shanghai.aliyuncs.com:1883" ,如果无具体的业务需求，请不要设置。
        // 文件配置测试 itls
        if ("itls_secret".equals(deviceSecret)){
            clientConfig.channelHost = productKey + Address;//线上
            clientConfig.productSecret = deviceSecret;
            clientConfig.secureMode = 8;
        }
        params.mqttClientConfig = clientConfig;
        // 测试clientId 可以设置的时候去掉注释即可
        // 注册下行监听，包括长连接的状态和云端下行的数据
        LinkKit.getInstance().registerOnPushListener(iConnectNotifyListener);

        /**
         * 设备初始化建联
         * onError 初始化建联失败，如果因网络问题导致初始化失败，需要用户重试初始化
         * onInitDone 初始化成功
         */
        LinkKit.getInstance().init(mContext, params, iLinkKitConnectListener);
    }

    /**
     * MQTT初始化2
     * @param mContext 上下文
     * @param productKey  产品类型
     * @param deviceName  设备名称
     * @param deviceSecret 设备密钥
     * @param Address mqtt的地址
     * @param iLinkKitConnectListener 初始化的回调
     */
    public static void Initauthentication(final Context mContext,
                                          String productKey,
                                          String deviceName,
                                          String deviceSecret,
                                          String Address,
                                         final ILinkKitConnectListener iLinkKitConnectListener) {
        // 构造三元组信息对象
        DeviceInfo deviceInfo = new DeviceInfo();
        // 产品类型
        deviceInfo.productKey = productKey;
        // 设备名称
        deviceInfo.deviceName = deviceName;
        // 设备密钥
        deviceInfo.deviceSecret = deviceSecret;
        // 产品密钥
        deviceInfo.productSecret = "";
        //  全局默认域名
        IoTApiClientConfig userData = new IoTApiClientConfig();
        // 设备的一些初始化属性，可以根据云端的注册的属性来设置。
        /**
         * 物模型初始化的初始值
         * 如果这里什么属性都不填，物模型就没有当前设备相关属性的初始值。
         * 用户调用物模型上报接口之后，物模型会有相关数据缓存。
         * 用户根据时间情况设置
         */

        Map<String, ValueWrapper> propertyValues = new HashMap<>();
        // 示例
//        propertyValues.put("LightSwitch", new ValueWrapper.BooleanValueWrapper(0));
        LinkKitInitParams params = new LinkKitInitParams();
        params.deviceInfo = deviceInfo;
        params.propertyValues = propertyValues;
        params.connectConfig = userData;

        /**
         * 慎用：如不清楚是否要设置，可以不设置该参数
         * Mqtt 相关参数设置
         * 域名、产品密钥、认证安全模式等；
         */
        IoTMqttClientConfig clientConfig = new IoTMqttClientConfig(productKey, deviceName,deviceSecret);
        // 对应 receiveOfflineMsg = !cleanSession, 默认不接受离线消息 false
        clientConfig.receiveOfflineMsg = true;//cleanSession=0 接受离线消息
        clientConfig.receiveOfflineMsg = false;//cleanSession=1 不接受离线消息
        clientConfig.channelHost =productKey + Address;//线上
        // 设置 mqtt 请求域名，默认"{pk}.iot-as-mqtt.cn-shanghai.aliyuncs.com:1883" ,如果无具体的业务需求，请不要设置。
        // 文件配置测试 itls
        if ("itls_secret".equals(deviceSecret)){
            clientConfig.channelHost = productKey + Address;//线上
            clientConfig.productSecret = deviceSecret;
            clientConfig.secureMode = 8;
        }
        params.mqttClientConfig = clientConfig;
        // 测试clientId 可以设置的时候去掉注释即可
        /**
         * 设备初始化建联
         * onError 初始化建联失败，如果因网络问题导致初始化失败，需要用户重试初始化
         * onInitDone 初始化成功
         */
        LinkKit.getInstance().init(mContext, params, iLinkKitConnectListener);
    }


    /**
     * 注册下行监听，包括长连接的状态和云端下行的数据
     * @param iConnectNotifyListener 回调
     */
    public static void ConnectionStatu(IConnectNotifyListener iConnectNotifyListener){
        // 注册下行监听，包括长连接的状态和云端下行的数据
        LinkKit.getInstance().registerOnPushListener(iConnectNotifyListener);
    }




    /**
     * 订阅
     * @param productKey  // 产品类型
     * @param deviceName //设备名称
     * @param deviceSecret //设备密钥
     * @param iConnectSubscribeListener 回调
     */
    public static void InitSubscription(String productKey, String deviceName, String deviceSecret,IConnectSubscribeListener iConnectSubscribeListener) {
        String testSubscribeTopicAndQos = "/" + productKey + "/" + deviceName + "/user/get:0";
        if (TextUtils.isEmpty(testSubscribeTopicAndQos)) {
            Log.d("InitManagerUtils","请根据自身产品设置需要测试订阅的topic");
            return;
        }
        if (!testSubscribeTopicAndQos.contains(":")) {
            Log.d("InitManagerUtils","格式不合法，必须包含qos（qos只能是0或1）");
            return;
        }
        String[] topicQosArray = testSubscribeTopicAndQos.split(":");
        String topic = topicQosArray[0];
        String qosString = topicQosArray[1];
        if (TextUtils.isEmpty(topic)) {
            Log.d("InitManagerUtils","格式不合法，topic不能为空");
            return;
        }
        if (TextUtils.isEmpty(qosString)) {
            Log.d("InitManagerUtils","格式不合法，qos不能为空");
            return;
        }

        int qos = 0;
        try {
            qos = Integer.parseInt(qosString);
            if (qos != 0 && qos != 1) {
                Log.d("InitManagerUtils","qos值非法，设置为0或1");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("InitManagerUtils","qos值非法，请设置为0或1");
            return;
        }

        MqttSubscribeRequest subscribeRequest = new MqttSubscribeRequest();
        subscribeRequest.isSubscribe = true;
        subscribeRequest.topic = topic;
        subscribeRequest.qos = qos;
        LinkKit.getInstance().subscribe(subscribeRequest,iConnectSubscribeListener);
    }

    /**
     * 传递信息到mqtt服务上
     * @param mqttPublishRequest 你自己new一个实体参数自己相传什么传什么
     * @param iConnectSendListener 回调
     */
    public static void onFunc2Click(MqttPublishRequest mqttPublishRequest,IConnectSendListener iConnectSendListener) {
        if(null != mqttPublishRequest){
            LinkKit.getInstance().publish(mqttPublishRequest, iConnectSendListener);
        }else {
            Log.d("InitManagerUtils","topic不能为空");
        }
    }

    /**
     *  传递信息到mqtt服务上
     * @param topic Mqtt 返回的topic
     * @param payloadObj json数据或者别的
     * @param iConnectSendListener 回调
     */
    public static void onEquipmentMessage(String topic,Object payloadObj,IConnectSendListener iConnectSendListener) {
        // 发布
        MqttPublishRequest request = new MqttPublishRequest();
        request.isRPC = false;
        request.topic = topic;
        request.qos = 0;
        request.payloadObj = payloadObj;
        LinkKit.getInstance().publish(request, iConnectSendListener);
    }
}
