import com.gree.airconditioner.GreeAirconditionerDevice;
import com.gree.airconditioner.binding.GreeDeviceBinderService;
import com.gree.airconditioner.binding.GreeDeviceBinding;
import com.gree.airconditioner.communication.GreeCommunicationService;
import com.gree.airconditioner.dto.Command;
import com.gree.airconditioner.dto.CommandBuilder;
import com.gree.airconditioner.dto.status.GreeDeviceStatus;
import com.gree.airconditioner.dto.status.Switch;
import com.gree.airconditioner.services.GreeAirconditionerDeviceFinder;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;

public class GreeAirconditionerDeviceTest {

    @Test
    public void test() throws Exception {
        List<GreeAirconditionerDevice> devices = GreeAirconditionerDeviceFinder.findDevices();
        if (devices.size() > 0) {
            GreeCommunicationService communicationService = new GreeCommunicationService();
            GreeDeviceBinderService service = new GreeDeviceBinderService(communicationService);
            GreeDeviceBinding binding = service.getBiding(devices.get(0));

            Thread.sleep(1000);

            GreeDeviceStatus status = new GreeDeviceStatus();
            status.setPower(Switch.ON);

            Command command = CommandBuilder.builder().buildControlCommand(status, binding);
            String result = communicationService.sendCommand(devices.get(0), command, Function.identity());
        }
    }
}
