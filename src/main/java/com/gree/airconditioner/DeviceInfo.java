package com.gree.airconditioner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gree.airconditioner.dto.CommandResponse;
import com.gree.airconditioner.dto.packs.ScanResponsePack;
import com.gree.airconditioner.util.CryptoUtil;

import java.io.IOException;

public class DeviceInfo {
    private String name;
    private String version;
    private String macAddress;

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    private void setVersion(String version) {
        this.version = version;
    }

    public String getMacAddress() {
        return macAddress;
    }

    private void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    private static ScanResponsePack getScanResponsePack(String pack) {
        String jsonPack = CryptoUtil.decryptPack(pack);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonPack, ScanResponsePack.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DeviceInfo build(CommandResponse commandResponse) {
        DeviceInfo deviceInfo = new DeviceInfo();
        ScanResponsePack pack = getScanResponsePack(commandResponse.getPack());
        deviceInfo.setName(pack.getFriendlyName());
        deviceInfo.setVersion(pack.getVer());
        deviceInfo.setMacAddress(pack.getMac());
        return deviceInfo;
    }
}
