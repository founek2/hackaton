import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by lucky on 4/8/17.
 */
public class GpionPinListenerDIgitalBreak  implements GpioPinListenerDigital {
    Light light;

    public GpionPinListenerDIgitalBreak(Light light) {
        this.light = light;
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        System.out.println("changed");
        if (event.getState().isHigh()) {
            System.out.println("send true");
            light.setBreak(true);
            sendPost(LightLevels.URL+"/break",true,light.getId());
        }else {
            System.out.println("send false");
            light.setBreak(false);
            sendPost(LightLevels.URL+"/break",false,light.getId());
        }
    }

    private final String USER_AGENT = "Mozilla/5.0";

    private void sendPost(String url, boolean what, int id) {

        try {
            //String url = "http://192.168.1.158:3001";
            URL obj = new URL(url);
            sun.net.www.protocol.http.HttpURLConnection con = (sun.net.www.protocol.http.HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "break=" + what+"&number="+id;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();


            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
