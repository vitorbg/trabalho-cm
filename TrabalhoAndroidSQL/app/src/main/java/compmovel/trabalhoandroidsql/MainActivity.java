package compmovel.trabalhoandroidsql;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

import compmovel.trabalhoandroidsql.adapterlistview.AdapterListView;
import compmovel.trabalhoandroidsql.persistencia.Produto;
import compmovel.trabalhoandroidsql.persistencia.ProdutoDAO;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EditText editTextPesquisaProduto;
    private ImageButton btnAdicionar;
    private Button btnSair;
    private ListView listView;
    private AdapterListView adapterListView;
    private ArrayList<Produto> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        editTextPesquisaProduto = (EditText) this.findViewById(R.id.editTextPesquisaProduto);

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

        Intent intent = new Intent(this,ProdutoActivity.class);

        intent.putExtra("id",item.getId());
        this.startActivity(intent);

        //Demostração
        Toast.makeText(this, "Você Clicou em: " + item.getNome() + " - " + item.getDescricao(), Toast.LENGTH_LONG).show();
    }


    private void chamaTelaAdiciona(View v){
        Intent intent = new Intent(this,AddActivity.class);

        intent.putExtra("altera",false);
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
        if (id == R.id.novo_produto) {
            Intent intent = new Intent(this,AddActivity.class);
            intent.putExtra("altera",false);
            this.startActivity(intent);
            return true;
        }

        if (id == R.id.fornecedores) {
            Intent intent = new Intent(this,FornecedoresActivity.class);
            this.startActivity(intent);
            return true;
        }

        if (id == R.id.servidor) {
            Intent intent = new Intent(this,ServidorActivity.class);
            this.startActivity(intent);
            return true;
        }

        if (id == R.id.sair_app) {
            System.exit(1);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
