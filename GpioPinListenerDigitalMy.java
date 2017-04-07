import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
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
            clicked = true;


            try {
                sendPost();
            } catch (Exception e) {
                e.printStackTrace();
            }


          /*  try {
                System.out.println("send");
                System.out.println("curl --data \"on:"+light.getId()+"\" 192.168.1.158:3001 --header \"Content-Type: text/html\"");
                Process p = Runtime.getRuntime().exec("curl --data \"on:"+light.getId()+"\" 192.168.1.158:3001 --header \"Content-Type: text/html\"");
                System.out.println("response: "+p.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }*/


        } else {
            clicked = false;
            thread = new Thread() {
                public void run() {
                    try {
                        this.sleep(500);
                        if (!clicked) {
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
    private void sendPost(String url,String what,int id) throws Exception {

        //String url = "http://192.168.1.158:3001";
        URL obj = new URL(url);
        sun.net.www.protocol.http.HttpURLConnection con = (sun.net.www.protocol.http.HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = what+"="+id;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        in.close();

    }

}
