import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.SoftPwm;

/**
 * Created by lucky on 4/7/17.
 */
public class GpioPinListenerDigitalMy implements GpioPinListenerDigital {
    GpioPinDigitalOutput led;
    int position;
    public GpioPinListenerDigitalMy(GpioPinDigitalOutput led, int position){
        this.led=led;
        this.position = position;
    }
    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        if (event.getState().isHigh()) {
            System.out.println(led.getPin().getAddress());
            SoftPwm.softPwmWrite(led.getPin().getAddress(),50);
            //led.high();
        } else {
            //led.low();
            SoftPwm.softPwmStop(led.getPin().getAddress());
        }
        System.out.println("button changed" + position);
    }
}
