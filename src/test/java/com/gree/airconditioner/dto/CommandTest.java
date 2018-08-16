package com.gree.airconditioner.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandTest {

    @Test
    public void toJson() throws Exception {
        Command command = Command.builder().buildScanCommand();
        assertEquals("{\"t\":\"scan\"}", command.toJson());
    }
}