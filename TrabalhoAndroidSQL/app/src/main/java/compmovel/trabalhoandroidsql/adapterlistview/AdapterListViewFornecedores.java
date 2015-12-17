package compmovel.trabalhoandroidsql.adapterlistview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import compmovel.trabalhoandroidsql.R;
import compmovel.trabalhoandroidsql.persistencia.Fornecedor;

/**
 * Created by vitor on 16/12/15.
 */
public class AdapterListViewFornecedores extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<Fornecedor> fornecedores;

    public AdapterListViewFornecedores(Context context, ArrayList<Fornecedor> fornecedores) {
        //Itens que preencheram o listview
        this.fornecedores = fornecedores;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }


    /**
     * Retorna a quantidade de itens
     *
     * @return
     */
    public int getCount() {
        return fornecedores.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public Fornecedor getFornecedor(int position) {
        return fornecedores.get(position);
    }

    /**
     * Sem implementação
     *
     * @param position
     * @return
     */
    public long getIFornecedorId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        //Pega o item de acordo com a posção.
        Fornecedor item = fornecedores.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.fornecedor_list, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.textView6)).setText(item.getNome());
        ((TextView) view.findViewById(R.id.textView7)).setText("Telefone: "+item.getTelefone());



        //((ImageView) view.findViewById(R.id.imagemview)).setImageResource(item.getIconeRid());

        return view;
    }

}
