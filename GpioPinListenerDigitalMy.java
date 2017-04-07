import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.SoftPwm;

/**
 * Created by lucky on 4/7/17.
 */
public class GpioPinListenerDigitalMy implements GpioPinListenerDigital {
    GpioPinDigitalOutput led0;
    GpioPinDigitalOutput led1;
    GpioPinDigitalOutput led2;
    int position;
    public GpioPinListenerDigitalMy(GpioPinDigitalOutput led0,GpioPinDigitalOutput led1,GpioPinDigitalOutput led2, int position){
        this.led0=led0;
        this.led1=led1;
        this.led2=led2;
        this.position = position;
    }
    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        if (event.getState().isHigh()) {
            SoftPwm.softPwmCreate(led0.getPin().getAddress(),0,100);
            SoftPwm.softPwmWrite(led0.getPin().getAddress(),5);

            SoftPwm.softPwmCreate(led1.getPin().getAddress(),0,100);
            SoftPwm.softPwmWrite(led1.getPin().getAddress(),30);

            SoftPwm.softPwmCreate(led2.getPin().getAddress(),50,100);
            SoftPwm.softPwmWrite(led2.getPin().getAddress(),100);

            //led.high();
        } else {
            //led.low();
            SoftPwm.softPwmStop(led0.getPin().getAddress());
            SoftPwm.softPwmStop(led1.getPin().getAddress());
            SoftPwm.softPwmStop(led2.getPin().getAddress());
        }
        System.out.println("button changed" + position);
    }
}
