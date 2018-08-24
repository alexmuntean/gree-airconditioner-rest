package com.gree.airconditioner.dto.packs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gree.airconditioner.DeviceInfo;
import com.gree.airconditioner.binding.GreeDeviceBinding;
import com.gree.airconditioner.util.CryptoUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class StatusRequestPack {
    private static final Logger log = LogManager.getLogger(BindRequestPack.class);

    @JsonProperty("mac")
    private String mac;

    @JsonProperty("t")
    private String t;

    @JsonProperty("cols")
    private String[] cols;

    public StatusRequestPack(String mac) {
        this.mac = mac;
        this.t = "status";
        this.cols = new String[]{
                "Pow",
                "Mod",
                "SetTem",
                "WdSpd",
                "Air",
                "Blo",
                "Health",
                "SwhSlp",
                "Lig",
                "SwingLfRig",
                "SwUpDn",
                "Quiet",
                "Tur",
                "StHt",
                "TemUn",
                "HeatCoolType",
                "TemRec",
                "SvSt"
        };
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

    public String[] getCols() {
        return cols;
    }

    public void setCols(String[] cols) {
        this.cols = cols;
    }

    public static String build(DeviceInfo info, GreeDeviceBinding binding) {
        String packEncrypted = null;
        StatusRequestPack pack = new StatusRequestPack(info.getMacAddress());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String packJson = mapper.writeValueAsString(pack);
            log.info("pack command for status: {}", packJson);
            packEncrypted = CryptoUtil.encryptPack(binding.getAesKey().getBytes(), packJson);
        } catch (IOException e) {
            log.error("Can't make the bind pack", e);
        }
        return packEncrypted;
    }
}
