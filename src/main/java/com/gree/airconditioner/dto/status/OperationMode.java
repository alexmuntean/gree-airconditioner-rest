package com.gree.airconditioner.dto.status;

public enum OperationMode {
    AUTO(0),
    COOL(1),
    DRY(2),
    FAN(3),
    HEAT(4);

    private int status;

    OperationMode(int status) {
        this.status = status;
    }


    public int getStatus() {
        return status;
    }

    public static OperationMode fromCode(int rawStatus) {
        for (OperationMode value : values()) {
            if (value.getStatus() == rawStatus) {
                return value;
            }
        }
        return null;
    }
}
