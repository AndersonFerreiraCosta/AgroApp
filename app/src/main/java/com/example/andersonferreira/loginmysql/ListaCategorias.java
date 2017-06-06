package com.example.andersonferreira.loginmysql;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.andersonferreira.loginmysql.Download_data.download_complete;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaCategorias extends AppCompatActivity  implements Download_data.download_complete {

    public ListView list;
    //public ArrayList<Categorias> categorias = new ArrayList<Categorias>();
    public List<Categorias> categorias = new ArrayList<Categorias>();
    public ListAdapterCategorias adapter;

    private String categoriaSelecionado = null;
    private String usuario_id = null;


    public void get_data(String data)
    {
        try {
            JSONArray data_array=new JSONArray(data);
            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());
                Categorias add=new Categorias();

                add.id = obj.getString("id");
                add.nome = obj.getString("nome");
                categorias.add(add);
            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_categorias);
        list = (ListView) findViewById(R.id.list);
        adapter = new ListAdapterCategorias(this);
        list.setAdapter(adapter);

        Download_data download_data = new Download_data((download_complete) this);
        download_data.download_data_from_link("http://www.andersonferreira.esy.es/AgroApp/listarCategorias.php");


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent form = new Intent(ListaCategorias.this, ListaProdutosVenda.class);

                categoriaSelecionado = categorias.get(position).getId();
                usuario_id = getIntent().getExtras().getString("usuario_id");

                form.putExtra("categoria", categoriaSelecionado);
                form.putExtra("usuario_id", usuario_id);

                startActivity(form);

            }
        });


    }

}
