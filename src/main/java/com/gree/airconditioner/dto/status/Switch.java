package com.gree.airconditioner.dto.status;

public enum Switch {
    OFF(0),
    ON(1);

    private int status;

    Switch(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static Switch fromCode(int rawStatus) {
        for (Switch value : values()) {
            if (value.getStatus() == rawStatus) {
                return value;
            }
        }
        return null;
    }
}
