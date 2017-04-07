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
        lights.put(0,new Light(1,RaspiPin.GPIO_09 , RaspiPin.GPIO_16));
        lights.put(0,new Light(2,RaspiPin.GPIO_27 , RaspiPin.GPIO_01));
        lights.put(0,new Light(3,RaspiPin.GPIO_00 , RaspiPin.GPIO_04));
        lights.put(0,new Light(4,RaspiPin.GPIO_02 , RaspiPin.GPIO_05));
        lights.put(0,new Light(5,RaspiPin.GPIO_03 , RaspiPin.GPIO_06));
        lights.put(0,new Light(6,RaspiPin.GPIO_12 , RaspiPin.GPIO_10));
        lights.put(0,new Light(7,RaspiPin.GPIO_13 , RaspiPin.GPIO_11));
        lights.put(8,new Light(8,RaspiPin.GPIO_14 , RaspiPin.GPIO_26));



        // create gpio controller
      /*  final GpioController gpio = GpioFactory.getInstance();


        // provision gpio pin #01 & #03 as an output pins and blink
        int lights = 9;
        final GpioPinDigitalOutput[] leds = new GpioPinDigitalOutput[lights];
        leds[0] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08);
        leds[1] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09);
        leds[2] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
        leds[3] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
        leds[4] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02);
        leds[5] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03);
        leds[6] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12);
        leds[7] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13);
        leds[8] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14);


        for (int i = 0; i < leds.length; i++) {
            SoftPwm.softPwmCreate(leds[i].getPin().getAddress(),0,100);
            SoftPwm.softPwmWrite(leds[i].getPin().getAddress(),5);
        }


        final GpioPinDigitalInput[] buttons = new GpioPinDigitalInput[lights];
        buttons[0] = gpio.provisionDigitalInputPin(RaspiPin.GPIO_15, PinPullResistance.PULL_DOWN);
        buttons[1] = gpio.provisionDigitalInputPin(RaspiPin.GPIO_16, PinPullResistance.PULL_DOWN);
        buttons[2] = gpio.provisionDigitalInputPin(RaspiPin.GPIO_01, PinPullResistance.PULL_DOWN);
        buttons[3] = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_DOWN);
        buttons[4] = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05, PinPullResistance.PULL_DOWN);
        buttons[5] = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06, PinPullResistance.PULL_DOWN);
        buttons[6] = gpio.provisionDigitalInputPin(RaspiPin.GPIO_10, PinPullResistance.PULL_DOWN);
        buttons[7] = gpio.provisionDigitalInputPin(RaspiPin.GPIO_11, PinPullResistance.PULL_DOWN);
        buttons[8] = gpio.provisionDigitalInputPin(RaspiPin.GPIO_26, PinPullResistance.PULL_DOWN);





        for (int i = 0+2; i < buttons.length; i++) {

            buttons[i].addListener(new GpioPinListenerDigitalMy(leds[i-2],leds[i-1],leds[i],i));
         /*  buttons[i].addTrigger(new GpioBlinkStateTrigger(PinState.HIGH, leds[i-2], 3));
            buttons[i].addTrigger(new GpioBlinkStateTrigger(PinState.HIGH, leds[i-1], 2));
            buttons[i].addTrigger(new GpioBlinkStateTrigger(PinState.HIGH, leds[i],1));
            buttons[i].addTrigger(new GpioBlinkStateTrigger(PinState.HIGH, leds[i+1], 2));
            buttons[i].addTrigger(new GpioBlinkStateTrigger(PinState.HIGH, leds[i+2], 3));

            // create a gpio control trigger on the input pin ; when the input goes LOW, turn off blinking
            buttons[i].addTrigger(new GpioBlinkStopStateTrigger(PinState.LOW, leds[i-2]));
            buttons[i].addTrigger(new GpioBlinkStopStateTrigger(PinState.LOW, leds[i-1]));
            buttons[i].addTrigger(new GpioBlinkStopStateTrigger(PinState.LOW, leds[i]));
            buttons[i].addTrigger(new GpioBlinkStopStateTrigger(PinState.LOW, leds[i+1]));
            buttons[i].addTrigger(new GpioBlinkStopStateTrigger(PinState.LOW, leds[i+2]));
        }
*/
        int i = 0;
        while (true) {
            Thread.sleep(500);
         /*   leds[i].blink(10);
            Thread.sleep(500);
            leds[i].setState(PinState.LOW);
            i++;
            if (i >= lights)
                i = 0;*/
        }
    }
}
