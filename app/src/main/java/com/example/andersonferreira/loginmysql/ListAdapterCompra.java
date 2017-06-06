package com.example.andersonferreira.loginmysql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapterCompra extends BaseAdapter {

    ListaCompra main;
    ListAdapterCompra(ListaCompra main)
    {
        this.main = main;
    }



    public int getCount() {
        return  main.compra.size();
    }
    public Object getItem(int position) {
        return null;
    }
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolderItem {
        TextView vendedor_id;
        TextView vendedor_nome;
        TextView produto_id;
        TextView produto_nome;
        TextView quantidade;
        TextView unidade_medida;
        TextView telefone;
        TextView email;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent){
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) main.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_compra, null);

            holder.vendedor_nome = (TextView) convertView.findViewById(R.id.vendedor_nome);
            holder.telefone = (TextView) convertView.findViewById(R.id.telefone);
            holder.email = (TextView) convertView.findViewById(R.id.email);
            holder.produto_nome = (TextView) convertView.findViewById(R.id.produto_nome);
            holder.quantidade = (TextView) convertView.findViewById(R.id.quantidade);
            holder.unidade_medida = (TextView) convertView.findViewById(R.id.unidade_medida);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolderItem) convertView.getTag();
        }

        holder.vendedor_nome.setText(this.main.compra.get(position).vendedor_nome);
        holder.telefone.setText(this.main.compra.get(position).telefone);
        holder.email.setText(this.main.compra.get(position).email);
        holder.produto_nome.setText(this.main.compra.get(position).produto_nome);
        holder.quantidade.setText(this.main.compra.get(position).quantidade);
        holder.unidade_medida.setText(this.main.compra.get(position).unidade_medida);

        return convertView;
    }

}