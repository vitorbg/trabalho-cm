package compmovel.trabalhoandroidsql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

import compmovel.trabalhoandroidsql.persistencia.Produto;
import compmovel.trabalhoandroidsql.persistencia.ProdutoDAO;

public class ProdutoActivity extends AppCompatActivity {

    TextView txtViewNome;
    TextView txtViewDescricao;
    TextView txtViewPreco;
    Button btnApagar;
    Button btnAlterar;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");

        txtViewNome = (TextView) this.findViewById(R.id.textViewProdutoNome);
        txtViewDescricao = (TextView) this.findViewById(R.id.textViewProdutoDescricao);
        txtViewPreco = (TextView) this.findViewById(R.id.textViewProdutoPreco);
        btnApagar = (Button) this.findViewById(R.id.btnProdutoDeletar);
        btnAlterar = (Button) this.findViewById(R.id.btnProdutoAlterar);


        ProdutoDAO produtoDAO = new ProdutoDAO(this);
        try {
            produtoDAO.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Produto produto = produtoDAO.getById(id);
        produtoDAO.close();

        txtViewNome.setText(produto.getNome());
        txtViewDescricao.setText(produto.getDescricao());
        txtViewPreco.setText(String.valueOf(produto.getPreco()));

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apagaProduto(v);
            }

        });

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alteraProduto(v);
            }

        });

    }


    private void alteraProduto(View v){



        Intent intent = new Intent(this,AddActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("altera",true);


        this.startActivity(intent);
    }


    private void apagaProduto(View v){
        ProdutoDAO produtoDAO = new ProdutoDAO(this);
        try {
            produtoDAO.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Produto produto = produtoDAO.getById(id);
        produtoDAO.delete(produto);
        produtoDAO.close();


        Intent intent = new Intent(this,MainActivity.class);


        this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_produto, menu);
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
