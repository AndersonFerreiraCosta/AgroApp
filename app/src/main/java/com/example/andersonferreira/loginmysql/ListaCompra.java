package com.example.andersonferreira.loginmysql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.andersonferreira.loginmysql.Download_data.download_complete;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaCompra extends AppCompatActivity  implements download_complete {

    public ListView list;
    public ArrayList<Compra> compra = new ArrayList<Compra>();
    public ListAdapterCompra adapter;

    private String vendedor_id = null;
    private String produto_id = null;

    public void get_data(String data)
    {
        try {
            JSONArray data_array=new JSONArray(data);
            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());
                Compra add=new Compra();
                add.vendedor_id = obj.getString("vendedor_id");
                add.vendedor_nome = obj.getString("vendedor_nome");
                add.telefone = obj.getString("telefone");
                add.email = obj.getString("email");
                add.produto_id = obj.getString("produto_id");
                add.produto_nome = obj.getString("produto_nome");
                add.quantidade = obj.getString("quantidade");
                add.unidade_medida = obj.getString("unidade_medida");
                compra.add(add);
            }


            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_compra);
        list = (ListView) findViewById(R.id.list);
        adapter = new ListAdapterCompra(this);
        list.setAdapter(adapter);


        vendedor_id = (String) getIntent().getSerializableExtra("vendedor_id");
        produto_id = (String) getIntent().getSerializableExtra("produto_id");


        Download_data download_data = new Download_data((download_complete) this);
        download_data.download_data_from_link("http://www.andersonferreira.esy.es/AgroApp/produtoEscolhido.php?vendedor_id="+vendedor_id+"?&produto_id="+produto_id);
        //download_data.download_data_from_link("http://www.andersonferreira.esy.es/AgroApp/teste.json");
    }


}
