package com.gree.airconditioner.binding.exception;

import com.gree.airconditioner.GreeAirconditionerDevice;

public class BindingUnsuccessfulException extends RuntimeException {
    private final GreeAirconditionerDevice device;

    public BindingUnsuccessfulException(GreeAirconditionerDevice device) {
        this.device = device;
    }
}
