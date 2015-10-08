package compmovel.trabalhoandroidsql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import compmovel.trabalhoandroidsql.persistencia.Produto;

/**
 * Created by vitor on 08/10/15.
 */

public class AdapterListView extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<Produto> itens;

    public AdapterListView(Context context, ArrayList<Produto> itens) {
        //Itens que preencheram o listview
        this.itens = itens;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Retorna a quantidade de itens
     *
     * @return
     */
    public int getCount() {
        return itens.size();
    }

    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public Produto getItem(int position) {
        return itens.get(position);
    }

    /**
     * Sem implementação
     *
     * @param position
     * @return
     */
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        //Pega o item de acordo com a posção.
        Produto item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.item_list, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.textView1)).setText(item.getNome());
        ((TextView) view.findViewById(R.id.textView2)).setText(item.getDescricao());
        ((TextView) view.findViewById(R.id.textView3)).setText(String.valueOf(item.getPreco()));
        //((ImageView) view.findViewById(R.id.imagemview)).setImageResource(item.getIconeRid());

        return view;
    }
}
