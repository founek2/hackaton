/**
 * Created by lucky on 4/7/17.
 */

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.trigger.GpioBlinkStateTrigger;
import com.pi4j.io.gpio.trigger.GpioBlinkStopStateTrigger;
import com.pi4j.wiringpi.SoftPwm;
import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("<--Pi4J--> GPIO Blink Example ... started.");

        Map<Integer, Light> lights = new HashMap<Integer, Light>();
        lights.put(0,new Light(0,RaspiPin.GPIO_08 , RaspiPin.GPIO_15));
        lights.put(1,new Light(1,RaspiPin.GPIO_09 , RaspiPin.GPIO_16));
        lights.put(2,new Light(2,RaspiPin.GPIO_27 , RaspiPin.GPIO_01));
        lights.put(3,new Light(3,RaspiPin.GPIO_00 , RaspiPin.GPIO_04));
        lights.put(4,new Light(4,RaspiPin.GPIO_02 , RaspiPin.GPIO_05));
        lights.put(5,new Light(5,RaspiPin.GPIO_03 , RaspiPin.GPIO_06));
        lights.put(6,new Light(6,RaspiPin.GPIO_12 , RaspiPin.GPIO_10));
        lights.put(7,new Light(7,RaspiPin.GPIO_13 , RaspiPin.GPIO_11));
        lights.put(8,new Light(8,RaspiPin.GPIO_14 , RaspiPin.GPIO_26));
        //test
        lights.get(0).addSideLight(new Light[]{lights.get(1)});

        lights.get(1).addSideLight(new Light[]{lights.get(0)});
        lights.get(1).addSideLight(new Light[]{lights.get(2)});

        lights.get(2).addSideLight(new Light[]{lights.get(3)});
        lights.get(2).addSideLight(new Light[]{lights.get(1)});

        lights.get(3).addSideLight(new Light[]{lights.get(4)});
        lights.get(3).addSideLight(new Light[]{lights.get(2)});


        lights.get(4).addSideLight(new Light[]{lights.get(5)});
        lights.get(4).addSideLight(new Light[]{lights.get(3)});

        lights.get(5).addSideLight(new Light[]{lights.get(6)});
        lights.get(5).addSideLight(new Light[]{lights.get(4)});

        lights.get(6).addSideLight(new Light[]{lights.get(7)});
        lights.get(6).addSideLight(new Light[]{lights.get(5)});

        lights.get(7).addSideLight(new Light[]{lights.get(8)});
        lights.get(7).addSideLight(new Light[]{lights.get(6)});

        lights.get(8).addSideLight(new Light[]{lights.get(7)});


        lights.get(7).addBreak(RaspiPin.GPIO_21);
        lights.get(8).addBreak(RaspiPin.GPIO_22);

        while (true) {
            Thread.sleep(500);
        }
    }
}
