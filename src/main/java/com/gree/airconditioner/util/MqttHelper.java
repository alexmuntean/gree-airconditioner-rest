package com.gree.airconditioner.util;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public final class MqttHelper {
    private MqttHelper() {
    }

    public static MqttMessage getMqttMessage(String value) {
        MqttMessage msg = new MqttMessage(value.getBytes());
        msg.setQos(1);
        msg.setRetained(true);
        return msg;
    }
}
