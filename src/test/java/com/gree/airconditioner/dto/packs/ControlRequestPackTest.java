package com.gree.airconditioner.dto.packs;

import com.gree.airconditioner.dto.status.GreeDeviceStatus;
import com.gree.airconditioner.dto.status.Switch;
import org.junit.Test;

public class ControlRequestPackTest {

    @Test
    public void testPowerCommand() throws Exception {
        GreeDeviceStatus status = new GreeDeviceStatus();
        status.setPower(Switch.ON);

        ControlRequestPack pack = new ControlRequestPack(status);
        System.out.println(pack.toJson());
    }
}
