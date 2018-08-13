package com.gree.airconditioner.binding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gree.airconditioner.DeviceInfo;
import com.gree.airconditioner.GreeAirconditionerDevice;
import com.gree.airconditioner.binding.exception.BindingUnsuccessfulException;
import com.gree.airconditioner.communication.GreeCommunicationService;
import com.gree.airconditioner.dto.Command;
import com.gree.airconditioner.dto.CommandBuilder;
import com.gree.airconditioner.dto.CommandResponse;
import com.gree.airconditioner.dto.CommandType;
import com.gree.airconditioner.dto.packs.BindResponsePack;
import com.gree.airconditioner.util.CryptoUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GreeDeviceBinderService {
    private static final Logger log = LogManager.getLogger(GreeDeviceBinderService.class);
    private final GreeCommunicationService communicationService;

    private Map<GreeAirconditionerDevice, GreeDeviceBinding> bindings = new HashMap<>();

    public GreeDeviceBinderService(GreeCommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    public GreeDeviceBinding getBiding(GreeAirconditionerDevice device) {
        if (log.isDebugEnabled()) {
            log.debug("Attempting to bind with {}", device.getConnectionInfo().getAddress());
        }
        GreeDeviceBinding greeDeviceBinding = bindings.get(device);
        if (greeDeviceBinding != null) {
            long bindingCreationTime = greeDeviceBinding.getCreationDate().getTime();
            long nowTime = GregorianCalendar.getInstance().getTime().getTime();
            if (nowTime - bindingCreationTime < TimeUnit.MINUTES.toMillis(2)) {
                return greeDeviceBinding;
            }
        }
        DeviceInfo deviceInfo = device.getDeviceInfo();
        Command bindCommand = CommandBuilder.builder().buildBindCommand(deviceInfo);

        GreeDeviceBinding binding = sendBindCommand(device, bindCommand);
        bindings.put(device, binding);

        return binding;
    }


    private GreeDeviceBinding sendBindCommand(GreeAirconditionerDevice device, Command bindCommand) {
        GreeDeviceBinding binding = communicationService.sendCommand(device, bindCommand, (responseString) -> {
            BindResponsePack responsePack = getBindingResponse(responseString);
            if (responsePack.getT().equalsIgnoreCase(CommandType.BINDOK.getCode())) {
                if (log.isDebugEnabled()) {
                    String ipAddress = device.getConnectionInfo().getAddress().getHostAddress();
                    log.debug("Bind with device {} successful", ipAddress);
                }
                return new GreeDeviceBinding(device, responsePack.getKey());
            } else {
                throw new BindingUnsuccessfulException(device);
            }
        });

        return binding;
    }

    private BindResponsePack getBindingResponse(String bindingResponseString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            CommandResponse response = mapper.readValue(bindingResponseString, CommandResponse.class);
            String encryptedPack = response.getPack();
            String decryptedPack = CryptoUtil.decryptPack(encryptedPack);
            BindResponsePack responsePack = mapper.readValue(decryptedPack, BindResponsePack.class);
            return responsePack;
        } catch (IOException e) {
            log.error("Can't map binding response to command response", e);
        }
        return null;
    }
}
