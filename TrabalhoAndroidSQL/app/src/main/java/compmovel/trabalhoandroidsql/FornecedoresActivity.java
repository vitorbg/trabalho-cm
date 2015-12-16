package compmovel.trabalhoandroidsql;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

import compmovel.trabalhoandroidsql.adapterlistview.AdapterListViewFornecedores;
import compmovel.trabalhoandroidsql.persistencia.Fornecedor;
import compmovel.trabalhoandroidsql.persistencia.Produto;


public class FornecedoresActivity extends AppCompatActivity {

    private ListView listView;
    private AdapterListViewFornecedores adapterListView;
    private ArrayList<Fornecedor> fornecedores;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedores);
        Intent intent;

        listView = (ListView) findViewById(R.id.listViewFornecedores);
        btn = (Button) findViewById(R.id.buttonLigarFornecedorDois);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            trataClique(v);
            }
        });
        try {
            createListView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void trataClique(View v){
        Intent intent2;

        intent2 = new Intent(Intent.ACTION_CALL);
        intent2.setData(Uri.parse("tel: 3432196189"));
        this.startActivity(intent2);
    }


    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        //Pega o item que foi selecionado.
        Fornecedor fornecedor = adapterListView.getFornecedor(arg2);
        Intent intent;
        intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: 3432196189"));



    }

    private void createListView() throws SQLException {
        //Criamos nossa lista que preenchera o ListView
        fornecedores = new ArrayList<Fornecedor>();

        Fornecedor fornecedor1 = new Fornecedor(1,"Atacado","3432196189");
        fornecedores.add(fornecedor1);

        //Cria o adapter
        adapterListView = new AdapterListViewFornecedores(this, fornecedores);

        //Define o Adapter
        listView.setAdapter(adapterListView);

        //Cor quando a lista é selecionada para ralagem.
        listView.setCacheColorHint(Color.TRANSPARENT);


    }
}
