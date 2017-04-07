import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.io.IOException;


/**
 * Created by lucky on 4/7/17.
 */
public class GpioPinListenerDigitalMy implements GpioPinListenerDigital {
    Light light;
    Thread thread;
    boolean clicked = false;

    public GpioPinListenerDigitalMy(Light light) {
        this.light = light;
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        if (event.getState().isHigh()) {
            light.setLighted(true);

            try {
                Process p = Runtime.getRuntime().exec("curl --data '{\"on\":"+light.getId()+"}' localhost:3000 --header \"Content-Type: application/json\"");
            } catch (IOException e) {
                e.printStackTrace();
            }
            clicked = true;
        } else {
            clicked = false;
            thread = new Thread() {
                public void run() {
                    try {
                        this.sleep(500);
                        if (!clicked) {
                            try {
                            Process p = Runtime.getRuntime().exec("curl --data '{\"off\":"+light.getId()+"}' localhost:3000 --header \"Content-Type: application/json\"");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                            light.setLighted(false);
                        }
                    } catch (InterruptedException  e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();

        }
        System.out.println("changed" + light.getId());
    }

}
