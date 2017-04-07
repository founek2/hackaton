import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

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
            led.high();
        } else {
            led.low();
        }
        System.out.println("button changed" + position);
    }
}
