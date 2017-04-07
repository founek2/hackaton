import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.SoftPwm;

/**
 * Created by lucky on 4/7/17.
 */
public class Light {
    private int id;
    private GpioPinDigitalOutput led;
    private GpioPinDigitalInput button;
    private Pin pinLed;
    private Pin pinButton;

    public Light(int id, Pin led, Pin button){
        this.id=id;
        this.led = GpioFactory.getInstance().provisionDigitalOutputPin(led);
        this.button = GpioFactory.getInstance().provisionDigitalInputPin(button, PinPullResistance.PULL_DOWN);
        SoftPwm.softPwmCreate(led.getAddress(),0,100);
        SoftPwm.softPwmWrite(led.getAddress(),5);
    }

}