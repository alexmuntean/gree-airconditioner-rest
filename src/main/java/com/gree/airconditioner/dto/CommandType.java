package com.gree.airconditioner.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CommandType {
    SCAN("scan"),
    STATUS("status"),
    BIND("bind"),
    BINDOK("bindok"),
    PACK("pack");

    private String code;

    CommandType(String code) {
        this.code = code;
    }

    @JsonCreator
    public CommandType fromCode(String code) {
        for (CommandType type : values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
