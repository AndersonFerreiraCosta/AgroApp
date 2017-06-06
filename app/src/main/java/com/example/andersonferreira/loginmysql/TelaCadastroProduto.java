package com.example.andersonferreira.loginmysql;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastroProduto extends AppCompatActivity {

    EditText editvendedor_id, editproduto_id, editquantidade, editunidade_medida;
    Button btncadastrar, btncancelar;

    private String usuario_id = null;
    private String categoria_id = null;

    String url = "";
    String parametros = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro_produto);

        // editvendedor_id = (EditText)findViewById(R.id.vendedor_id);
       // editproduto_id = (EditText)findViewById(R.id.produto_id);
        editquantidade = (EditText)findViewById(R.id.quantidade);
        editunidade_medida = (EditText)findViewById(R.id.unidade_medida);
        btncadastrar = (Button) findViewById(R.id.cadastrar);
        btncancelar = (Button) findViewById(R.id.cancelar);


        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    usuario_id = getIntent().getExtras().getString("usuario_id");
                    categoria_id = (String) getIntent().getSerializableExtra("produto_id");

                    String vendedor_id = usuario_id;
                    String produto_id = categoria_id;

                   // String vendedor_id = editvendedor_id.getText().toString();
                   // String produto_id = editproduto_id.getText().toString();
                    String quantidade = editquantidade.getText().toString();
                    String unidade_medida = editunidade_medida.getText().toString();


                    if(vendedor_id.isEmpty() || produto_id.isEmpty() || quantidade.isEmpty() || unidade_medida.isEmpty() ) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://www.andersonferreira.esy.es/AgroApp/registrarProduto.php";

                        parametros = "vendedor_id=" + vendedor_id + "&produto_id=" + produto_id + "&quantidade=" + quantidade + "&unidade_medida=" + unidade_medida;

                        new SolicitaDados().execute(url);





                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada", Toast.LENGTH_LONG).show();
                }





            }
        });
    }
    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected  String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);
        }
        @Override
        protected void onPostExecute(String resultado) {

            if (resultado.contains("registro_erro")) {

                Toast.makeText(getApplicationContext(), "Erro no registro", Toast.LENGTH_LONG).show();
            }

            if (resultado.contains("update_erro")) {

                    Toast.makeText(getApplicationContext(), "Erro ao atualizar", Toast.LENGTH_LONG).show();
            }
            if (resultado.contains("registro_ok")) {

                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                Intent abreInicio = new Intent(TelaCadastroProduto.this, ListaCategorias.class);

                usuario_id = getIntent().getExtras().getString("usuario_id");
                abreInicio.putExtra("usuario_id", usuario_id);

                startActivity(abreInicio);
            }

                if (resultado.contains("update_ok")) {

                    Toast.makeText(getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_LONG).show();
                    Intent abreInicio2 = new Intent(TelaCadastroProduto.this, ListaCategorias.class);

                    usuario_id = getIntent().getExtras().getString("usuario_id");
                    abreInicio2.putExtra("usuario_id", usuario_id);

                    startActivity(abreInicio2);
            } //else {
               // Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG).show();
          //  }

        }
    }
}
