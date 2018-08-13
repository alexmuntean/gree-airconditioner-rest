package com.gree.airconditioner;

import com.gree.airconditioner.binding.GreeDeviceBinderService;
import com.gree.airconditioner.communication.GreeCommunicationService;
import com.gree.airconditioner.services.GreeAirconditionerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

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

}
