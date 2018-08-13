package com.gree.airconditioner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Command {
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


    Command() {
    }

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

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static CommandBuilder builder() {
        return new CommandBuilder();
    }


    @Override
    public String toString() {
        return "Command{" +
                "commandType=" + commandType +
                ", uid=" + uid +
                ", cid='" + cid + '\'' +
                ", i=" + i +
                ", pack='" + pack + '\'' +
                ", tcid='" + tcid + '\'' +
                '}';
    }
}
