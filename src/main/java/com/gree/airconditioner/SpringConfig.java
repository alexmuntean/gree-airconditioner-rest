package com.gree.airconditioner;

import com.gree.airconditioner.binding.GreeDeviceBinderService;
import com.gree.airconditioner.communication.GreeCommunicationService;
import com.gree.airconditioner.dto.status.GreeDeviceStatus;
import com.gree.airconditioner.dto.status.OperationMode;
import com.gree.airconditioner.dto.status.Switch;
import com.gree.airconditioner.dto.status.Temperature;
import com.gree.airconditioner.services.GreeAirconditionerService;
import com.gree.airconditioner.util.MqttHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

@Configuration
public class SpringConfig {
    private static final Logger log = LogManager.getLogger(SpringConfig.class);

    @Bean
    public GreeCommunicationService communicationService() {
        return new GreeCommunicationService();
    }

    @Bean
    public GreeDeviceBinderService binderService() {
        return new GreeDeviceBinderService(communicationService());
    }

    @Bean
    public GreeAirconditionerService airconditionerService() {
        GreeAirconditionerService greeAirconditionerService = new GreeAirconditionerService(binderService(), communicationService());
        greeAirconditionerService.discoverDevices();
        return greeAirconditionerService;
    }

    @Bean
    public IMqttClient mqtt() {
        String publisherId = UUID.randomUUID().toString();
        IMqttClient mqttClient = null;
        try {
            mqttClient = new MqttClient("tcp://192.168.1.150:1883", publisherId, new MemoryPersistence());
        } catch (MqttException e) {
            return null;
        }

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(30);
        try {
            mqttClient.connect(options);
        } catch (MqttException e) {
            return null;
        }

        return mqttClient;
    }

    @Scheduled(fixedDelay = 10000)
    public void updateStatus() {
        GreeAirconditionerService service = airconditionerService();
        GreeDeviceStatus status = service.getDevice().getStatus();

        OperationMode operationMode = status.getOperationMode();
        Switch power = status.getPower();
        Temperature temperature = status.getTemperature();

        IMqttClient mqtt = mqtt();
        try {
            mqtt.publish("smarthome-hub/devices/air-conditioner/gree/power", MqttHelper.getMqttMessage(power.toString()));
            mqtt.publish("smarthome-hub/devices/air-conditioner/gree/mode", MqttHelper.getMqttMessage(operationMode.toString()));
            mqtt.publish("smarthome-hub/devices/air-conditioner/gree/temperature", MqttHelper.getMqttMessage(Integer.valueOf(temperature.getTemperature()).toString()));
            mqtt.publish("smarthome-hub/devices/air-conditioner/gree/temperature-unit", MqttHelper.getMqttMessage(temperature.getUnit().toString()));
        } catch (MqttException e) {
            log.error(e);
        }
    }

}
