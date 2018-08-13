package com.gree.airconditioner.dto.status;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {
    @JsonProperty("SetTem")
    private int temperature;
    @JsonProperty("TemUn")
    private TemperatureUnit unit;

    public Temperature(int temperature, TemperatureUnit unit) {
        this.temperature = temperature;
        this.unit = unit;
    }

    public int getTemperature() {
        return temperature;
    }

    public TemperatureUnit getUnit() {
        return unit;
    }
}
