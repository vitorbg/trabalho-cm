package compmovel.trabalhoandroidsql.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by vitor on 17/12/15.
 */
public class StatusRest {

    private static final int HTTP_COD_SUCESSO = 200;



    public boolean verificaStatusServidor() throws IOException {
        String result;

        URL url = new URL("http://192.168.0.13:8080/wstrabalhoandroidsql/webresources/status/retorna     ");
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        conn.setRequestMethod("GET");

        /*    if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }*/

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String output;
        output = br.readLine();
        br.close();
        System.out.println("StatusRest output = "+output);

        if(output.contains("s")){
            return true;
        }
        return false;

    }
}
