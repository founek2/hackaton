import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.SoftPwm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by lucky on 4/7/17.
 */
public class Light {
    private int id;
    private GpioPinDigitalOutput led;
    private GpioPinDigitalInput button;
    private boolean lighted = false;
    private LinkedList<Light[]> sideLights = new LinkedList<Light[]>();
    private Map<Light, Integer> parrentLights = new HashMap<Light, Integer>();//mostFaraway to Least Faraway

    public Light(int id, Pin led, Pin button) {
        this.id = id;
        this.led = GpioFactory.getInstance().provisionDigitalOutputPin(led);
        this.button = GpioFactory.getInstance().provisionDigitalInputPin(button, PinPullResistance.PULL_DOWN);
        SoftPwm.softPwmCreate(this.led.getPin().getAddress(), 0, 100);
        SoftPwm.softPwmWrite(this.led.getPin().getAddress(), LightLevels.LIGHT_LEVELS[0]);
        this.button.addListener(new GpioPinListenerDigitalMy(this));
    }

    public void addSideLight(Light[] sideLight) {
        if (sideLight.length != LightLevels.LIGHT_LEVELS.length - 2) {
            throw new IllegalArgumentException("There mus't be " + (LightLevels.LIGHT_LEVELS.length - 2) + " side lights");
        }
        this.sideLights.add(sideLight);
    }

    public boolean isLighted() {
        return lighted;
    }

    public void addSubLevelLight(Light parrent, int level) {
        parrentLights.put(parrent,level);
        SoftPwm.softPwmWrite(led.getPin().getAddress(), maxLight());
    }

    public void removeSubLevelLight(Light parrent){
        parrentLights.remove(parrent);
        SoftPwm.softPwmWrite(led.getPin().getAddress(), maxLight());
    }


    public void setLighted(boolean lighted) {
        this.lighted = lighted;
        if (lighted) {
            SoftPwm.softPwmWrite(led.getPin().getAddress(), LightLevels.LIGHT_LEVELS[LightLevels.LIGHT_LEVELS.length - 1]);
            for (Light[] lights : sideLights) {
                for (int i = 0; i < lights.length; i++) {
                    lights[i].addSubLevelLight(this,LightLevels.LIGHT_LEVELS[i+1]);
                }
            }
        } else {
            SoftPwm.softPwmWrite(led.getPin().getAddress(), maxLight());
            for (Light[] lights : sideLights) {
                for (int i = 0; i < lights.length; i++) {
                    lights[i].removeSubLevelLight(this);
                }
            }
        }
    }

    public int maxLight() {
        Map.Entry<Light, Integer> maxEntry = null;

        for (Map.Entry<Light, Integer> entry : parrentLights.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        if (maxEntry == null) {
            return LightLevels.LIGHT_LEVELS[0];
        } else {
            return maxEntry.getValue();
        }
    }

}
