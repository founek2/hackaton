import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;


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
                sendPost();
            } catch (Exception e) {
                e.printStackTrace();
            }
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


    private final String USER_AGENT = "Mozilla/5.0";
    private void sendPost() throws Exception {

        String url = "http://192.168.1.158:3001";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}
