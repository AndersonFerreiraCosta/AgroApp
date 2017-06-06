package com.example.andersonferreira.loginmysql;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andersonferreira.loginmysql.Download_data.download_complete;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaProdutosVenda extends AppCompatActivity  implements download_complete {

    public ListView list;
    public ArrayList<ProdutosVenda> produtosVenda = new ArrayList<ProdutosVenda>();
    public ListAdapterProdutosVenda adapter;

    private String categoriaParaSelecionar = null;
    private String usuario_id = null;

    Button btnCadastarProduto;

    private String vendedor_id = null;
    private String produto_id = null;


    public void get_data(String data)
    {
        try {
            JSONArray data_array=new JSONArray(data);
            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());
                ProdutosVenda add=new ProdutosVenda();
                add.vendedor_id = obj.getString("vendedor_id");
                add.vendedor_nome = obj.getString("vendedor_nome");
                add.produto_id = obj.getString("produto_id");
                add.produto_nome = obj.getString("produto_nome");
                add.quantidade = obj.getString("quantidade");
                add.unidade_medida = obj.getString("unidade_medida");
                produtosVenda.add(add);
            }


            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_pordutosvenda);
        list = (ListView) findViewById(R.id.list);
        adapter = new ListAdapterProdutosVenda(this);
        list.setAdapter(adapter);

        categoriaParaSelecionar = (String) getIntent().getSerializableExtra("categoria");


        Download_data download_data = new Download_data((download_complete) this);
        download_data.download_data_from_link("http://www.andersonferreira.esy.es/AgroApp/listaProdutosPorCategoria.php?categoria="+categoriaParaSelecionar);
        //download_data.download_data_from_link("http://www.andersonferreira.esy.es/AgroApp/teste.json");

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Intent form = new Intent(ListaProdutosVenda.this, ListaCompra.class);

                vendedor_id = produtosVenda.get(position).getVendedor_id();
                produto_id = produtosVenda.get(position).getProduto_id();

                form.putExtra("vendedor_id", vendedor_id);
                form.putExtra("produto_id", produto_id);

                startActivity(form);

            }
        });

     //   usuario_id = getIntent().getExtras().getString("usuario_id");
       // Toast.makeText(getApplicationContext(), usuario_id , Toast.LENGTH_LONG).show();


        btnCadastarProduto = (Button) findViewById(R.id.btnCadastrarProduto);

      btnCadastarProduto.setOnClickListener(new View.OnClickListener() {
         @Override public void onClick(View v) {

            ConnectivityManager connMgr = (ConnectivityManager)
                      getSystemService(Context.CONNECTIVITY_SERVICE);NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

             if (networkInfo != null && networkInfo.isConnected()) {

                  Intent form2 = new Intent(ListaProdutosVenda.this, TelaCadastroProduto.class);

                   usuario_id = getIntent().getExtras().getString("usuario_id");

                  form2.putExtra("usuario_id", usuario_id);
                 form2.putExtra("produto_id", categoriaParaSelecionar);

                 startActivity(form2);

             } else {
                 Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada", Toast.LENGTH_LONG).show();
               }
           }
       });

  }


}
