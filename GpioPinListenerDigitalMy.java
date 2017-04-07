import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lucky on 4/7/17.
 */
public class GpioPinListenerDigitalMy implements GpioPinListenerDigital {
    Light light;

    Timer timer = null;

    public GpioPinListenerDigitalMy(Light light) {
        this.light = light;
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        if (event.getState().isHigh()) {
            if (timer == null) {
                light.setLighted(true);
            }else {
                timer.cancel();
                timer=null;
            }

        } else {
            timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    light.setLighted(false);
                    timer = null;
                }
            }, 0, 500);
        }
    }
}
