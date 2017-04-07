/**
 * Created by lucky on 4/7/17.
 */

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("<--Pi4J--> GPIO Blink Example ... started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #01 & #03 as an output pins and blink
        int lights = 9;
        final GpioPinDigitalOutput[] led = new GpioPinDigitalOutput[lights];
        led[0] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08);
        led[1] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09);
        //led[2]  = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07);
        led[3] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
        led[4] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02);
        led[5] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03);
        led[6] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12);
        led[7] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13);
        led[8] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14);

        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled

        // continuously blink the led every 1 second
        System.out.println(" ... the LED will continue blinking until the program is terminated.");
        System.out.println(" ... PRESS <CTRL-C> TO STOP THE PROGRAM.");

        // keep program running until user aborts (CTRL-C)
        while (true) {
            Thread.sleep(500);
            for (int i = 0; i < led.length; i++) {
                if (i != 2)
                    led[i].low();
            }
            Thread.sleep(500);
            for (int i = 0; i < led.length; i++) {
                if (i != 2)
                    led[i].high();
            }
        }

        // stop all GPIO activity/threads
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller
    }
}
