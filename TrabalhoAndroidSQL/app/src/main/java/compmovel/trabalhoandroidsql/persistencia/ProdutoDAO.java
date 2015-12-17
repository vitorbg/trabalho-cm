package compmovel.trabalhoandroidsql.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitor on 08/10/15.
 */
public class ProdutoDAO {

    private SQLiteDatabase database;
    private String[] columns = {VendasDB.COLUMN_ID,
            VendasDB.COLUMN_NOME,
            VendasDB.COLUMN_DESCRICAO,
            VendasDB.COLUMN_PRECO,
            VendasDB.COLUMN_FOTO};

    private VendasDB sqliteOpenHelper;

    public ProdutoDAO(Context context) {
        sqliteOpenHelper = new VendasDB(context);
    }

    public void open() throws SQLException {
        database = sqliteOpenHelper.getWritableDatabase();
    }

    public void close() {
        sqliteOpenHelper.close();
    }

    public void create(String nome, String descricao, double preco, String foto) {
        ContentValues values = new ContentValues();

// inserindo na tabela
        values.put(VendasDB.COLUMN_NOME, nome);
        values.put(VendasDB.COLUMN_DESCRICAO, descricao);
        values.put(VendasDB.COLUMN_PRECO, preco);
        values.put(VendasDB.COLUMN_FOTO, foto);
        long insertId = database.insert(VendasDB.TABLE_PRODUTO, null, values);
    }

    public void delete ( Produto produto ) {
        long id = produto.getId();
        database.delete (VendasDB.TABLE_PRODUTO,
                VendasDB.COLUMN_ID + " = " + id , null );
    }

    public ArrayList <Produto> getAll () {
        ArrayList<Produto> itens = new ArrayList<Produto>() ;
        Cursor cursor = database . query ( VendasDB.TABLE_PRODUTO,
                columns, null , null , null , null , null );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Produto produto = new Produto();
            produto.setId(cursor.getInt(0));
            produto.setNome(cursor.getString(1));
            produto.setDescricao(cursor.getString(2));
            produto.setPreco(cursor.getDouble(3));
            produto.setFoto(cursor.getString(4));
            itens.add(produto);
            cursor.moveToNext();

            System.out.println("id: " + produto.getId());
            System.out.println("nome: " + produto.getNome());
            System.out.println("nota: " + produto.getDescricao());
        }
        cursor . close();
        return itens ;
    }

    public Produto getById(int id){
        Produto resultado = null;
        ArrayList<Produto> itens = new ArrayList<Produto>() ;
        Cursor cursor = database . query ( VendasDB.TABLE_PRODUTO,
                columns, null , null , null , null , null );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Produto produto = new Produto();
            produto.setId(cursor.getInt(0));
            produto.setNome(cursor.getString(1));
            produto.setDescricao(cursor.getString(2));
            produto.setPreco(cursor.getDouble(3));
            produto.setFoto(cursor.getString(4));
            if(produto.getId() == id){
                resultado = produto;
            }
            itens.add(produto);
            cursor.moveToNext();

            System.out.println("id: " + produto.getId());
            System.out.println("nome: " + produto.getNome());
            System.out.println("nota: " + produto.getDescricao());
            System.out.println("foto: " + produto.getFoto());

        }
        cursor . close ();
        return resultado;
    }



}