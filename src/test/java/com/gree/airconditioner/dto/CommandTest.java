package com.gree.airconditioner.dto;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandTest {

    @Test
    public void toJson() throws Exception {
        Command command = Command.builder().buildScanCommand();
        Assert.assertEquals("{\"t\":\"scan\"}", command.toJson());
    }
}