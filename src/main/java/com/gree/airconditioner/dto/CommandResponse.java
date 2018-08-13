package com.gree.airconditioner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gree.airconditioner.dto.packs.ScanResponsePack;
import com.gree.airconditioner.util.CryptoUtil;

import java.io.IOException;

public class CommandResponse {
    @JsonProperty("t")
    private CommandType commandType;

    @JsonProperty("uid")
    private Long uid;

    @JsonProperty("cid")
    private String cid;

    @JsonProperty("i")
    private Integer i;

    @JsonProperty("pack")
    private String pack;

    @JsonProperty("tcid")
    private String tcid;

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getTcid() {
        return tcid;
    }

    public void setTcid(String tcid) {
        this.tcid = tcid;
    }

    @Override
    public String toString() {
        return "CommandResponse{" +
                "commandType=" + commandType +
                ", uid=" + uid +
                ", cid='" + cid + '\'' +
                ", i=" + i +
                ", pack='" + getPack() + '\'' +
                ", tcid='" + tcid + '\'' +
                '}';
    }

}
