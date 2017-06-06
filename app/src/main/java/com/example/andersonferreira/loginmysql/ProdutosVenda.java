package com.example.andersonferreira.loginmysql;

import java.io.Serializable;

public class ProdutosVenda implements Serializable {

    String vendedor_id;
    String vendedor_nome;
    String produto_id;
    String produto_nome;
    String quantidade;
    String unidade_medida;


    public String getVendedor_id() {return vendedor_id;}

    public void setVendedor_id(String vendedor_id) {
        this.vendedor_id = vendedor_id;
    }

    public String getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(String produto_id) {
        this.produto_id = produto_id;
    }
}
