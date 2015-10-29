package compmovel.trabalhoandroidsql;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import compmovel.trabalhoandroidsql.persistencia.Produto;
import compmovel.trabalhoandroidsql.persistencia.ProdutoDAO;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ImageButton btnAdicionar;
    private ListView listView;
    private AdapterListView adapterListView;
    private ArrayList<Produto> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        btnAdicionar = (ImageButton) this.findViewById(R.id.imgBtnAdicionar);


        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaAdiciona(v);
            }

        });


        //Define o Listener quando alguem clicar no item.
        listView.setOnItemClickListener(this);
        try {
            createListView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createListView() throws SQLException {
        //Criamos nossa lista que preenchera o ListView
        itens = new ArrayList<Produto>();

        ProdutoDAO produtoDAO = new ProdutoDAO(this);
        produtoDAO.open();
        ArrayList<Produto> all = produtoDAO.getAll();
        produtoDAO.close();



        Produto item1 = new Produto(1,"Pão", "Pão Frances", 50);
        Produto item2 = new Produto(2,"Pão", "Pão Italiano", 32);
        Produto item3 = new Produto(3,"Pão", "Pão Doce", 56);
        Produto item4 = new Produto(4,"Pão", "Pão Brasileiro", 20);

        itens.add(item1);
        itens.add(item2);
        itens.add(item3);
        itens.add(item4);

        //Cria o adapter
        adapterListView = new AdapterListView(this, all);

        //Define o Adapter
        listView.setAdapter(adapterListView);

        //Cor quando a lista é selecionada para ralagem.
        listView.setCacheColorHint(Color.TRANSPARENT);



    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        //Pega o item que foi selecionado.
        Produto item = adapterListView.getItem(arg2);
        //Demostração
        Toast.makeText(this, "Você Clicou em: " + item.getNome() +" - "+ item.getDescricao(), Toast.LENGTH_LONG).show();
    }


    private void chamaTelaAdiciona(View v){
        Intent intent = new Intent(this,AddActivity.class);
        this.startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
