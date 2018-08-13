package com.gree.airconditioner.dto.status;

public enum TemperatureUnit {
    CELSIUS(0),
    FAHRENHEIT(1);

    private int status;

    TemperatureUnit(int status) {
        this.status = status;
    }


    public int getStatus() {
        return status;
    }

    public static TemperatureUnit fromCode(int rawStatus) {
        for (TemperatureUnit value : values()) {
            if (value.getStatus() == rawStatus) {
                return value;
            }
        }
        return null;
    }
}
