package com.gree.airconditioner.dto.packs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BindResponsePack {
    @JsonProperty("t")
    private String t;
    @JsonProperty("mac")
    private String mac;
    @JsonProperty("key")
    private String key;
    @JsonProperty("r")
    private String r;

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "BindResponsePack{" +
                "t='" + t + '\'' +
                ", mac='" + mac + '\'' +
                ", key='" + key + '\'' +
                ", r='" + r + '\'' +
                '}';
    }
}
