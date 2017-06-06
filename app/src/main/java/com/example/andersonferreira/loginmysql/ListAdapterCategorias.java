package com.example.andersonferreira.loginmysql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapterCategorias extends BaseAdapter {

    ListaCategorias main;
    ListAdapterCategorias(ListaCategorias main)
    {
        this.main = main;
    }
    public int getCount() {
        return  main.categorias.size();
    }
    public Object getItem(int position) {
        return null;
    }
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolderItem {


        TextView nome;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent){
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) main.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_categorias, null);


            holder.nome = (TextView) convertView.findViewById(R.id.nome);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolderItem) convertView.getTag();

        }

        holder.nome.setText(this.main.categorias.get(position).nome);


        return convertView;
    }

}