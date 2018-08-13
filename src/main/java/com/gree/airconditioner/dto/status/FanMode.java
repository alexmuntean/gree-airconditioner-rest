package com.gree.airconditioner.dto.status;

public enum FanMode {
    AUTO(0),
    LOW(1),
    MEDIUM_LOW(2),
    MEDIUM(3),
    MEDIUM_HIGH(4),
    HIGH(5);

    private int status;

    FanMode(int status) {
        this.status = status;
    }


    public int getStatus() {
        return status;
    }

    public static FanMode fromCode(int rawStatus) {
        for (FanMode value : values()) {
            if (value.getStatus() == rawStatus) {
                return value;
            }
        }
        return null;
    }
}
