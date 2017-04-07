import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

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
            clicked = true;
        } else {
            System.out.println("start timer " + light.getId());
            clicked = false;
            thread = new Thread() {
                public void run() {
                    try {
                        this.sleep(500);
                        if (!clicked) {
                            System.out.println("trigered " + light.getId());
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
