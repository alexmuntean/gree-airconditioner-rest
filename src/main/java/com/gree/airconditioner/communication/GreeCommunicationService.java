package com.gree.airconditioner.communication;

import com.gree.airconditioner.ConnectionInfo;
import com.gree.airconditioner.GreeAirconditionerDevice;
import com.gree.airconditioner.binding.GreeDeviceBinderService;
import com.gree.airconditioner.dto.Command;
import com.gree.airconditioner.util.GreeAirconditionerHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.function.Function;

public class GreeCommunicationService {
    private static final Logger log = LogManager.getLogger(GreeDeviceBinderService.class);

    public <T> T sendCommand(GreeAirconditionerDevice device, Command command, Function<String, T> function) {
        String json = command.toJson();
        if (log.isDebugEnabled()) {
            log.debug("Sending command: {}", json);
        }

        ConnectionInfo connectionInfo = device.getConnectionInfo();
        InetAddress address = connectionInfo.getAddress();
        Integer port = connectionInfo.getPort();
        DatagramPacket datagram = new DatagramPacket(json.getBytes(), json.getBytes().length, address, port);
        DatagramSocket datagramSocket = GreeAirconditionerHelper.getDatagramSocket();

        try {
            datagramSocket.send(datagram);
            byte[] receiveData = new byte[500];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(receivePacket);
            String responseString = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            return function.apply(responseString);
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("Can't send command {}", command, e);
            }
            log.info("Trying to send it again {}", command, e);
            return sendCommand(device, command, function);
        }
    }
}
