package com.gree.airconditioner.binding;

import com.gree.airconditioner.DeviceInfo;
import com.gree.airconditioner.GreeAirconditionerDevice;

import java.util.Date;
import java.util.GregorianCalendar;

public class GreeDeviceBinding {
    private final GreeAirconditionerDevice device;
    private final String aesKey;
    private Date creationDate;

    public GreeDeviceBinding(GreeAirconditionerDevice device, String aesKey) {
        this.device = device;
        this.aesKey = aesKey;
        this.creationDate = GregorianCalendar.getInstance().getTime();
    }

    public GreeAirconditionerDevice getDevice() {
        return device;
    }

    public String getMacAddress() {
        return device.getDeviceInfo().getMacAddress();
    }

    public String getAesKey() {
        return aesKey;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
