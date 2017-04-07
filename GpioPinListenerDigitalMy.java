import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.util.TimerTask;

/**
 * Created by lucky on 4/7/17.
 */
public class GpioPinListenerDigitalMy implements GpioPinListenerDigital {
    Light light;
    Thread thread;

    public GpioPinListenerDigitalMy(Light light) {
        this.light = light;
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        if (event.getState().isHigh()) {
            light.setLighted(true);
            thread.interrupt();
        } else {
            System.out.println("start timer " + light.getId());

            thread=new Thread() {
                public void run() {
                    try {
                        this.sleep(5000);
                        System.out.println("trigered " + light.getId());
                        light.setLighted(false);
                    } catch (InterruptedException | IllegalThreadStateException e) {
                        e.printStackTrace();
                    }
                    this.interrupt();
                }
            };
            thread.start();

        }
        System.out.println("changed" + light.getId());
    }
}
