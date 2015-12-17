package compmovel.trabalhoandroidsql;

import android.content.Context;
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

import compmovel.trabalhoandroidsql.rest.StatusRest;

public class ServidorActivity extends AppCompatActivity {

    private Button btnStatus;
    private TextView textViewStatus;
    private TextView textViewInternet;
    private StatusRest statusRest = new StatusRest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servidor);

        btnStatus = (Button) this.findViewById(R.id.buttonStatusServidor);
        textViewStatus = (TextView) this.findViewById(R.id.textViewStatusServidor);
        textViewInternet = (TextView) this.findViewById(R.id.textViewInternet);

        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trataCliqueStatus(v);
            }

        });

    }

    private void trataCliqueStatus(View v){
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
    }

    public boolean isOnline(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());

    }
}
