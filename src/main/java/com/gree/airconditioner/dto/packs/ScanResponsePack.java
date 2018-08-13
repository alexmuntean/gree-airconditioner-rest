package com.gree.airconditioner.dto.packs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScanResponsePack {
    @JsonProperty("t")
    private String t;
    @JsonProperty("cid")
    private String cid;
    @JsonProperty("bc")
    private String brandCompany;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("catalog")
    private String catalog;
    @JsonProperty("mac")
    private String mac;
    @JsonProperty("mid")
    private String mid;
    @JsonProperty("model")
    private String model;
    @JsonProperty("name")
    private String friendlyName;
    @JsonProperty("series")
    private String series;
    @JsonProperty("vender")
    private String vender;
    @JsonProperty("ver")
    private String ver;
    @JsonProperty("lock")
    private Integer lock;

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBrandCompany() {
        return brandCompany;
    }

    public void setBrandCompany(String brandCompany) {
        this.brandCompany = brandCompany;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getVender() {
        return vender;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public Integer getLock() {
        return lock;
    }

    public void setLock(Integer lock) {
        this.lock = lock;
    }

    @Override
    public String toString() {
        return "ScanResponsePack{" +
                "t='" + t + '\'' +
                ", cid='" + cid + '\'' +
                ", brandCompany='" + brandCompany + '\'' +
                ", brand='" + brand + '\'' +
                ", catalog='" + catalog + '\'' +
                ", mac='" + mac + '\'' +
                ", mid='" + mid + '\'' +
                ", model='" + model + '\'' +
                ", friendlyName='" + friendlyName + '\'' +
                ", series='" + series + '\'' +
                ", vender='" + vender + '\'' +
                ", ver='" + ver + '\'' +
                ", lock=" + lock +
                '}';
    }
}