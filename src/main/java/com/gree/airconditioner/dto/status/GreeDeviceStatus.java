package com.gree.airconditioner.dto.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class GreeDeviceStatus {
    @JsonProperty("Pow")
    private Switch power;

    @JsonProperty("Mod")
    private OperationMode operationMode;

    @JsonUnwrapped
    private Temperature temperature;

    @JsonProperty("WdSpd")
    private FanMode fanMode;

    @JsonProperty("Air")
    private Switch air;

    @JsonProperty("Blo")
    private Switch blow;

    @JsonProperty("Health")
    private Switch health;

    @JsonProperty("SwhSlp")
    private Switch sleepMode;

    @JsonProperty("Lig")
    private Switch lightIndicator;

    @JsonProperty("SwUpDn")
    private SwingDirection swingDirection;

    @JsonProperty("Quiet")
    private Switch quiet;

    @JsonProperty("Tur")
    private Switch maximumIntensity;

    @JsonProperty("SwSt")
    private Switch energySavingMode;

    public Switch getPower() {
        return power;
    }

    public void setPower(Switch power) {
        this.power = power;
    }

    public OperationMode getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public FanMode getFanMode() {
        return fanMode;
    }

    public void setFanMode(FanMode fanMode) {
        this.fanMode = fanMode;
    }

    public Switch getAir() {
        return air;
    }

    public void setAir(Switch air) {
        this.air = air;
    }

    public Switch getBlow() {
        return blow;
    }

    public void setBlow(Switch blow) {
        this.blow = blow;
    }

    public Switch getHealth() {
        return health;
    }

    public void setHealth(Switch health) {
        this.health = health;
    }

    public Switch getSleepMode() {
        return sleepMode;
    }

    public void setSleepMode(Switch sleepMode) {
        this.sleepMode = sleepMode;
    }

    public Switch getLightIndicator() {
        return lightIndicator;
    }

    public void setLightIndicator(Switch lightIndicator) {
        this.lightIndicator = lightIndicator;
    }

    public SwingDirection getSwingDirection() {
        return swingDirection;
    }

    public void setSwingDirection(SwingDirection swingDirection) {
        this.swingDirection = swingDirection;
    }

    public Switch getQuiet() {
        return quiet;
    }

    public void setQuiet(Switch quiet) {
        this.quiet = quiet;
    }

    public Switch getMaximumIntensity() {
        return maximumIntensity;
    }

    public void setMaximumIntensity(Switch maximumIntensity) {
        this.maximumIntensity = maximumIntensity;
    }

    public Switch getEnergySavingMode() {
        return energySavingMode;
    }

    public void setEnergySavingMode(Switch energySavingMode) {
        this.energySavingMode = energySavingMode;
    }
}
