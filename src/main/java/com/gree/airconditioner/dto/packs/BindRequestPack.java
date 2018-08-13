package com.gree.airconditioner.dto.packs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gree.airconditioner.DeviceInfo;
import com.gree.airconditioner.util.CryptoUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class BindRequestPack {
    private static final Logger log = LogManager.getLogger(BindRequestPack.class);

    @JsonProperty("mac")
    private String mac;

    @JsonProperty("t")
    private String t;

    @JsonProperty("uid")
    private Integer uid;

    public BindRequestPack(String mac) {
        this.mac = mac;
        this.t = "bind";
        this.uid = 0;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public static String build(DeviceInfo info) {
        String packEncrypted = null;
        BindRequestPack pack = new BindRequestPack(info.getMacAddress());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String packJson = mapper.writeValueAsString(pack);
            packEncrypted = CryptoUtil.encryptPack(packJson);
        } catch (IOException e) {
            log.error("Can't make the bind pack", e);
        }
        return packEncrypted;
    }
}
