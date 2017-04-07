/**
 * Created by lucky on 4/7/17.
 */

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("<--Pi4J--> GPIO Blink Example ... started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

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





        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addListener(new GpioPinListenerDigitalMy(leds[i],i));
        }

        int i = 2;
        while (true) {
            Thread.sleep(500);
            leds[i].high();
            Thread.sleep(500);
            leds[i].low();
            i++;
            if (i >= lights)
                i = 0;
        }
    }
}
