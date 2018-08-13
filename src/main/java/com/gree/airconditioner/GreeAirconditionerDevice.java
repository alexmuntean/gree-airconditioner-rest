package com.gree.airconditioner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class GreeAirconditionerDevice {
    private final static Logger log = LogManager.getLogger(GreeAirconditionerDevice.class);

    private DeviceInfo deviceInfo;
    private ConnectionInfo connectionInfo;

    public GreeAirconditionerDevice(DeviceInfo device, ConnectionInfo connection) {
        this.deviceInfo = device;
        this.connectionInfo = connection;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public ConnectionInfo getConnectionInfo() {
        return connectionInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GreeAirconditionerDevice that = (GreeAirconditionerDevice) o;
        return Objects.equals(connectionInfo, that.connectionInfo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(connectionInfo);
    }
}
