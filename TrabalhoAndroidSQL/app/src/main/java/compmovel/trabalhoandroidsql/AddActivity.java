package compmovel.trabalhoandroidsql;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.sql.SQLException;

import compmovel.trabalhoandroidsql.persistencia.Produto;
import compmovel.trabalhoandroidsql.persistencia.ProdutoDAO;

public class AddActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtDescricao;
    private EditText edtPreco;
    private Button btnAdiciona;
    private Button btnFoto;
    private int id;
    private boolean altera;
    private Produto produto;
    private String localFoto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edtNome = (EditText) this.findViewById(R.id.editTextNome);
        edtDescricao = (EditText) this.findViewById(R.id.editTextDescricao);
        edtPreco = (EditText) this.findViewById(R.id.editTextPreco);
        btnAdiciona = (Button) this.findViewById(R.id.btnAdiciona);
        btnFoto = (Button) this.findViewById(R.id.buttonTiraFoto);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        altera = extras.getBoolean("altera");

        if (altera == true){

            ProdutoDAO produtoDAO = new ProdutoDAO(this);
            try {
                produtoDAO.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            produto = produtoDAO.getById(id);
            produtoDAO.close();
            edtNome.setText(produto.getNome());
            edtDescricao.setText(produto.getDescricao());
            edtPreco.setText(String.valueOf(produto.getPreco()));

        }
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            tiraFoto(v);
            }

        });

        btnAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    insere(v);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        });

    }

    private void tiraFoto(View v){
        localFoto = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".jpg";
        File arquivo = new File(localFoto);

        Uri local = Uri.fromFile(arquivo);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,localFoto);

        startActivity(intent);
    }

    private void insere(View v) throws SQLException {

        if(altera==true){
            ProdutoDAO produtoDAO = new ProdutoDAO(this);
            produtoDAO.open();
            produtoDAO.delete(produto);
            produtoDAO.close();
        }

        String nome = edtNome.getText().toString();
        String descricao = edtDescricao.getText().toString();
        Double preco = Double.valueOf(edtPreco.getText().toString());
        String foto = localFoto;

        ProdutoDAO produtoDAO = new ProdutoDAO(this);
        produtoDAO.open();
        produtoDAO.create(nome, descricao, preco,foto);
        produtoDAO.getAll();
        produtoDAO.close();

        Intent intent = new Intent(this,MainActivity.class);
        this.startActivity(intent);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
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
