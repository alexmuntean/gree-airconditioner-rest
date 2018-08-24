package com.gree.airconditioner.controllers;

import com.gree.airconditioner.GreeAirconditionerDevice;
import com.gree.airconditioner.dto.status.GreeDeviceStatus;
import com.gree.airconditioner.services.GreeAirconditionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GreeAirconditionerController {

    @Autowired
    private GreeAirconditionerService airconditionerService;

    @GetMapping("/powerOn")
    public String powerOn() {
        List<GreeAirconditionerDevice> devices = this.airconditionerService.getDevices();
        this.airconditionerService.turnOn(devices.get(0));
        return "done";
    }

    @GetMapping("/temperature")
    public String temperature(@RequestParam() Integer temperature) {
        List<GreeAirconditionerDevice> devices = this.airconditionerService.getDevices();
        this.airconditionerService.setTemperature(devices.get(0), temperature);
        return "done";
    }

    @GetMapping("/status")
    public GreeDeviceStatus getStatus() {
        List<GreeAirconditionerDevice> devices = this.airconditionerService.getDevices();
        return this.airconditionerService.getStatus(devices.get(0));
    }

    @GetMapping("/powerOff")
    public String powerOff() {
        List<GreeAirconditionerDevice> devices = this.airconditionerService.getDevices();
        this.airconditionerService.turnOff(devices.get(0));
        return "done";
    }
}
