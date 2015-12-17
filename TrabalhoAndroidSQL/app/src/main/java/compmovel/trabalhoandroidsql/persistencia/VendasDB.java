package compmovel.trabalhoandroidsql.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vitor on 08/10/15.
 */
public class VendasDB extends SQLiteOpenHelper {
    public static final String TABLE_PRODUTO = "produto";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_DESCRICAO = "descricao";
    public static final String COLUMN_PRECO = "preco";
    public static final String COLUMN_FOTO = "foto";
    private static final String DATABASE_NAME = "vendas.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_PRODUTO + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            +COLUMN_NOME + " text not null, "
            +COLUMN_DESCRICAO + " text not null, "
            +COLUMN_PRECO + " real not null, "
            +COLUMN_FOTO + " text not null ); ";


    public VendasDB(Context context) {
        super ( context , DATABASE_NAME , null , DATABASE_VERSION );
    }

    @Override
    public void onCreate (SQLiteDatabase database) {
        database.execSQL (DATABASE_CREATE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db. execSQL (" DROP TABLE IF EXISTS " + TABLE_PRODUTO);
        onCreate(db);
    }
}
