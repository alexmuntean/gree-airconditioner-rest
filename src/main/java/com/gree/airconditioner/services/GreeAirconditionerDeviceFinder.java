package com.gree.airconditioner.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gree.airconditioner.ConnectionInfo;
import com.gree.airconditioner.DeviceInfo;
import com.gree.airconditioner.GreeAirconditionerDevice;
import com.gree.airconditioner.dto.Command;
import com.gree.airconditioner.dto.CommandResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static com.gree.airconditioner.util.GreeAirconditionerHelper.getDatagramSocket;

public class GreeAirconditionerDeviceFinder {
    private final static Logger log = LogManager.getLogger(GreeAirconditionerDeviceFinder.class);

    private GreeAirconditionerDeviceFinder() {
    }

    public static List<GreeAirconditionerDevice> findDevices() {
        Enumeration<NetworkInterface> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback()) {
                    continue;    // Do not want to use the loopback interface.
                }
                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    if (broadcast == null) {
                        continue;

                    }
                    if (log.isDebugEnabled()) {
                        log.debug("Searching for devices on broadcast address {}", broadcast);
                    }

                    return findDevices(broadcast);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<GreeAirconditionerDevice> findDevices(String broadcastAddress) {
        try {
            return findDevices(InetAddress.getByName(broadcastAddress));
        } catch (UnknownHostException e) {
            log.error("Couldn't connect to {}", broadcastAddress, e);
        }
        return null;
    }

    public static List<GreeAirconditionerDevice> findDevices(InetAddress broadcastAddress) {
        if (log.isInfoEnabled()) {
            log.info("Looking for air conditioner devices on {}. This might take a while...", broadcastAddress.getHostAddress());
        }

        Command command = Command.builder().buildScanCommand();
        byte[] scanCommand = command.toJson().getBytes();

        DatagramSocket datagramSocket = getDatagramSocket();
        DatagramPacket sendPacket = new DatagramPacket(scanCommand, scanCommand.length, broadcastAddress, 7000);
        try {
            datagramSocket.send(sendPacket);
        } catch (IOException e) {
            log.error("Can't send packet", e);
        }

        List<GreeAirconditionerDevice> devices = new ArrayList<>();

        byte[] receiveData = new byte[1024];
        boolean timeoutRecieved = false;
        while (!timeoutRecieved) {
            // Receive a response
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                datagramSocket.receive(receivePacket);
                InetAddress remoteAddress = receivePacket.getAddress();
                Integer remotePort = receivePacket.getPort();

                // Read the response
                CommandResponse commandResponse = getResponseCommand(receivePacket);

                // If there was no pack, ignore the response
                if (commandResponse.getPack() == null) {
                    continue;
                }

                if (log.isDebugEnabled()) {
                    log.debug("Found device {}", commandResponse);
                }

                DeviceInfo deviceInfo = DeviceInfo.build(commandResponse);
                ConnectionInfo connectionInfo = ConnectionInfo.build(remoteAddress, remotePort);
                GreeAirconditionerDevice device = new GreeAirconditionerDevice(deviceInfo, connectionInfo);
                devices.add(device);

            } catch (IOException e) {
                timeoutRecieved = true;
            }
        }
        if (log.isInfoEnabled()) {
            log.info("Found {} devices", devices.size());
        }
        return devices;
    }

    private static CommandResponse getResponseCommand(DatagramPacket receivePacket) throws IOException {
        String modifiedSentence = new String(receivePacket.getData());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(modifiedSentence, CommandResponse.class);
    }
}
