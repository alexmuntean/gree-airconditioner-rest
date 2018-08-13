package com.gree.airconditioner.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.DatagramSocket;
import java.net.SocketException;

public class GreeAirconditionerHelper {
    private final static Logger log = LogManager.getLogger(GreeAirconditionerHelper.class);

    private static DatagramSocket clientSocket = null;

    private GreeAirconditionerHelper() {
    }

    public static DatagramSocket getDatagramSocket() {
        if (clientSocket == null) {
            try {
                clientSocket = new DatagramSocket();
                clientSocket.setSoTimeout(60000);
            } catch (SocketException e) {
                log.error("Can't create a datagram socket", e);
                throw new RuntimeException("Can't create a datagram socket");
            }
        }
        return clientSocket;
    }
}
