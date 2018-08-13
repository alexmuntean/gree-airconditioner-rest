package com.gree.airconditioner.controllers;

import com.gree.airconditioner.GreeAirconditionerDevice;
import com.gree.airconditioner.services.GreeAirconditionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GreeAirconditionerController {

    @Autowired
    private GreeAirconditionerService airconditionerService;

    @RequestMapping("/powerOn")
    public String powerOn() {
        List<GreeAirconditionerDevice> devices = this.airconditionerService.getDevices();
        this.airconditionerService.turnOn(devices.get(0));
        return "done";
    }

    @RequestMapping("/temperature")
    public String temperature(@RequestParam() Integer temperature) {
        List<GreeAirconditionerDevice> devices = this.airconditionerService.getDevices();
        this.airconditionerService.setTemperature(devices.get(0), temperature);
        return "done";
    }

    @RequestMapping("/powerOff")
    public String powerOff() {
        List<GreeAirconditionerDevice> devices = this.airconditionerService.getDevices();
        this.airconditionerService.turnOff(devices.get(0));
        return "done";
    }
}
