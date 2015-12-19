package compmovel.trabalhoandroidsql.thread;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.IOException;

import compmovel.trabalhoandroidsql.R;
import compmovel.trabalhoandroidsql.rest.StatusRest;

/**
 * Created by vitor on 19/12/15.
 */
public class ComunicacaoServidor extends IntentService {


    private StatusRest statusRest = new StatusRest();
    private boolean status;
    private TextView textViewStatus;
    private TextView textViewInternet;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ComunicacaoServidor(String name) {
        super(name);
    }


    public void run() {

        boolean status = false;
        if(isOnline()) {
            textViewInternet.setText("INTERNET ONLINE");




            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                status = statusRest.verificaStatusServidor();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("ServidorActivity STATUS = " + status);
            if (status) {
                textViewStatus.setText("ONLINE");
            } else {
                textViewStatus.setText("OFFLINE");
            }
        }
        else
        {
            textViewStatus.setText("OFFLINE");
            textViewInternet.setText("INTERNET OFFLINE");
        }


        while(true){

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                status = statusRest.verificaStatusServidor();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }




    public boolean isOnline(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());

    }

    @Override
    protected void onHandleIntent(Intent intent) {
     run();
    }
}
