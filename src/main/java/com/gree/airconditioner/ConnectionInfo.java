package com.gree.airconditioner;

import java.net.InetAddress;
import java.util.Objects;

public class ConnectionInfo {
    private InetAddress address;
    private Integer port;

    private ConnectionInfo(InetAddress address, Integer port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public Integer getPort() {
        return port;
    }

    public static ConnectionInfo build(InetAddress address, Integer port) {
        return new ConnectionInfo(address, port);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionInfo that = (ConnectionInfo) o;
        return Objects.equals(address.getHostAddress(), that.address.getHostAddress()) &&
                Objects.equals(port, that.port);
    }

    @Override
    public int hashCode() {

        return Objects.hash(address.getHostAddress(), port);
    }
}
